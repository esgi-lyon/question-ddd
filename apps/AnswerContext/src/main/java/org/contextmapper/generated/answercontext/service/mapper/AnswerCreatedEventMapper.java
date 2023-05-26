package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.Answer;
import org.contextmapper.generated.answercontext.domain.AnswerCreatedEvent;
import org.contextmapper.generated.answercontext.service.dto.AnswerCreatedEventDTO;
import org.contextmapper.generated.answercontext.service.dto.AnswerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnswerCreatedEvent} and its DTO {@link AnswerCreatedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnswerCreatedEventMapper extends EntityMapper<AnswerCreatedEventDTO, AnswerCreatedEvent> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "answerId")
    AnswerCreatedEventDTO toDto(AnswerCreatedEvent s);

    @Named("answerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnswerDTO toDtoAnswerId(Answer answer);
}
