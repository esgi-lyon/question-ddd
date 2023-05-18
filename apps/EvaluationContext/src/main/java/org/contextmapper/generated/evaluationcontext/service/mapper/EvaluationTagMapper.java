package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.EvaluationTag;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationTagDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EvaluationTag} and its DTO {@link EvaluationTagDTO}.
 */
@Mapper(componentModel = "spring")
public interface EvaluationTagMapper extends EntityMapper<EvaluationTagDTO, EvaluationTag> {}
