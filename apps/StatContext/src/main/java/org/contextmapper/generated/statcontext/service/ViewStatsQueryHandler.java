package org.contextmapper.generated.statcontext.service;

import org.contextmapper.generated.statcontext.repository.ViewStatsCommandRepository;
import org.contextmapper.generated.statcontext.service.dto.UserStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.dto.ViewStatsCommandDTO;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectQuestionMapper;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectTagMapper;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectUserMapper;
import org.contextmapper.generated.statcontext.service.mapper.ViewStatsCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@Transactional
public class ViewStatsQueryHandler extends ViewStatsCommandService {
    private final Logger log = LoggerFactory.getLogger(ViewStatsQueryHandler.class);

    private final UserStatsViewedEventService userStatsViewedEventService;
    private final QuestionStatsViewedEventService questionStatsViewedEventService;
    private final TagStatsViewedEventService tagStatsViewedEventService;

    private final StatisticSubjectUserMapper statisticSubjectUserMapper;
    private final StatisticSubjectQuestionMapper statisticSubjectQuestionMapper;
    private final StatisticSubjectTagMapper statisticSubjectTagMapper;

    public ViewStatsQueryHandler(
        ViewStatsCommandRepository viewStatsCommandRepository,
        UserStatsViewedEventService userStatsViewedEventService,
        QuestionStatsViewedEventService questionStatsViewedEventService,
        TagStatsViewedEventService tagStatsViewedEventService,
        StatisticSubjectUserMapper statisticSubjectUserMapper,
        StatisticSubjectQuestionMapper statisticSubjectQuestionMapper,
        StatisticSubjectTagMapper statisticSubjectTagMapper,
        ViewStatsCommandMapper viewStatsCommandMapper
    ) {
        super(viewStatsCommandRepository, viewStatsCommandMapper);
        this.userStatsViewedEventService = userStatsViewedEventService;
        this.questionStatsViewedEventService = questionStatsViewedEventService;
        this.tagStatsViewedEventService = tagStatsViewedEventService;
        this.statisticSubjectUserMapper = statisticSubjectUserMapper;
        this.statisticSubjectQuestionMapper = statisticSubjectQuestionMapper;
        this.statisticSubjectTagMapper = statisticSubjectTagMapper;
    }

    public ViewStatsCommandDTO handleViewLeaderBoardQuery(ViewStatsCommandDTO viewStatsCommand) {
        final var userDTO = viewStatsCommand.getUser();
        // final var questionDTO = viewStatsCommand.getQuestion();
        // final var tagDTO = viewStatsCommand.getTag();
        log.info("Handle command to view stats");
        /* Not implemented
        if (questionDTO != null) {
            final var questionStatsViewedEventDTO = new QuestionStatsViewedEventDTO();
            questionStatsViewedEventDTO.setQuestion(questionDTO);
            questionStatsViewedEventService.save(questionStatsViewedEventDTO);
            viewStatsCommand.setQuestion(questionDTO);
        }

        if (tagDTO != null) {
            final var tagStatsViewedEventDTO = new TagStatsViewedEventDTO();
            tagStatsViewedEventDTO.setTag(tagDTO);
            tagStatsViewedEventService.save(tagStatsViewedEventDTO);
            viewStatsCommand.setTag(tagDTO);
        }
        */

        return save(viewStatsCommand);
    }
}
