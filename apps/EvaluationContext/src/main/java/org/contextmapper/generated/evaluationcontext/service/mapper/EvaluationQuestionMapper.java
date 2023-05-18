package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.EvaluationQuestion;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationQuestionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EvaluationQuestion} and its DTO {@link EvaluationQuestionDTO}.
 */
@Mapper(componentModel = "spring")
public interface EvaluationQuestionMapper extends EntityMapper<EvaluationQuestionDTO, EvaluationQuestion> {}
