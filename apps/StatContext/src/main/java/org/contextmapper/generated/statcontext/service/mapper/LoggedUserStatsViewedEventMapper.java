package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.EvaluationStats;
import org.contextmapper.generated.statcontext.domain.LoggedUserStatsViewedEvent;
import org.contextmapper.generated.statcontext.service.dto.EvaluationStatsDTO;
import org.contextmapper.generated.statcontext.service.dto.LoggedUserStatsViewedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LoggedUserStatsViewedEvent} and its DTO {@link LoggedUserStatsViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface LoggedUserStatsViewedEventMapper extends EntityMapper<LoggedUserStatsViewedEventDTO, LoggedUserStatsViewedEvent> {
    @Mapping(target = "stat", source = "stat", qualifiedByName = "evaluationStatsId")
    LoggedUserStatsViewedEventDTO toDto(LoggedUserStatsViewedEvent s);

    @Named("evaluationStatsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationStatsDTO toDtoEvaluationStatsId(EvaluationStats evaluationStats);
}
