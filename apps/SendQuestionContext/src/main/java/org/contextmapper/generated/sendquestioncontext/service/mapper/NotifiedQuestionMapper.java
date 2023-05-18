package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.NotifiedQuestion;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedQuestionDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotifiedQuestion} and its DTO {@link NotifiedQuestionDTO}.
 */
@Mapper(componentModel = "spring")
public interface NotifiedQuestionMapper extends EntityMapper<NotifiedQuestionDTO, NotifiedQuestion> {
    @Mapping(target = "questionResource", source = "questionResource", qualifiedByName = "questionSentId")
    NotifiedQuestionDTO toDto(NotifiedQuestion s);

    @Named("questionSentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionSentDTO toDtoQuestionSentId(QuestionSent questionSent);
}
