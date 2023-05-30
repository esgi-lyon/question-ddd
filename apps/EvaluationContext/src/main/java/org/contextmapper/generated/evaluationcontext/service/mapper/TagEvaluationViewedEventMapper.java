package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.Evaluation;
import org.contextmapper.generated.evaluationcontext.domain.TagEvaluationViewedEvent;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.TagEvaluationViewedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagEvaluationViewedEvent} and its DTO {@link TagEvaluationViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagEvaluationViewedEventMapper extends EntityMapper<TagEvaluationViewedEventDTO, TagEvaluationViewedEvent> {
    @Mapping(target = "evaluation", source = "evaluation", qualifiedByName = "evaluationId")
    TagEvaluationViewedEventDTO toDto(TagEvaluationViewedEvent s);

    @Named("evaluationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationDTO toDtoEvaluationId(Evaluation evaluation);
}
