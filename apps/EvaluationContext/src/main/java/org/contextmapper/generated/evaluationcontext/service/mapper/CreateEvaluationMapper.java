package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.CreateEvaluation;
import org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer;
import org.contextmapper.generated.evaluationcontext.service.dto.CreateEvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluatedAnswerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreateEvaluation} and its DTO {@link CreateEvaluationDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreateEvaluationMapper extends EntityMapper<CreateEvaluationDTO, CreateEvaluation> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "evaluatedAnswerId")
    CreateEvaluationDTO toDto(CreateEvaluation s);

    @Named("evaluatedAnswerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluatedAnswerDTO toDtoEvaluatedAnswerId(EvaluatedAnswer evaluatedAnswer);
}
