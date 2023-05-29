package org.contextmapper.generated.answercontext.service;

import org.contextmapper.generated.answercontext.client.sendquestioncontext.api.QueryHandlersApi;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.api.QuestionSentTagInfosResourceApi;
import org.contextmapper.generated.answercontext.domain.enumeration.AnswerState;
import org.contextmapper.generated.answercontext.repository.TagChoicesListedEventRepository;
import org.contextmapper.generated.answercontext.service.dto.*;
import org.contextmapper.generated.answercontext.service.mapper.TagChoicesListedEventMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

import java.util.Optional;

@Primary
@Service
@Transactional
public class TagChoicesListCommandHandler extends TagChoicesListedEventService {
    private final Logger log = LoggerFactory.getLogger(TagChoicesListCommandHandler.class);

    private final TagChoicesListedEventService tagChoicesListedEventService;

    private final TagChoicesListedService tagChoicesListedService;

    private final AvailableAnswerService availableAnswerService;

    private final QueryHandlersApi queryHandler;

    private final QuestionSentTagInfosResourceApi questionSentTagInfosResourceApi;

    private final QuestionSentIdService questionSentIdService;
    private final UserEmailService userEmailService;
    private final AnswerService answerService;

    public TagChoicesListCommandHandler(
        TagChoicesListedEventRepository tagChoicesListEventRepository,
        TagChoicesListedEventService tagChoicesListedEventService,
        TagChoicesListedEventMapper tagChoicesListedEventMapper,
        TagChoicesListedService tagChoicesListedService,
        AvailableAnswerService availableAnswerService,
        QueryHandlersApi queryHandlersApi,
        QuestionSentTagInfosResourceApi questionSentTagInfosResourceApi,
        QuestionSentIdService questionSentIdService,
        UserEmailService userEmailService, AnswerService answerService) {
        super(tagChoicesListEventRepository, tagChoicesListedEventMapper);
        this.tagChoicesListedEventService = tagChoicesListedEventService;
        this.tagChoicesListedService = tagChoicesListedService;
        this.availableAnswerService = availableAnswerService;
        this.queryHandler = queryHandlersApi;
        this.questionSentTagInfosResourceApi = questionSentTagInfosResourceApi;
        this.questionSentIdService = questionSentIdService;
        this.userEmailService = userEmailService;
        this.answerService = answerService;
    }

    public TagChoicesListedEventDTO handleTagChoicesListCommand(TagChoicesListCommandDTO cmd) {
        log.info("Handle command to list tag choices");

        final var questionId = cmd.getQuestionSent().getId();
        final var tagInfosDTOList = Optional.ofNullable(queryHandler.handleListTagInfos(questionId).getBody())
            .orElseThrow(() -> new RuntimeException("No question to view" + questionId));

        final var answer = new AnswerDTO();
        answer.setAnswerState(AnswerState.OPEN);
        final var questionSentId = new QuestionSentIdDTO();
        questionSentId.setQuestionId(questionId);
        final var questionIdSaved = questionSentIdService.save(questionSentId);
        answer.setQuestion(questionIdSaved);

        final var userMail = SecurityContextHolder.getContext().getAuthentication().getName();

        final var userMailDto = new UserEmailDTO();
        userMailDto.setMail(userMail);

        answer.setUserEmail(userEmailService.save(userMailDto));

        final var savedAnswer = answerService.save(answer);

        final var tagChoicesListedEventDTO = new TagChoicesListedEventDTO();
        tagChoicesListedEventDTO.setAnswerCreated(savedAnswer);

        tagInfosDTOList.forEach(e-> {
            final var answersAvailable = new AvailableAnswerDTO();
            answersAvailable.setTagId(e.getTagId());
            answersAvailable.setTagChoicesListedEvent(tagChoicesListedEventDTO);
            availableAnswerService.save(answersAvailable);
        });


        return save(tagChoicesListedEventDTO);
    }
}
