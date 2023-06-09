package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.AnswerCheckedEvent;
import org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer;
import org.contextmapper.generated.evaluationcontext.service.dto.AnswerCheckedEventDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluatedAnswerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnswerCheckedEvent} and its DTO {@link AnswerCheckedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnswerCheckedEventMapper extends EntityMapper<AnswerCheckedEventDTO, AnswerCheckedEvent> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "evaluatedAnswerId")
    AnswerCheckedEventDTO toDto(AnswerCheckedEvent s);

    @Named("evaluatedAnswerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluatedAnswerDTO toDtoEvaluatedAnswerId(EvaluatedAnswer evaluatedAnswer);
}
