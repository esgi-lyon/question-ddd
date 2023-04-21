package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.Question;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionSent} and its DTO {@link QuestionSentDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionSentMapper extends EntityMapper<QuestionSentDTO, QuestionSent> {
    @Mapping(target = "question", source = "question", qualifiedByName = "questionId")
    QuestionSentDTO toDto(QuestionSent s);

    @Named("questionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionDTO toDtoQuestionId(Question question);
}
