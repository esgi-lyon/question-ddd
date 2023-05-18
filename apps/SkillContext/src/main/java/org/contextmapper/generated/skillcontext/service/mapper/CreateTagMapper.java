package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.CreateTag;
import org.contextmapper.generated.skillcontext.domain.Tag;
import org.contextmapper.generated.skillcontext.service.dto.CreateTagDTO;
import org.contextmapper.generated.skillcontext.service.dto.TagDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreateTag} and its DTO {@link CreateTagDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreateTagMapper extends EntityMapper<CreateTagDTO, CreateTag> {
    @Mapping(target = "tag", source = "tag", qualifiedByName = "tagId")
    CreateTagDTO toDto(CreateTag s);

    @Named("tagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TagDTO toDtoTagId(Tag tag);
}
