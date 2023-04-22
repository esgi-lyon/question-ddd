package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.EvaluationCreatedEvent;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationCreatedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EvaluationCreatedEvent} and its DTO {@link EvaluationCreatedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface EvaluationCreatedEventMapper extends EntityMapper<EvaluationCreatedEventDTO, EvaluationCreatedEvent> {}
