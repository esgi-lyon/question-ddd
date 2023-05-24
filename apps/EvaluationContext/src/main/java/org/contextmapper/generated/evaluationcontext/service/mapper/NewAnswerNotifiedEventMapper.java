package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer;
import org.contextmapper.generated.evaluationcontext.domain.NewAnswerNotifiedEvent;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluatedAnswerDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.NewAnswerNotifiedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NewAnswerNotifiedEvent} and its DTO {@link NewAnswerNotifiedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface NewAnswerNotifiedEventMapper extends EntityMapper<NewAnswerNotifiedEventDTO, NewAnswerNotifiedEvent> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "evaluatedAnswerId")
    NewAnswerNotifiedEventDTO toDto(NewAnswerNotifiedEvent s);

    @Named("evaluatedAnswerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluatedAnswerDTO toDtoEvaluatedAnswerId(EvaluatedAnswer evaluatedAnswer);
}
