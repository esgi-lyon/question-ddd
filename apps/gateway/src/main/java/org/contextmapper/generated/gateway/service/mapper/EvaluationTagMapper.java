package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.EvaluationTag;
import org.contextmapper.generated.gateway.service.dto.EvaluationTagDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EvaluationTag} and its DTO {@link EvaluationTagDTO}.
 */
@Mapper(componentModel = "spring")
public interface EvaluationTagMapper extends EntityMapper<EvaluationTagDTO, EvaluationTag> {}
