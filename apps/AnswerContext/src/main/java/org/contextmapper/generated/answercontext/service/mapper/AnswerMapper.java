package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.Answer;
import org.contextmapper.generated.answercontext.domain.AnsweredTag;
import org.contextmapper.generated.answercontext.domain.QuestionId;
import org.contextmapper.generated.answercontext.service.dto.AnswerDTO;
import org.contextmapper.generated.answercontext.service.dto.AnsweredTagDTO;
import org.contextmapper.generated.answercontext.service.dto.QuestionIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Answer} and its DTO {@link AnswerDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnswerMapper extends EntityMapper<AnswerDTO, Answer> {
    @Mapping(target = "question", source = "question", qualifiedByName = "questionIdId")
    @Mapping(target = "answeredTag", source = "answeredTag", qualifiedByName = "answeredTagId")
    AnswerDTO toDto(Answer s);

    @Named("questionIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionIdDTO toDtoQuestionIdId(QuestionId questionId);

    @Named("answeredTagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnsweredTagDTO toDtoAnsweredTagId(AnsweredTag answeredTag);
}
