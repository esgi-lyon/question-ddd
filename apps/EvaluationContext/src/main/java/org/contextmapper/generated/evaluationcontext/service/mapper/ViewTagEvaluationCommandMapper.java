package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.EvaluationTag;
import org.contextmapper.generated.evaluationcontext.domain.ViewTagEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationTagDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.ViewTagEvaluationCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ViewTagEvaluationCommand} and its DTO {@link ViewTagEvaluationCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewTagEvaluationCommandMapper extends EntityMapper<ViewTagEvaluationCommandDTO, ViewTagEvaluationCommand> {
    @Mapping(target = "tag", source = "tag", qualifiedByName = "evaluationTagId")
    ViewTagEvaluationCommandDTO toDto(ViewTagEvaluationCommand s);

    @Named("evaluationTagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationTagDTO toDtoEvaluationTagId(EvaluationTag evaluationTag);
}
