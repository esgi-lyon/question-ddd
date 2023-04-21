package org.contextmapper.generated.questioncontext.service.mapper;

import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.domain.QuestionResourceTag;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionResourceTag} and its DTO {@link QuestionResourceTagDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionResourceTagMapper extends EntityMapper<QuestionResourceTagDTO, QuestionResourceTag> {
    @Mapping(target = "questionId", source = "questionId", qualifiedByName = "questionResourceId")
    QuestionResourceTagDTO toDto(QuestionResourceTag s);

    @Named("questionResourceId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceDTO toDtoQuestionResourceId(QuestionResource questionResource);
}
