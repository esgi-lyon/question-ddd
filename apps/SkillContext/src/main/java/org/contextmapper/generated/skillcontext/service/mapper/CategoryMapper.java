package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.Category;
import org.contextmapper.generated.skillcontext.service.dto.CategoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {}
