package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.Evaluation;
import org.contextmapper.generated.evaluationcontext.domain.EvaluationCreated;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationCreatedDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EvaluationCreated} and its DTO {@link EvaluationCreatedDTO}.
 */
@Mapper(componentModel = "spring")
public interface EvaluationCreatedMapper extends EntityMapper<EvaluationCreatedDTO, EvaluationCreated> {
    @Mapping(target = "evaluation", source = "evaluation", qualifiedByName = "evaluationId")
    EvaluationCreatedDTO toDto(EvaluationCreated s);

    @Named("evaluationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationDTO toDtoEvaluationId(Evaluation evaluation);
}
