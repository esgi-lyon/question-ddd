package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.CreateQuestion;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentQuestionResourceTagId;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreateQuestionDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentQuestionResourceTagIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreateQuestion} and its DTO {@link CreateQuestionDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreateQuestionMapper extends EntityMapper<CreateQuestionDTO, CreateQuestion> {
    @Mapping(target = "resource", source = "resource", qualifiedByName = "questionSentQuestionResourceTagIdId")
    CreateQuestionDTO toDto(CreateQuestion s);

    @Named("questionSentQuestionResourceTagIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionSentQuestionResourceTagIdDTO toDtoQuestionSentQuestionResourceTagIdId(
        QuestionSentQuestionResourceTagId questionSentQuestionResourceTagId
    );
}
