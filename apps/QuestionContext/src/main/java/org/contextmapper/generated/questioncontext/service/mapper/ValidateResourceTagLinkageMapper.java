package org.contextmapper.generated.questioncontext.service.mapper;

import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.domain.ValidateResourceTagLinkage;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.ValidateResourceTagLinkageDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ValidateResourceTagLinkage} and its DTO {@link ValidateResourceTagLinkageDTO}.
 */
@Mapper(componentModel = "spring")
public interface ValidateResourceTagLinkageMapper extends EntityMapper<ValidateResourceTagLinkageDTO, ValidateResourceTagLinkage> {
    @Mapping(target = "questionId", source = "questionId", qualifiedByName = "questionResourceId")
    ValidateResourceTagLinkageDTO toDto(ValidateResourceTagLinkage s);

    @Named("questionResourceId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceDTO toDtoQuestionResourceId(QuestionResource questionResource);
}
