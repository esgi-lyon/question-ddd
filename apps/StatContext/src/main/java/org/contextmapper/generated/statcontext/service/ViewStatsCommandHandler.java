package org.contextmapper.generated.statcontext.service;

import org.contextmapper.generated.statcontext.domain.ViewStatsCommand;
import org.contextmapper.generated.statcontext.repository.ViewStatsCommandRepository;
import org.contextmapper.generated.statcontext.service.dto.UserStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.dto.QuestionStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.dto.TagStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectUserMapper;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectQuestionMapper;
import org.contextmapper.generated.statcontext.service.mapper.StatisticSubjectTagMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

@Primary
@Service
@Transactional
public class ViewStatsCommandHandler extends ViewStatsCommandService {
    private final Logger log = LoggerFactory.getLogger(ViewStatsCommandHandler.class);

    private final UserStatsViewedEventService userStatsViewedEventService;
    private final QuestionStatsViewedEventService questionStatsViewedEventService;
    private final TagStatsViewedEventService tagStatsViewedEventService;

    private final StatisticSubjectUserMapper statisticSubjectUserMapper;
    private final StatisticSubjectQuestionMapper statisticSubjectQuestionMapper;
    private final StatisticSubjectTagMapper statisticSubjectTagMapper;

    public ViewStatsCommandHandler(
        ViewStatsCommandRepository viewStatsCommandRepository,
        UserStatsViewedEventService userStatsViewedEventService,
        QuestionStatsViewedEventService questionStatsViewedEventService,
        TagStatsViewedEventService tagStatsViewedEventService,
        StatisticSubjectUserMapper statisticSubjectUserMapper,
        StatisticSubjectQuestionMapper statisticSubjectQuestionMapper,
        StatisticSubjectTagMapper statisticSubjectTagMapper
    ) {
        super(viewStatsCommandRepository);
        this.userStatsViewedEventService = userStatsViewedEventService;
        this.questionStatsViewedEventService = questionStatsViewedEventService;
        this.tagStatsViewedEventService = tagStatsViewedEventService;
        this.statisticSubjectUserMapper = statisticSubjectUserMapper;
        this.statisticSubjectQuestionMapper = statisticSubjectQuestionMapper;
        this.statisticSubjectTagMapper = statisticSubjectTagMapper;
    }

    public ViewStatsCommand handleViewStatsQuery(ViewStatsCommand viewStatsCommand) {
        final var userDTO = statisticSubjectUserMapper.toDto(viewStatsCommand.getUser());
        final var questionDTO = statisticSubjectQuestionMapper.toDto(viewStatsCommand.getQuestion());
        final var tagDTO = statisticSubjectTagMapper.toDto(viewStatsCommand.getTag());
        log.info("Handle command to view stats");

        // TODO Api calls
        if(userDTO != null){
            final var userStatsViewedEventDTO = new UserStatsViewedEventDTO();
            userStatsViewedEventDTO.setUser(userDTO);
            userStatsViewedEventService.save(userStatsViewedEventDTO);
            viewStatsCommand.setUser(statisticSubjectUserMapper.toEntity(userDTO));
        }

        if(questionDTO != null){
            final var questionStatsViewedEventDTO = new QuestionStatsViewedEventDTO();
            questionStatsViewedEventDTO.setQuestion(questionDTO);
            questionStatsViewedEventService.save(questionStatsViewedEventDTO);
            viewStatsCommand.setQuestion(statisticSubjectQuestionMapper.toEntity(questionDTO));
        }

        if(tagDTO != null){
            final var tagStatsViewedEventDTO = new TagStatsViewedEventDTO();
            tagStatsViewedEventDTO.setTag(tagDTO);
            tagStatsViewedEventService.save(tagStatsViewedEventDTO);
            viewStatsCommand.setTag(statisticSubjectTagMapper.toEntity(tagDTO));
        }

        return save(viewStatsCommand);
    }
}
