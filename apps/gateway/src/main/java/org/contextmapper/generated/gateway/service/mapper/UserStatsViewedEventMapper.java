package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.StatisticSubjectUser;
import org.contextmapper.generated.gateway.domain.UserStatsViewedEvent;
import org.contextmapper.generated.gateway.service.dto.StatisticSubjectUserDTO;
import org.contextmapper.generated.gateway.service.dto.UserStatsViewedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserStatsViewedEvent} and its DTO {@link UserStatsViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserStatsViewedEventMapper extends EntityMapper<UserStatsViewedEventDTO, UserStatsViewedEvent> {
    @Mapping(target = "user", source = "user", qualifiedByName = "statisticSubjectUserId")
    UserStatsViewedEventDTO toDto(UserStatsViewedEvent s);

    @Named("statisticSubjectUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectUserDTO toDtoStatisticSubjectUserId(StatisticSubjectUser statisticSubjectUser);
}
