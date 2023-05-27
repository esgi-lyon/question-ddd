package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.Category;
import org.contextmapper.generated.skillcontext.domain.CreateCategoryCommand;
import org.contextmapper.generated.skillcontext.service.dto.CategoryDTO;
import org.contextmapper.generated.skillcontext.service.dto.CreateCategoryCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreateCategoryCommand} and its DTO {@link CreateCategoryCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreateCategoryCommandMapper extends EntityMapper<CreateCategoryCommandDTO, CreateCategoryCommand> {
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryId")
    CreateCategoryCommandDTO toDto(CreateCategoryCommand s);

    @Named("categoryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryDTO toDtoCategoryId(Category category);
}
