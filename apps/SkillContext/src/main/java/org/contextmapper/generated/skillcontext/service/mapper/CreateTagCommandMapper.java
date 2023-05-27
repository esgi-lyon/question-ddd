package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.CreateTagCommand;
import org.contextmapper.generated.skillcontext.domain.Tag;
import org.contextmapper.generated.skillcontext.service.dto.CreateTagCommandDTO;
import org.contextmapper.generated.skillcontext.service.dto.TagDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreateTagCommand} and its DTO {@link CreateTagCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreateTagCommandMapper extends EntityMapper<CreateTagCommandDTO, CreateTagCommand> {
    @Mapping(target = "tag", source = "tag", qualifiedByName = "tagId")
    CreateTagCommandDTO toDto(CreateTagCommand s);

    @Named("tagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TagDTO toDtoTagId(Tag tag);
}
