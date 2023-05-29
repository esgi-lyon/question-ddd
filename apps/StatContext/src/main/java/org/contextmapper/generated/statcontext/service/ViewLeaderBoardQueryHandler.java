package org.contextmapper.generated.statcontext.service;

import org.contextmapper.generated.statcontext.repository.ViewStatsCommandRepository;
import org.contextmapper.generated.statcontext.service.dto.UserStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.dto.ViewLeaderBoardCommandDTO;
import org.contextmapper.generated.statcontext.service.dto.ViewStatsCommandDTO;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectUserMapper;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectQuestionMapper;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectTagMapper;
import org.contextmapper.generated.statcontext.service.mapper.ViewStatsCommandMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

@Primary
@Service
@Transactional
public class ViewLeaderBoardQueryHandler extends ViewStatsCommandService {
    private final Logger log = LoggerFactory.getLogger(ViewLeaderBoardQueryHandler.class);

    private final UserStatsViewedEventService userStatsViewedEventService;
    private final QuestionStatsViewedEventService questionStatsViewedEventService;
    private final TagStatsViewedEventService tagStatsViewedEventService;

    private final StatisticSubjectUserMapper statisticSubjectUserMapper;
    private final StatisticSubjectQuestionMapper statisticSubjectQuestionMapper;
    private final StatisticSubjectTagMapper statisticSubjectTagMapper;

    public ViewLeaderBoardQueryHandler(
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

    public ViewLeaderBoardCommandDTO handleViewLeaderBoardQuery(ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO) {
        // final var userDTO = viewLeaderBoardCommandDTO.getUser();
        // final var questionDTO = viewStatsCommand.getQuestion();
        // final var tagDTO = viewStatsCommand.getTag();
        /*
        log.info("Handle command to view leaderboard");

        if (userDTO != null) {
            final var userStatsViewedEventDTO = new UserStatsViewedEventDTO();
            userStatsViewedEventDTO.setUser(userDTO);
            userStatsViewedEventService.save(userStatsViewedEventDTO);
            viewLeaderBoardCommandDTO.setUser(userDTO);
        }



        return save(viewLeaderBoardCommandDTO);*/
        return null;
    }
}
