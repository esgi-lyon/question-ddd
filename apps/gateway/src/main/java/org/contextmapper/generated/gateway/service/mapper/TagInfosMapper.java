package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.TagInfos;
import org.contextmapper.generated.gateway.service.dto.TagInfosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagInfos} and its DTO {@link TagInfosDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagInfosMapper extends EntityMapper<TagInfosDTO, TagInfos> {}
