package org.contextmapper.generated.evaluationcontext.service;

import org.contextmapper.generated.evaluationcontext.client.answercontext.api.AnswerResourceApi;
import org.contextmapper.generated.evaluationcontext.client.answercontext.api.UserEmailResourceApi;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.Status;
import org.contextmapper.generated.evaluationcontext.repository.CreateEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.*;
import org.contextmapper.generated.evaluationcontext.service.mapper.CreateEvaluationCommandMapper;
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
public class CreateEvaluationCommandHandler extends CreateEvaluationCommandService {
    private final Logger log = LoggerFactory.getLogger(CreateEvaluationCommandHandler.class);

    private final EvaluationService evaluationService;

    private final EvaluationCreatedEventService evaluationCreatedEventService;

    private final AnsweringUserService answeringUserService;

    private final AnswerResourceApi answerResourceApi;

    private final UserEmailResourceApi userEmailResourceApi;

    private final EvaluationQuestionService evaluationQuestionService;

    public CreateEvaluationCommandHandler(
        CreateEvaluationCommandRepository createEvaluationCommandRepository,
        EvaluationService evaluationService,
        EvaluationCreatedEventService evaluationCreatedEventService,
        CreateEvaluationCommandMapper createEvaluationCommandMapper,
        AnsweringUserService answeringUserService,
        AnswerResourceApi answerResourceApi,
        EvaluationQuestionService evaluationQuestionService,
        UserEmailResourceApi userEmailResourceApi
    ) {
        super(createEvaluationCommandRepository, createEvaluationCommandMapper);
        this.evaluationService = evaluationService;
        this.evaluationCreatedEventService = evaluationCreatedEventService;
        this.answeringUserService = answeringUserService;
        this.answerResourceApi = answerResourceApi;
        this.evaluationQuestionService = evaluationQuestionService;
        this.userEmailResourceApi = userEmailResourceApi;
    }

    public EvaluationCreatedEventDTO handleCreateEvaluationCommand(CreateEvaluationCommandDTO evaluationCommandDTO) {
        log.info("Handle command to create evaluation");
        final var createEvaluation = new EvaluationDTO();

        final var answerId = evaluationCommandDTO.getAnswer().getAnswerId();
        final var evaluatorEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        final var answer = Optional.ofNullable(answerResourceApi.getAnswer(answerId).getBody()).orElseThrow();

        final var user =  new AnsweringUserDTO();
        final var userEmail = Optional.ofNullable(
            userEmailResourceApi.getUserEmail(answer.getUserEmail().getId()).getBody()
        ).orElseThrow();

        user.setMail(userEmail.getMail());

        final var savedEvaluationUser = answeringUserService.save(user);
        createEvaluation.setUser(savedEvaluationUser);

        final var evaluationQuestion = new EvaluationQuestionDTO();
        evaluationQuestion.setQuestionId(answer.getQuestion().getQuestionId());
        final var savedEvaluationQuestion = evaluationQuestionService.save(evaluationQuestion);

        createEvaluation.setQuestion(savedEvaluationQuestion);
        createEvaluation.setStatus(Status.OPENED);
        createEvaluation.setAnsweredQuestionDifficultyLevel(evaluationCommandDTO.getDifficultyLevel());
        createEvaluation.setEvaluatorMail(evaluatorEmail);

        final var saved = evaluationService.save(createEvaluation);

        final var evaluationCreatedEventDTO = new EvaluationCreatedEventDTO();
        evaluationCommandDTO.setId(saved.getId());
        evaluationCreatedEventDTO.setEvaluation(saved);
        return evaluationCreatedEventService.save(evaluationCreatedEventDTO);
    }

}
