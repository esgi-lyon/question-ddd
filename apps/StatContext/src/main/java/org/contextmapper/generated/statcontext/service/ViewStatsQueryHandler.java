package org.contextmapper.generated.statcontext.service;

import org.contextmapper.generated.statcontext.client.evaluationcontext.api.QueryHandlersResourceApiClient;
import org.contextmapper.generated.statcontext.client.evaluationcontext.model.EvaluationDTO;
import org.contextmapper.generated.statcontext.domain.*;
import org.contextmapper.generated.statcontext.repository.EvaluationStatsRepository;
import org.contextmapper.generated.statcontext.repository.ViewStatsCommandRepository;
import org.contextmapper.generated.statcontext.service.dto.LoggedUserStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.dto.QuestionStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.dto.UserStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.dto.ViewStatsCommandDTO;
import org.contextmapper.generated.statcontext.service.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Service
@Transactional
public class ViewStatsQueryHandler extends ViewStatsCommandService {

    private final LoggedUserStatsViewedEventService loggedUserStatsViewedEventService;

    public static class AllEventsWrapper {
        public UserStatsViewedEventDTO userStatsViewedEventDTO;

        public LoggedUserStatsViewedEventDTO loggedUserStatsViewedEventDTO;

        public QuestionStatsViewedEventDTO questionStatsViewedEventDTO;

        public void userStatsViewedEventDTO(UserStatsViewedEventDTO userStatsViewedEventDTO) {
            this.userStatsViewedEventDTO = userStatsViewedEventDTO;
        }

        public void loggedUserStatsViewedEventDTO(LoggedUserStatsViewedEventDTO loggedUserStatsViewedEventDTO) {
            this.loggedUserStatsViewedEventDTO = loggedUserStatsViewedEventDTO;
        }

        public void questionStatsViewedEventDTO(QuestionStatsViewedEventDTO questionStatsViewedEventDTO) {
            this.questionStatsViewedEventDTO = questionStatsViewedEventDTO;
        }
    }
    private final Logger log = LoggerFactory.getLogger(ViewStatsQueryHandler.class);
    private final QueryHandlersResourceApiClient queryHandlersResourceApi;
    private final EvaluationStatsRepository evaluationStatsRepository;

    private final EvaluationStatsMapper evaluationStatsMapper;
    private final UserStatsViewedEventService userStatsViewedEventService;

    public ViewStatsQueryHandler(
        ViewStatsCommandRepository viewStatsCommandRepository,
        ViewStatsCommandMapper viewStatsCommandMapper,
        QueryHandlersResourceApiClient queryHandlersResourceApi,
        EvaluationStatsRepository evaluationStatsRepository,
        EvaluationStatsMapper evaluationStatsMapper,
        UserStatsViewedEventService userStatsViewedEventService,
        LoggedUserStatsViewedEventService loggedUserStatsViewedEventService
    ) {
        super(viewStatsCommandRepository, viewStatsCommandMapper);
        this.queryHandlersResourceApi = queryHandlersResourceApi;
        this.evaluationStatsRepository = evaluationStatsRepository;
        this.evaluationStatsMapper = evaluationStatsMapper;
        this.userStatsViewedEventService = userStatsViewedEventService;
        this.loggedUserStatsViewedEventService = loggedUserStatsViewedEventService;
    }

    public AllEventsWrapper handle(ViewStatsCommandDTO viewStatsCommand) {
        save(viewStatsCommand);
        log.info("Handle command to view stats");
        final var user = Objects.isNull(viewStatsCommand.getUser())
            ? SecurityContextHolder.getContext().getAuthentication().getName()
            : viewStatsCommand.getUser().getMail();

        final var events = new AllEventsWrapper();

        final var userEvaluations = Optional.ofNullable(
            queryHandlersResourceApi.handleViewUserEvaluationQuery(user).getBody()
            )
            .orElseThrow();

        final var totalScore = userEvaluations.stream().map(EvaluationDTO::getScore).reduce(Integer::sum).orElseThrow();

        final var savedStats = evaluationStatsRepository.save(
            new EvaluationStats()
                .total(totalScore)
                .evaluations(
                userEvaluations
                    .stream()
                    .map(e -> new EvaluationStatEntry()
                        .evaluationId(e.getId())
                        .question(new StatisticSubjectQuestion().questionId(e.getQuestion().getId()))
                    )
                    .collect(Collectors.toUnmodifiableSet())
            )
        );

        if (Objects.isNull(viewStatsCommand.getUser())) {
            final var userStatEvent = new LoggedUserStatsViewedEventDTO();
            userStatEvent.setStat(evaluationStatsMapper.toDto(savedStats));

            events.loggedUserStatsViewedEventDTO(loggedUserStatsViewedEventService.save(userStatEvent));
        }

        final var userStatEvent = new UserStatsViewedEventDTO();
        userStatEvent.setStat(evaluationStatsMapper.toDto(savedStats));

        events.userStatsViewedEventDTO(userStatsViewedEventService.save(userStatEvent));

        return events;
    }
}
