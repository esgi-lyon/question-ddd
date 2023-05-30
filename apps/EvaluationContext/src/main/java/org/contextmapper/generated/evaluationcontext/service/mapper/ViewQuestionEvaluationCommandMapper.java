package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.EvaluationQuestion;
import org.contextmapper.generated.evaluationcontext.domain.ViewQuestionEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationQuestionDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.ViewQuestionEvaluationCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ViewQuestionEvaluationCommand} and its DTO {@link ViewQuestionEvaluationCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewQuestionEvaluationCommandMapper extends EntityMapper<ViewQuestionEvaluationCommandDTO, ViewQuestionEvaluationCommand> {
    @Mapping(target = "question", source = "question", qualifiedByName = "evaluationQuestionId")
    ViewQuestionEvaluationCommandDTO toDto(ViewQuestionEvaluationCommand s);

    @Named("evaluationQuestionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationQuestionDTO toDtoEvaluationQuestionId(EvaluationQuestion evaluationQuestion);
}
