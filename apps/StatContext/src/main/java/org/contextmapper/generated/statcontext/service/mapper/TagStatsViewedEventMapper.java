package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.TagStatsViewedEvent;
import org.contextmapper.generated.statcontext.service.dto.TagStatsViewedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagStatsViewedEvent} and its DTO {@link TagStatsViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagStatsViewedEventMapper extends EntityMapper<TagStatsViewedEventDTO, TagStatsViewedEvent> {}
