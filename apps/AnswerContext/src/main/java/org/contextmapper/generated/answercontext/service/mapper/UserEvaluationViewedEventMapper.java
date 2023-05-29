package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.Answer;
import org.contextmapper.generated.answercontext.domain.UserEvaluationViewedEvent;
import org.contextmapper.generated.answercontext.service.dto.AnswerDTO;
import org.contextmapper.generated.answercontext.service.dto.UserEvaluationViewedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserEvaluationViewedEvent} and its DTO {@link UserEvaluationViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserEvaluationViewedEventMapper extends EntityMapper<UserEvaluationViewedEventDTO, UserEvaluationViewedEvent> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "answerId")
    UserEvaluationViewedEventDTO toDto(UserEvaluationViewedEvent s);

    @Named("answerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnswerDTO toDtoAnswerId(Answer answer);
}
