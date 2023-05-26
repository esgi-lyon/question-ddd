package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.Answer;
import org.contextmapper.generated.answercontext.domain.AnsweredTag;
import org.contextmapper.generated.answercontext.domain.QuestionSentId;
import org.contextmapper.generated.answercontext.domain.UserId;
import org.contextmapper.generated.answercontext.service.dto.AnswerDTO;
import org.contextmapper.generated.answercontext.service.dto.AnsweredTagDTO;
import org.contextmapper.generated.answercontext.service.dto.QuestionSentIdDTO;
import org.contextmapper.generated.answercontext.service.dto.UserIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Answer} and its DTO {@link AnswerDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnswerMapper extends EntityMapper<AnswerDTO, Answer> {
    @Mapping(target = "question", source = "question", qualifiedByName = "questionSentIdId")
    @Mapping(target = "answeredTag", source = "answeredTag", qualifiedByName = "answeredTagId")
    @Mapping(target = "userId", source = "userId", qualifiedByName = "userIdId")
    AnswerDTO toDto(Answer s);

    @Named("questionSentIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionSentIdDTO toDtoQuestionSentIdId(QuestionSentId questionSentId);

    @Named("answeredTagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnsweredTagDTO toDtoAnsweredTagId(AnsweredTag answeredTag);

    @Named("userIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserIdDTO toDtoUserIdId(UserId userId);
}
