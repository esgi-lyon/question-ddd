package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.UserStatsViewedEvent;
import org.contextmapper.generated.statcontext.service.dto.UserStatsViewedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserStatsViewedEvent} and its DTO {@link UserStatsViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserStatsViewedEventMapper extends EntityMapper<UserStatsViewedEventDTO, UserStatsViewedEvent> {}
