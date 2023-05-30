package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.AnsweringUser;
import org.contextmapper.generated.evaluationcontext.domain.ViewUserEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.service.dto.AnsweringUserDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.ViewUserEvaluationCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ViewUserEvaluationCommand} and its DTO {@link ViewUserEvaluationCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewUserEvaluationCommandMapper extends EntityMapper<ViewUserEvaluationCommandDTO, ViewUserEvaluationCommand> {
    @Mapping(target = "user", source = "user", qualifiedByName = "answeringUserId")
    ViewUserEvaluationCommandDTO toDto(ViewUserEvaluationCommand s);

    @Named("answeringUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnsweringUserDTO toDtoAnsweringUserId(AnsweringUser answeringUser);
}
