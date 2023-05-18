package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.StatisticSubjectQuestion;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectTag;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectUser;
import org.contextmapper.generated.statcontext.domain.ViewStats;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectQuestionDTO;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectTagDTO;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectUserDTO;
import org.contextmapper.generated.statcontext.service.dto.ViewStatsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ViewStats} and its DTO {@link ViewStatsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewStatsMapper extends EntityMapper<ViewStatsDTO, ViewStats> {
    @Mapping(target = "user", source = "user", qualifiedByName = "statisticSubjectUserId")
    @Mapping(target = "question", source = "question", qualifiedByName = "statisticSubjectQuestionId")
    @Mapping(target = "tag", source = "tag", qualifiedByName = "statisticSubjectTagId")
    ViewStatsDTO toDto(ViewStats s);

    @Named("statisticSubjectUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectUserDTO toDtoStatisticSubjectUserId(StatisticSubjectUser statisticSubjectUser);

    @Named("statisticSubjectQuestionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectQuestionDTO toDtoStatisticSubjectQuestionId(StatisticSubjectQuestion statisticSubjectQuestion);

    @Named("statisticSubjectTagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectTagDTO toDtoStatisticSubjectTagId(StatisticSubjectTag statisticSubjectTag);
}
