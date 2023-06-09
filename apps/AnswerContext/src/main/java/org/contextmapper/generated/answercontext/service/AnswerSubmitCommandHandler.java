package org.contextmapper.generated.answercontext.service;

import org.contextmapper.generated.answercontext.domain.enumeration.AnswerState;
import org.contextmapper.generated.answercontext.repository.AnswerSubmittedEventRepository;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmitCommandDTO;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmittedEventDTO;
import org.contextmapper.generated.answercontext.service.dto.UserEmailDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnswerSubmittedEventMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

@Primary
@Service
@Transactional
public class AnswerSubmitCommandHandler extends AnswerSubmittedEventService {
    private final Logger log = LoggerFactory.getLogger(AnswerSubmitCommandHandler.class);

    private final AnswerService answerService;
    private final UserEmailService userEmailService;

    public AnswerSubmitCommandHandler(
        AnswerSubmittedEventRepository answerSubmitCommandRepository,
        AnswerService answerService,
        AnswerSubmittedEventMapper answerSubmittedEventMapper,
        UserEmailService userEmailService) {
        super(answerSubmitCommandRepository, answerSubmittedEventMapper);
        this.answerService = answerService;
        this.userEmailService = userEmailService;
    }

    public AnswerSubmittedEventDTO handleAnswerSubmitCommand(AnswerSubmitCommandDTO answerCmd) {
        log.info("Handle command to submit answer");

        final var answer = answerService.findOne(answerCmd.getAnswer().getId()).orElseThrow();

        answer.setAnswerState(AnswerState.DONE);
        answer.setAnsweredTag(answer.getAnsweredTag());

        final var userEmailDto = new UserEmailDTO();
        final var mail = SecurityContextHolder.getContext().getAuthentication().getName();
        userEmailDto.setMail(mail);

        answer.setUserEmail(userEmailService.save(userEmailDto));

        final var savedAnswer = answerService.save(answer);

        final var answerSubmittedEventDTO = new AnswerSubmittedEventDTO();
        answerSubmittedEventDTO.setAnswer(savedAnswer);

        return save(answerSubmittedEventDTO);
    }
}
