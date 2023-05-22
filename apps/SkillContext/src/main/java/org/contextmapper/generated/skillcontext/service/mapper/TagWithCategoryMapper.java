package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.TagWithCategory;
import org.contextmapper.generated.skillcontext.service.dto.TagWithCategoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagWithCategory} and its DTO {@link TagWithCategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagWithCategoryMapper extends EntityMapper<TagWithCategoryDTO, TagWithCategory> {}
