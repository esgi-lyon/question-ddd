package org.contextmapper.generated.answercontext.service;

import org.contextmapper.generated.answercontext.domain.AnswerSubmitCommand;
import org.contextmapper.generated.answercontext.domain.AnswerSubmittedEvent;
import org.contextmapper.generated.answercontext.domain.enumeration.AnswerState;
import org.contextmapper.generated.answercontext.repository.AnswerSubmitCommandRepository;
import org.contextmapper.generated.answercontext.repository.AnswerSubmittedEventRepository;
import org.contextmapper.generated.answercontext.service.dto.AnswerDTO;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmitCommandDTO;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmittedEventDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnswerMapper;
import org.contextmapper.generated.answercontext.service.mapper.AnswerSubmittedEventMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

@Primary
@Service
@Transactional
public class AnswerSubmitCommandHandler extends AnswerSubmittedEventService {
    private final Logger log = LoggerFactory.getLogger(AnswerSubmitCommandHandler.class);

    private final AnswerService answerService;

    private final AnswerSubmittedEventService answerSubmittedEventService;

    public AnswerSubmitCommandHandler(
        AnswerSubmittedEventRepository answerSubmitCommandRepository,
        AnswerService answerService,
        AnswerSubmittedEventService answerSubmittedEventService,
        AnswerSubmittedEventMapper answerSubmittedEventMapper
    ) {
        super(answerSubmitCommandRepository, answerSubmittedEventMapper);
        this.answerService = answerService;
        this.answerSubmittedEventService = answerSubmittedEventService;
    }

    public AnswerSubmittedEventDTO handleAnswerSubmitCommand(AnswerSubmitCommandDTO answerCmd) {
        log.info("Handle command to submit answer");

        final var answer = answerService.findOne(answerCmd.getAnswer().getId()).orElseThrow();

        answer.setAnswerState(AnswerState.DONE);

        final var answerSubmittedEventDTO = new AnswerSubmittedEventDTO();
        answerSubmittedEventDTO.setAnswer(answerCmd.getAnswer());
        answerSubmittedEventService.save(answerSubmittedEventDTO);

        return save(
            answerSubmittedEventService.save(answerSubmittedEventDTO)
        );
    }
}
