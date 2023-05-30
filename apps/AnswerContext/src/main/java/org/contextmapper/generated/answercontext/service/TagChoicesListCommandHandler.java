package org.contextmapper.generated.answercontext.service;

import org.contextmapper.generated.answercontext.client.sendquestioncontext.api.QueryHandlersApi;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Service
@Transactional
public class TagChoicesListCommandHandler extends TagChoicesListedEventService {
    private final Logger log = LoggerFactory.getLogger(TagChoicesListCommandHandler.class);

    private final AvailableAnswerService availableAnswerService;

    private final QueryHandlersApi queryHandler;

    private final QuestionSentIdService questionSentIdService;
    private final UserEmailService userEmailService;
    private final AnswerService answerService;

    public record NewAnswerDto(AnswerDTO answer, List<AvailableAnswerDTO> availableAnswerDTOS) {
    }

    public TagChoicesListCommandHandler(
        TagChoicesListedEventRepository tagChoicesListEventRepository,
        TagChoicesListedEventMapper tagChoicesListedEventMapper,
        AvailableAnswerService availableAnswerService,
        QueryHandlersApi queryHandlersApi,
        QuestionSentIdService questionSentIdService,
        UserEmailService userEmailService,
        AnswerService answerService
    ) {
        super(tagChoicesListEventRepository, tagChoicesListedEventMapper);
        this.availableAnswerService = availableAnswerService;
        this.queryHandler = queryHandlersApi;
        this.questionSentIdService = questionSentIdService;
        this.userEmailService = userEmailService;
        this.answerService = answerService;
    }

    public NewAnswerDto handleTagChoicesListCommand(Long question) {
        log.info("Handle command to list tag choices for question : {}", question);
        final var cmd = new TagChoicesListCommandDTO();
        final var questionSendDTO = new QuestionSentIdDTO();
        questionSendDTO.setQuestionId(question);
        cmd.setQuestionSent(questionSendDTO);
        // TODO not urgent save

        final var questionId = cmd.getQuestionSent().getQuestionId();
        final var tagInfosDTOList = Optional.ofNullable(queryHandler.handlePrepareQuestionsCommand1(questionId).getBody())
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

        final var available = tagInfosDTOList.stream().map(e -> {
            final var answersAvailable = new AvailableAnswerDTO();
            answersAvailable.setTagId(e.getTagId());
            return availableAnswerService.save(answersAvailable);
        }).toList();

        return new NewAnswerDto(savedAnswer, available);
    }
}
