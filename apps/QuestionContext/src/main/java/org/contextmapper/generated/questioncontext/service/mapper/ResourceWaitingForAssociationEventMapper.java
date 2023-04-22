package org.contextmapper.generated.questioncontext.service.mapper;

import org.contextmapper.generated.questioncontext.domain.ResourceWaitingForAssociationEvent;
import org.contextmapper.generated.questioncontext.service.dto.ResourceWaitingForAssociationEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResourceWaitingForAssociationEvent} and its DTO {@link ResourceWaitingForAssociationEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResourceWaitingForAssociationEventMapper
    extends EntityMapper<ResourceWaitingForAssociationEventDTO, ResourceWaitingForAssociationEvent> {}
