package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.AnswerChecked;
import org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer;
import org.contextmapper.generated.evaluationcontext.service.dto.AnswerCheckedDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluatedAnswerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnswerChecked} and its DTO {@link AnswerCheckedDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnswerCheckedMapper extends EntityMapper<AnswerCheckedDTO, AnswerChecked> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "evaluatedAnswerId")
    AnswerCheckedDTO toDto(AnswerChecked s);

    @Named("evaluatedAnswerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluatedAnswerDTO toDtoEvaluatedAnswerId(EvaluatedAnswer evaluatedAnswer);
}
