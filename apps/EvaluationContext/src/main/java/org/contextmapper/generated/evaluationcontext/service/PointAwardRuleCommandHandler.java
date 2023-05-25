package org.contextmapper.generated.evaluationcontext.service;

import org.contextmapper.generated.evaluationcontext.domain.AwardPointForEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.repository.AwardPointForEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardedPointEventDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluationMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

@Primary
@Service
@Transactional
public class PointAwardRuleCommandHandler extends AwardPointForEvaluationCommandService {
    private final Logger log = LoggerFactory.getLogger(PointAwardRuleCommandHandler.class);

    private final EvaluationService evaluationService;

    private final AwardedPointEventService awardedPointEventService;

    private final EvaluationMapper evaluationMapper;

    public PointAwardRuleCommandHandler(
        AwardPointForEvaluationCommandRepository awardPointForEvaluationCommandRepository,
        EvaluationService evaluationService,
        AwardedPointEventService awardedPointEventService,
        EvaluationMapper evaluationMapper
    ) {
        super(awardPointForEvaluationCommandRepository);
        this.evaluationService = evaluationService;
        this.awardedPointEventService = awardedPointEventService;
        this.evaluationMapper = evaluationMapper;
    }

    public AwardPointForEvaluationCommand handleAwardPointForEvaluationCommand(EvaluationDTO evaluationDTO) {
        log.info("Handle command to award points for evaluation");
        AwardPointForEvaluationCommand awardPointForEvaluationCommand = new AwardPointForEvaluationCommand();

        final var saved = evaluationService.save(evaluationDTO);

        final var awardedPointEventDTO = new AwardedPointEventDTO();
        evaluationDTO.setId(saved.getId());
        // awardedPointEventDTO.setAnswer(evaluationDTO.getAnswer());
        awardedPointEventService.save(awardedPointEventDTO);

        return save(
            awardPointForEvaluationCommand.evaluation(evaluationMapper.toEntity(saved))
        );
    }
}
