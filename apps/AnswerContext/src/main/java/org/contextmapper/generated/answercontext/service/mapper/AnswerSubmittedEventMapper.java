package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.Answer;
import org.contextmapper.generated.answercontext.domain.AnswerSubmittedEvent;
import org.contextmapper.generated.answercontext.service.dto.AnswerDTO;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmittedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnswerSubmittedEvent} and its DTO {@link AnswerSubmittedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnswerSubmittedEventMapper extends EntityMapper<AnswerSubmittedEventDTO, AnswerSubmittedEvent> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "answerId")
    AnswerSubmittedEventDTO toDto(AnswerSubmittedEvent s);

    @Named("answerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnswerDTO toDtoAnswerId(Answer answer);
}
