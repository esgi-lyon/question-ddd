package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.CategoryCreatedEvent;
import org.contextmapper.generated.skillcontext.service.dto.CategoryCreatedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategoryCreatedEvent} and its DTO {@link CategoryCreatedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryCreatedEventMapper extends EntityMapper<CategoryCreatedEventDTO, CategoryCreatedEvent> {}
