package org.contextmapper.generated.statcontext.service;

import org.contextmapper.generated.statcontext.client.evaluationcontext.api.AnsweringUserResourceApi;
import org.contextmapper.generated.statcontext.client.evaluationcontext.api.QueryHandlersResourceApiClient;
import org.contextmapper.generated.statcontext.client.evaluationcontext.model.EvaluationDTO;
import org.contextmapper.generated.statcontext.domain.*;
import org.contextmapper.generated.statcontext.repository.*;
import org.contextmapper.generated.statcontext.service.dto.LoggedUserStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.dto.QuestionStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.dto.UserStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.dto.ViewStatsCommandDTO;
import org.contextmapper.generated.statcontext.service.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Primary
@Service
@Transactional
public class ViewStatsQueryHandler extends ViewStatsCommandService {

    private final LoggedUserStatsViewedEventService loggedUserStatsViewedEventService;

    public static class AllEventsWrapper {
        public Set<EvaluationStatEntry> userStatsViewedEventDTO;

        public QuestionStatsViewedEventDTO questionStatsViewedEventDTO;

        public void userStatsViewedEvent(Set<EvaluationStatEntry> userStatsViewedEventDTO) {
            this.userStatsViewedEventDTO = userStatsViewedEventDTO;
        }

        // Not implemented
        public void questionStatsViewedEventDTO(QuestionStatsViewedEventDTO questionStatsViewedEventDTO) {
            this.questionStatsViewedEventDTO = questionStatsViewedEventDTO;
        }
    }

    private final Logger log = LoggerFactory.getLogger(ViewStatsQueryHandler.class);
    private final QueryHandlersResourceApiClient queryHandlersResourceApi;
    private final EvaluationStatsRepository evaluationStatsRepository;

    private final EvaluationStatsMapper evaluationStatsMapper;
    private final UserStatsViewedEventService userStatsViewedEventService;
    private final StatisticSubjectQuestionRepository statisticSubjectQuestionRepository;
    private final EvaluationStatEntryRepository evaluationStatEntryRepository;
    private final StatisticSubjectUserRepository statisticSubjectUserRepository;

    public ViewStatsQueryHandler(
        ViewStatsCommandRepository viewStatsCommandRepository,
        ViewStatsCommandMapper viewStatsCommandMapper,
        QueryHandlersResourceApiClient queryHandlersResourceApi,
        EvaluationStatsRepository evaluationStatsRepository,
        EvaluationStatsMapper evaluationStatsMapper,
        UserStatsViewedEventService userStatsViewedEventService,
        LoggedUserStatsViewedEventService loggedUserStatsViewedEventService,
        StatisticSubjectQuestionRepository statisticSubjectQuestionRepository,
        EvaluationStatEntryRepository evaluationStatEntryRepository,
        StatisticSubjectUserRepository statisticSubjectUserRepository) {
        super(viewStatsCommandRepository, viewStatsCommandMapper);
        this.queryHandlersResourceApi = queryHandlersResourceApi;
        this.evaluationStatsRepository = evaluationStatsRepository;
        this.evaluationStatsMapper = evaluationStatsMapper;
        this.userStatsViewedEventService = userStatsViewedEventService;
        this.loggedUserStatsViewedEventService = loggedUserStatsViewedEventService;
        this.statisticSubjectQuestionRepository = statisticSubjectQuestionRepository;
        this.evaluationStatEntryRepository = evaluationStatEntryRepository;
        this.statisticSubjectUserRepository = statisticSubjectUserRepository;
    }

    public AllEventsWrapper handle(ViewStatsCommandDTO viewStatsCommand) {
        log.info("Handle command to view stats");
        final var user = viewStatsCommand.getUser().getMail();

        final var events = new AllEventsWrapper();

        final var userEvaluations = Optional.ofNullable(
                queryHandlersResourceApi.handleViewUserEvaluationQuery(user).getBody()
            )
            .orElseThrow();

        final var totalScore = userEvaluations.stream()
            .map(EvaluationDTO::getScore).reduce(Integer::sum).orElseThrow();

        final var savedStats = evaluationStatsRepository.save(
            new EvaluationStats().total(totalScore)
        );

        final var savedEvaluationEntries = userEvaluations
            .stream()
            .map(e -> evaluationStatEntryRepository.save(new EvaluationStatEntry()
                .evaluationId(e.getId())
                .evaluationStats(savedStats)
                    .score(e.getScore())
                    .user(statisticSubjectUserRepository.save(
                        new StatisticSubjectUser().mail(user)
                    ))
                .question(
                    statisticSubjectQuestionRepository.save(
                        new StatisticSubjectQuestion().questionId(e.getQuestion().getId())
                    )
                )
            ))
            .collect(Collectors.toUnmodifiableSet());

        final var userStatEvent = new UserStatsViewedEventDTO();
        userStatEvent.setStat(evaluationStatsMapper.toDto(savedStats));
        userStatsViewedEventService.save(userStatEvent);

        events.userStatsViewedEvent(savedEvaluationEntries);

        return events;
    }
}
