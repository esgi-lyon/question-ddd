package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.EvaluationId;
import org.contextmapper.generated.statcontext.domain.EvaluationStats;
import org.contextmapper.generated.statcontext.service.dto.EvaluationIdDTO;
import org.contextmapper.generated.statcontext.service.dto.EvaluationStatsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EvaluationId} and its DTO {@link EvaluationIdDTO}.
 */
@Mapper(componentModel = "spring")
public interface EvaluationIdMapper extends EntityMapper<EvaluationIdDTO, EvaluationId> {
    @Mapping(target = "evaluationStats", source = "evaluationStats", qualifiedByName = "evaluationStatsId")
    EvaluationIdDTO toDto(EvaluationId s);

    @Named("evaluationStatsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationStatsDTO toDtoEvaluationStatsId(EvaluationStats evaluationStats);
}
