package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.Evaluation;
import org.contextmapper.generated.evaluationcontext.domain.QuestionEvaluationViewedEvent;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.QuestionEvaluationViewedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionEvaluationViewedEvent} and its DTO {@link QuestionEvaluationViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionEvaluationViewedEventMapper extends EntityMapper<QuestionEvaluationViewedEventDTO, QuestionEvaluationViewedEvent> {
    @Mapping(target = "evaluation", source = "evaluation", qualifiedByName = "evaluationId")
    QuestionEvaluationViewedEventDTO toDto(QuestionEvaluationViewedEvent s);

    @Named("evaluationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationDTO toDtoEvaluationId(Evaluation evaluation);
}
