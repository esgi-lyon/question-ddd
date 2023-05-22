package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.StatisticSubjectTag;
import org.contextmapper.generated.gateway.domain.TagStatsViewedEvent;
import org.contextmapper.generated.gateway.service.dto.StatisticSubjectTagDTO;
import org.contextmapper.generated.gateway.service.dto.TagStatsViewedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagStatsViewedEvent} and its DTO {@link TagStatsViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagStatsViewedEventMapper extends EntityMapper<TagStatsViewedEventDTO, TagStatsViewedEvent> {
    @Mapping(target = "tag", source = "tag", qualifiedByName = "statisticSubjectTagId")
    TagStatsViewedEventDTO toDto(TagStatsViewedEvent s);

    @Named("statisticSubjectTagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectTagDTO toDtoStatisticSubjectTagId(StatisticSubjectTag statisticSubjectTag);
}
