package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.EvaluationQuestion;
import org.contextmapper.generated.gateway.service.dto.EvaluationQuestionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EvaluationQuestion} and its DTO {@link EvaluationQuestionDTO}.
 */
@Mapper(componentModel = "spring")
public interface EvaluationQuestionMapper extends EntityMapper<EvaluationQuestionDTO, EvaluationQuestion> {}
