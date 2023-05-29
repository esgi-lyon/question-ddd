package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.UserEmail;
import org.contextmapper.generated.answercontext.domain.ViewUserEvaluationCommand;
import org.contextmapper.generated.answercontext.service.dto.UserEmailDTO;
import org.contextmapper.generated.answercontext.service.dto.ViewUserEvaluationCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ViewUserEvaluationCommand} and its DTO {@link ViewUserEvaluationCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewUserEvaluationCommandMapper extends EntityMapper<ViewUserEvaluationCommandDTO, ViewUserEvaluationCommand> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userEmailId")
    ViewUserEvaluationCommandDTO toDto(ViewUserEvaluationCommand s);

    @Named("userEmailId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserEmailDTO toDtoUserEmailId(UserEmail userEmail);
}
