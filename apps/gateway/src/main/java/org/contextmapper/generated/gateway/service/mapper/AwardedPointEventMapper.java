package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.AwardedPointEvent;
import org.contextmapper.generated.gateway.domain.EvaluatedAnswer;
import org.contextmapper.generated.gateway.service.dto.AwardedPointEventDTO;
import org.contextmapper.generated.gateway.service.dto.EvaluatedAnswerDTO;
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
