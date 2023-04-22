package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.TagCreatedEvent;
import org.contextmapper.generated.skillcontext.service.dto.TagCreatedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagCreatedEvent} and its DTO {@link TagCreatedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagCreatedEventMapper extends EntityMapper<TagCreatedEventDTO, TagCreatedEvent> {}
