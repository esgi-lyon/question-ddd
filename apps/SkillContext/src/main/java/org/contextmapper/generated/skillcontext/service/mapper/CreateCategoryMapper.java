package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.Category;
import org.contextmapper.generated.skillcontext.domain.CreateCategory;
import org.contextmapper.generated.skillcontext.service.dto.CategoryDTO;
import org.contextmapper.generated.skillcontext.service.dto.CreateCategoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreateCategory} and its DTO {@link CreateCategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreateCategoryMapper extends EntityMapper<CreateCategoryDTO, CreateCategory> {
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryId")
    CreateCategoryDTO toDto(CreateCategory s);

    @Named("categoryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryDTO toDtoCategoryId(Category category);
}
