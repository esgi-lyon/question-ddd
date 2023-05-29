package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.EvaluationStats;
import org.contextmapper.generated.statcontext.service.dto.EvaluationStatsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EvaluationStats} and its DTO {@link EvaluationStatsDTO}.
 */
@Mapper(componentModel = "spring")
public interface EvaluationStatsMapper extends EntityMapper<EvaluationStatsDTO, EvaluationStats> {}
