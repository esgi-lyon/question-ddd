package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.QuestionStatsViewedEvent;
import org.contextmapper.generated.gateway.domain.StatisticSubjectQuestion;
import org.contextmapper.generated.gateway.service.dto.QuestionStatsViewedEventDTO;
import org.contextmapper.generated.gateway.service.dto.StatisticSubjectQuestionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionStatsViewedEvent} and its DTO {@link QuestionStatsViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionStatsViewedEventMapper extends EntityMapper<QuestionStatsViewedEventDTO, QuestionStatsViewedEvent> {
    @Mapping(target = "question", source = "question", qualifiedByName = "statisticSubjectQuestionId")
    QuestionStatsViewedEventDTO toDto(QuestionStatsViewedEvent s);

    @Named("statisticSubjectQuestionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectQuestionDTO toDtoStatisticSubjectQuestionId(StatisticSubjectQuestion statisticSubjectQuestion);
}
