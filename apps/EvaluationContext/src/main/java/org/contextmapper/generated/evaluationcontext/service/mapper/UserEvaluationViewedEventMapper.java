package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.Evaluation;
import org.contextmapper.generated.evaluationcontext.domain.UserEvaluationViewedEvent;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.UserEvaluationViewedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserEvaluationViewedEvent} and its DTO {@link UserEvaluationViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserEvaluationViewedEventMapper extends EntityMapper<UserEvaluationViewedEventDTO, UserEvaluationViewedEvent> {
    @Mapping(target = "evaluation", source = "evaluation", qualifiedByName = "evaluationId")
    UserEvaluationViewedEventDTO toDto(UserEvaluationViewedEvent s);

    @Named("evaluationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationDTO toDtoEvaluationId(Evaluation evaluation);
}
