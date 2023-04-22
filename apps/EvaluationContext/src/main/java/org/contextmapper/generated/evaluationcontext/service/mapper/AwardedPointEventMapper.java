package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.AwardedPointEvent;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardedPointEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AwardedPointEvent} and its DTO {@link AwardedPointEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface AwardedPointEventMapper extends EntityMapper<AwardedPointEventDTO, AwardedPointEvent> {}
