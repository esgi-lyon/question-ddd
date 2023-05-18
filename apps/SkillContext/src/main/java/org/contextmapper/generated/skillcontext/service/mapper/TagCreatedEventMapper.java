package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.TagCreatedEvent;
import org.contextmapper.generated.skillcontext.domain.TagInfos;
import org.contextmapper.generated.skillcontext.service.dto.TagCreatedEventDTO;
import org.contextmapper.generated.skillcontext.service.dto.TagInfosDTO;
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
