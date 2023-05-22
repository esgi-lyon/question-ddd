package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.CategoryCreatedEvent;
import org.contextmapper.generated.gateway.domain.CategoryId;
import org.contextmapper.generated.gateway.service.dto.CategoryCreatedEventDTO;
import org.contextmapper.generated.gateway.service.dto.CategoryIdDTO;
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
