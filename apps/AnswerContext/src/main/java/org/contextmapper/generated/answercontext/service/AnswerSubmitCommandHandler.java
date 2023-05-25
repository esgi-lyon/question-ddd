package org.contextmapper.generated.answercontext.service;

import org.contextmapper.generated.answercontext.domain.AnswerSubmitCommand;
import org.contextmapper.generated.answercontext.repository.AnswerSubmitCommandRepository;
import org.contextmapper.generated.answercontext.service.dto.AnswerDTO;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmittedEventDTO;
import org.contextmapper.generated.answercontext.service.mapper.AnswerMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

@Primary
@Service
@Transactional
public class AnswerSubmitCommandHandler extends AnswerSubmitCommandService {
    private final Logger log = LoggerFactory.getLogger(AnswerSubmitCommandHandler.class);

    private final AnswerService answerService;

    private final AnswerSubmittedEventService answerSubmittedEventService;

    private final AnswerMapper answerMapper;

    public AnswerSubmitCommandHandler(
        AnswerSubmitCommandRepository answerSubmitCommandRepository,
        AnswerService answerService,
        AnswerSubmittedEventService answerSubmittedEventService,
        AnswerMapper answerMapper
    ) {
        super(answerSubmitCommandRepository);
        this.answerService = answerService;
        this.answerSubmittedEventService = answerSubmittedEventService;
        this.answerMapper = answerMapper;
    }

    public AnswerSubmitCommand handleAnswerSubmitCommand(AnswerDTO answerDTO) {
        log.info("Handle command to submit answer");
        AnswerSubmitCommand answerSubmitCommand = new AnswerSubmitCommand();

        final var saved = answerService.save(answerDTO);

        final var answerSubmittedEventDTO = new AnswerSubmittedEventDTO();
        answerDTO.setId(saved.getId());
        answerSubmittedEventDTO.setAnswer(answerDTO);
        answerSubmittedEventService.save(answerSubmittedEventDTO);

        return save(
            answerSubmitCommand.answer(answerMapper.toEntity(saved))
        );
    }
}
