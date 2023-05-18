package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.CategoryCreated;
import org.contextmapper.generated.skillcontext.domain.CategoryId;
import org.contextmapper.generated.skillcontext.service.dto.CategoryCreatedDTO;
import org.contextmapper.generated.skillcontext.service.dto.CategoryIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategoryCreated} and its DTO {@link CategoryCreatedDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryCreatedMapper extends EntityMapper<CategoryCreatedDTO, CategoryCreated> {
    @Mapping(target = "categoryId", source = "categoryId", qualifiedByName = "categoryIdId")
    CategoryCreatedDTO toDto(CategoryCreated s);

    @Named("categoryIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryIdDTO toDtoCategoryIdId(CategoryId categoryId);
}
