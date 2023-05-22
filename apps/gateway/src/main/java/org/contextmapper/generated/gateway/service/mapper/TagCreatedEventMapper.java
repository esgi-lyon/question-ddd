package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.TagCreatedEvent;
import org.contextmapper.generated.gateway.domain.TagInfos;
import org.contextmapper.generated.gateway.service.dto.TagCreatedEventDTO;
import org.contextmapper.generated.gateway.service.dto.TagInfosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagCreatedEvent} and its DTO {@link TagCreatedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagCreatedEventMapper extends EntityMapper<TagCreatedEventDTO, TagCreatedEvent> {
    @Mapping(target = "tagId", source = "tagId", qualifiedByName = "tagInfosId")
    TagCreatedEventDTO toDto(TagCreatedEvent s);

    @Named("tagInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TagInfosDTO toDtoTagInfosId(TagInfos tagInfos);
}
