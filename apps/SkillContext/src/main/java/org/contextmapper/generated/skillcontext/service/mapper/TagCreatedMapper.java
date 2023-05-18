package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.TagCreated;
import org.contextmapper.generated.skillcontext.domain.TagInfos;
import org.contextmapper.generated.skillcontext.service.dto.TagCreatedDTO;
import org.contextmapper.generated.skillcontext.service.dto.TagInfosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagCreated} and its DTO {@link TagCreatedDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagCreatedMapper extends EntityMapper<TagCreatedDTO, TagCreated> {
    @Mapping(target = "tagId", source = "tagId", qualifiedByName = "tagInfosId")
    TagCreatedDTO toDto(TagCreated s);

    @Named("tagInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TagInfosDTO toDtoTagInfosId(TagInfos tagInfos);
}
