package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.CheckAnswer;
import org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer;
import org.contextmapper.generated.evaluationcontext.service.dto.CheckAnswerDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluatedAnswerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CheckAnswer} and its DTO {@link CheckAnswerDTO}.
 */
@Mapper(componentModel = "spring")
public interface CheckAnswerMapper extends EntityMapper<CheckAnswerDTO, CheckAnswer> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "evaluatedAnswerId")
    CheckAnswerDTO toDto(CheckAnswer s);

    @Named("evaluatedAnswerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluatedAnswerDTO toDtoEvaluatedAnswerId(EvaluatedAnswer evaluatedAnswer);
}
