package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.AwardPointForEvaluation;
import org.contextmapper.generated.evaluationcontext.domain.Evaluation;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardPointForEvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AwardPointForEvaluation} and its DTO {@link AwardPointForEvaluationDTO}.
 */
@Mapper(componentModel = "spring")
public interface AwardPointForEvaluationMapper extends EntityMapper<AwardPointForEvaluationDTO, AwardPointForEvaluation> {
    @Mapping(target = "evaluation", source = "evaluation", qualifiedByName = "evaluationId")
    AwardPointForEvaluationDTO toDto(AwardPointForEvaluation s);

    @Named("evaluationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationDTO toDtoEvaluationId(Evaluation evaluation);
}
