package org.contextmapper.generated.evaluationcontext.service;

import org.contextmapper.generated.evaluationcontext.domain.enumeration.Status;
import org.contextmapper.generated.evaluationcontext.repository.CheckAnswerCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.CheckAnswerCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.AnswerCheckedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.CheckAnswerCommandMapper;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluatedAnswerMapper;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluationMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

@Primary
@Service
@Transactional
public class CheckAnswerCommandHandler extends CheckAnswerCommandService {
    private final Logger log = LoggerFactory.getLogger(CheckAnswerCommandHandler.class);

    private final EvaluationService evaluationService;

    private final AnswerCheckedEventService answerCheckedEventService;

    public CheckAnswerCommandHandler(
        CheckAnswerCommandRepository checkAnswerCommandRepository,
        EvaluationService evaluationService,
        AnswerCheckedEventService answerCheckedEventService,
        CheckAnswerCommandMapper checkAnswerCommandMapper
    ) {
        super(checkAnswerCommandRepository, checkAnswerCommandMapper);
        this.evaluationService = evaluationService;
        this.answerCheckedEventService = answerCheckedEventService;
    }

    public CheckAnswerCommandDTO handleCheckAnswerCommand(CheckAnswerCommandDTO checkAnswerCommand) {
        log.info("Handle command to check answer");
        final var evaluationDTO = new EvaluationDTO();

        // Api clients call to recover question from send question context
        //evaluationDTO.setUser();
        // evaluationDTO.setQuestion();

        // here you might need to add business logic to determine the status (VALID or INVALID) of the answer
        // for simplicity, let's just update the status as VALID
        evaluationDTO.setStatus(Status.VALID);

        final var saved = evaluationService.save(evaluationDTO);

        final var answerCheckedEventDTO = new AnswerCheckedEventDTO();
        evaluationDTO.setId(saved.getId());
        answerCheckedEventDTO.setAnswer(checkAnswerCommand.getAnswer());
        answerCheckedEventService.save(answerCheckedEventDTO);

        return save(checkAnswerCommand);
    }
}
