package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.CategoryCreatedEvent;
import org.contextmapper.generated.skillcontext.domain.CategoryId;
import org.contextmapper.generated.skillcontext.service.dto.CategoryCreatedEventDTO;
import org.contextmapper.generated.skillcontext.service.dto.CategoryIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategoryCreatedEvent} and its DTO {@link CategoryCreatedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryCreatedEventMapper extends EntityMapper<CategoryCreatedEventDTO, CategoryCreatedEvent> {
    @Mapping(target = "categoryId", source = "categoryId", qualifiedByName = "categoryIdId")
    CategoryCreatedEventDTO toDto(CategoryCreatedEvent s);

    @Named("categoryIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryIdDTO toDtoCategoryIdId(CategoryId categoryId);
}
