package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.TagInfos;
import org.contextmapper.generated.skillcontext.service.dto.TagInfosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagInfos} and its DTO {@link TagInfosDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagInfosMapper extends EntityMapper<TagInfosDTO, TagInfos> {}
