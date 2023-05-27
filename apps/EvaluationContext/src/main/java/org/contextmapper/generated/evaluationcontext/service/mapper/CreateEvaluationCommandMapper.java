package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.CreateEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer;
import org.contextmapper.generated.evaluationcontext.service.dto.CreateEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluatedAnswerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreateEvaluationCommand} and its DTO {@link CreateEvaluationCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreateEvaluationCommandMapper extends EntityMapper<CreateEvaluationCommandDTO, CreateEvaluationCommand> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "evaluatedAnswerId")
    CreateEvaluationCommandDTO toDto(CreateEvaluationCommand s);

    @Named("evaluatedAnswerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluatedAnswerDTO toDtoEvaluatedAnswerId(EvaluatedAnswer evaluatedAnswer);
}
