package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.AwardPointForEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.domain.Evaluation;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardPointForEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AwardPointForEvaluationCommand} and its DTO {@link AwardPointForEvaluationCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface AwardPointForEvaluationCommandMapper
    extends EntityMapper<AwardPointForEvaluationCommandDTO, AwardPointForEvaluationCommand> {
    @Mapping(target = "evaluation", source = "evaluation", qualifiedByName = "evaluationId")
    AwardPointForEvaluationCommandDTO toDto(AwardPointForEvaluationCommand s);

    @Named("evaluationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationDTO toDtoEvaluationId(Evaluation evaluation);
}
