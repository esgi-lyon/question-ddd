package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.EvaluationStats;
import org.contextmapper.generated.statcontext.domain.QuestionStatsViewedEvent;
import org.contextmapper.generated.statcontext.service.dto.EvaluationStatsDTO;
import org.contextmapper.generated.statcontext.service.dto.QuestionStatsViewedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionStatsViewedEvent} and its DTO {@link QuestionStatsViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionStatsViewedEventMapper extends EntityMapper<QuestionStatsViewedEventDTO, QuestionStatsViewedEvent> {
    @Mapping(target = "stat", source = "stat", qualifiedByName = "evaluationStatsId")
    QuestionStatsViewedEventDTO toDto(QuestionStatsViewedEvent s);

    @Named("evaluationStatsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationStatsDTO toDtoEvaluationStatsId(EvaluationStats evaluationStats);
}
