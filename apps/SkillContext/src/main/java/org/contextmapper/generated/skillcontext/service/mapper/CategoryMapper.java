package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.Category;
import org.contextmapper.generated.skillcontext.domain.CategoryId;
import org.contextmapper.generated.skillcontext.service.dto.CategoryDTO;
import org.contextmapper.generated.skillcontext.service.dto.CategoryIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {
    @Mapping(target = "identifier", source = "identifier", qualifiedByName = "categoryIdId")
    CategoryDTO toDto(Category s);

    @Named("categoryIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryIdDTO toDtoCategoryIdId(CategoryId categoryId);
}
