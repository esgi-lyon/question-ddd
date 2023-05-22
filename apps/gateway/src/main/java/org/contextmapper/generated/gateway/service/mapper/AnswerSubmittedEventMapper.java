package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.Answer;
import org.contextmapper.generated.gateway.domain.AnswerSubmittedEvent;
import org.contextmapper.generated.gateway.service.dto.AnswerDTO;
import org.contextmapper.generated.gateway.service.dto.AnswerSubmittedEventDTO;
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
