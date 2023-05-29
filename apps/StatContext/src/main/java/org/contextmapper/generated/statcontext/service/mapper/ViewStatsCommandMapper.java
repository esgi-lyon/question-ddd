package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.StatisticSubjectQuestion;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectUser;
import org.contextmapper.generated.statcontext.domain.ViewStatsCommand;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectQuestionDTO;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectUserDTO;
import org.contextmapper.generated.statcontext.service.dto.ViewStatsCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ViewStatsCommand} and its DTO {@link ViewStatsCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewStatsCommandMapper extends EntityMapper<ViewStatsCommandDTO, ViewStatsCommand> {
    @Mapping(target = "user", source = "user", qualifiedByName = "statisticSubjectUserId")
    @Mapping(target = "question", source = "question", qualifiedByName = "statisticSubjectQuestionId")
    ViewStatsCommandDTO toDto(ViewStatsCommand s);

    @Named("statisticSubjectUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectUserDTO toDtoStatisticSubjectUserId(StatisticSubjectUser statisticSubjectUser);

    @Named("statisticSubjectQuestionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectQuestionDTO toDtoStatisticSubjectQuestionId(StatisticSubjectQuestion statisticSubjectQuestion);
}
