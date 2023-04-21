package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.CategoryId;
import org.contextmapper.generated.skillcontext.service.dto.CategoryIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategoryId} and its DTO {@link CategoryIdDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryIdMapper extends EntityMapper<CategoryIdDTO, CategoryId> {}
