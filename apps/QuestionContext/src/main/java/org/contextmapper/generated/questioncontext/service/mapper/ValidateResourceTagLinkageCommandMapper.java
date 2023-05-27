package org.contextmapper.generated.questioncontext.service.mapper;

import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.domain.ValidateResourceTagLinkageCommand;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.ValidateResourceTagLinkageCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ValidateResourceTagLinkageCommand} and its DTO {@link ValidateResourceTagLinkageCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface ValidateResourceTagLinkageCommandMapper
    extends EntityMapper<ValidateResourceTagLinkageCommandDTO, ValidateResourceTagLinkageCommand> {
    @Mapping(target = "questionId", source = "questionId", qualifiedByName = "questionResourceId")
    ValidateResourceTagLinkageCommandDTO toDto(ValidateResourceTagLinkageCommand s);

    @Named("questionResourceId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceDTO toDtoQuestionResourceId(QuestionResource questionResource);
}
