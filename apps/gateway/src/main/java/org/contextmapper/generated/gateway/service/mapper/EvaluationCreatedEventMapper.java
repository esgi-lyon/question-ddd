package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.Evaluation;
import org.contextmapper.generated.gateway.domain.EvaluationCreatedEvent;
import org.contextmapper.generated.gateway.service.dto.EvaluationCreatedEventDTO;
import org.contextmapper.generated.gateway.service.dto.EvaluationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EvaluationCreatedEvent} and its DTO {@link EvaluationCreatedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface EvaluationCreatedEventMapper extends EntityMapper<EvaluationCreatedEventDTO, EvaluationCreatedEvent> {
    @Mapping(target = "evaluation", source = "evaluation", qualifiedByName = "evaluationId")
    EvaluationCreatedEventDTO toDto(EvaluationCreatedEvent s);

    @Named("evaluationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationDTO toDtoEvaluationId(Evaluation evaluation);
}
