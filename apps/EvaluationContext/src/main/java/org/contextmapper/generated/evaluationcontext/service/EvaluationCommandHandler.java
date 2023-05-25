package org.contextmapper.generated.evaluationcontext.service;

import org.contextmapper.generated.evaluationcontext.domain.CreateEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.repository.CreateEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationCreatedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluationMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

@Primary
@Service
@Transactional
public class EvaluationCommandHandler extends CreateEvaluationCommandService {
    private final Logger log = LoggerFactory.getLogger(EvaluationCommandHandler.class);

    private final EvaluationService evaluationService;

    private final EvaluationCreatedEventService evaluationCreatedEventService;

    private final EvaluationMapper evaluationMapper;

    public EvaluationCommandHandler(
        CreateEvaluationCommandRepository createEvaluationCommandRepository,
        EvaluationService evaluationService,
        EvaluationCreatedEventService evaluationCreatedEventService,
        EvaluationMapper evaluationMapper
    ) {
        super(createEvaluationCommandRepository);
        this.evaluationService = evaluationService;
        this.evaluationCreatedEventService = evaluationCreatedEventService;
        this.evaluationMapper = evaluationMapper;
    }

    public CreateEvaluationCommand handleCreateEvaluationCommand(EvaluationDTO evaluationDTO) {
        log.info("Handle command to create evaluation");
        CreateEvaluationCommand createEvaluationCommand = new CreateEvaluationCommand();

        final var saved = evaluationService.save(evaluationDTO);

        final var evaluationCreatedEventDTO = new EvaluationCreatedEventDTO();
        evaluationDTO.setId(saved.getId());
        evaluationCreatedEventDTO.setEvaluation(evaluationDTO);
        evaluationCreatedEventService.save(evaluationCreatedEventDTO);

        return save(
            createEvaluationCommand//.answer(evaluationMapper.toEntity(saved.get))
        );
    }
}
