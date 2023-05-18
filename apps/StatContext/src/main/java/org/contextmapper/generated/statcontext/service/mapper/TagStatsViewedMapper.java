package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.StatisticSubjectTag;
import org.contextmapper.generated.statcontext.domain.TagStatsViewed;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectTagDTO;
import org.contextmapper.generated.statcontext.service.dto.TagStatsViewedDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagStatsViewed} and its DTO {@link TagStatsViewedDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagStatsViewedMapper extends EntityMapper<TagStatsViewedDTO, TagStatsViewed> {
    @Mapping(target = "tag", source = "tag", qualifiedByName = "statisticSubjectTagId")
    TagStatsViewedDTO toDto(TagStatsViewed s);

    @Named("statisticSubjectTagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectTagDTO toDtoStatisticSubjectTagId(StatisticSubjectTag statisticSubjectTag);
}
