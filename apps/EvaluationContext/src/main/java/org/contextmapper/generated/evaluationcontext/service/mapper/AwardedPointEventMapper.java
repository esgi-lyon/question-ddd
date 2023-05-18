package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.AwardedPointEvent;
import org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardedPointEventDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluatedAnswerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AwardedPointEvent} and its DTO {@link AwardedPointEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface AwardedPointEventMapper extends EntityMapper<AwardedPointEventDTO, AwardedPointEvent> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "evaluatedAnswerId")
    AwardedPointEventDTO toDto(AwardedPointEvent s);

    @Named("evaluatedAnswerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluatedAnswerDTO toDtoEvaluatedAnswerId(EvaluatedAnswer evaluatedAnswer);
}
