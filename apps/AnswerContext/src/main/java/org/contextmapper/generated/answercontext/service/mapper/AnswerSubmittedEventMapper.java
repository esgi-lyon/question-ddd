package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.AnswerSubmittedEvent;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmittedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnswerSubmittedEvent} and its DTO {@link AnswerSubmittedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnswerSubmittedEventMapper extends EntityMapper<AnswerSubmittedEventDTO, AnswerSubmittedEvent> {}
