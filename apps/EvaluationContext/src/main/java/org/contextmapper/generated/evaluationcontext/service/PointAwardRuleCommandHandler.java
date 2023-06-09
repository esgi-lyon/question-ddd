package org.contextmapper.generated.evaluationcontext.service;

import org.contextmapper.generated.evaluationcontext.domain.enumeration.UserLevel;
import org.contextmapper.generated.evaluationcontext.repository.AwardPointForEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardPointForEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardedPointEventDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.UserAndLevelDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.AwardPointForEvaluationCommandMapper;
import org.contextmapper.generated.evaluationcontext.service.mapper.EvaluationMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

import java.util.Objects;

@Primary
@Service
@Transactional
public class PointAwardRuleCommandHandler extends AwardPointForEvaluationCommandService {
    private final Logger log = LoggerFactory.getLogger(PointAwardRuleCommandHandler.class);

    private final EvaluationService evaluationService;

    private final AwardedPointEventService awardedPointEventService;

    private final UserAndLevelService userAndLevelService;

    public PointAwardRuleCommandHandler(
        AwardPointForEvaluationCommandRepository awardPointForEvaluationCommandRepository,
        EvaluationService evaluationService,
        AwardedPointEventService awardedPointEventService,
        AwardPointForEvaluationCommandMapper awardPointForEvaluationCommandMapper,
        UserAndLevelService userAndLevelService) {
        super(awardPointForEvaluationCommandRepository, awardPointForEvaluationCommandMapper);
        this.evaluationService = evaluationService;
        this.awardedPointEventService = awardedPointEventService;
        this.userAndLevelService = userAndLevelService;
    }

    public AwardPointForEvaluationCommandDTO handleAwardPointForEvaluationCommand(EvaluationDTO evaluationDTO) {
        log.info("Handle command to award points for evaluation");
        final var awardPointForEvaluationCommand = new AwardPointForEvaluationCommandDTO();

        final var evaluation = evaluationService.findOne(evaluationDTO.getId()).orElseThrow();

        final var userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        var nastyFindUser = userAndLevelService.findAll()
            .stream()
            .filter(e -> Objects.equals(e.getMail(), userEmail))
            .findFirst().orElseGet(() -> {
                final var newUserLvl = new UserAndLevelDTO();
                newUserLvl.setUserLevel(UserLevel.NEW);
                newUserLvl.setMail(userEmail);
                return userAndLevelService.save(newUserLvl);
            });

        if (evaluationDTO.getScore() > 1) {
            throw new RuntimeException("Evaluation score can't be > 1");
        }

        var scoreMultiplier = getScoreMultiplier(nastyFindUser.getUserLevel());
        evaluation.setScore(evaluationDTO.getScore() * scoreMultiplier);

        final var awardedPointEventDTO = new AwardedPointEventDTO();
        awardedPointEventService.save(awardedPointEventDTO);

        final var saved = evaluationService.save(evaluation);
        awardPointForEvaluationCommand.setEvaluation(saved);

        return save(awardPointForEvaluationCommand);
    }

    private int getScoreMultiplier(UserLevel userLevel) {
        switch(userLevel) {
            case REGULAR:
                return 2;
            case EXPERT:
                return 3;
            default:
                return 1;
        }
    }
}
