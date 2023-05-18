package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.CreatedQuestion;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreatedQuestionDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreatedQuestion} and its DTO {@link CreatedQuestionDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreatedQuestionMapper extends EntityMapper<CreatedQuestionDTO, CreatedQuestion> {
    @Mapping(target = "questionAndTag", source = "questionAndTag", qualifiedByName = "questionSentId")
    CreatedQuestionDTO toDto(CreatedQuestion s);

    @Named("questionSentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionSentDTO toDtoQuestionSentId(QuestionSent questionSent);
}
