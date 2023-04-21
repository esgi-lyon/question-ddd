package org.contextmapper.generated.skillcontext.service.mapper;

import org.contextmapper.generated.skillcontext.domain.TagId;
import org.contextmapper.generated.skillcontext.service.dto.TagIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagId} and its DTO {@link TagIdDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagIdMapper extends EntityMapper<TagIdDTO, TagId> {}
