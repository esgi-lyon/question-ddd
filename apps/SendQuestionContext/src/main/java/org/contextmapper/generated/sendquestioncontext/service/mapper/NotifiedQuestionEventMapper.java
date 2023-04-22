package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.NotifiedQuestionEvent;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedQuestionEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotifiedQuestionEvent} and its DTO {@link NotifiedQuestionEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface NotifiedQuestionEventMapper extends EntityMapper<NotifiedQuestionEventDTO, NotifiedQuestionEvent> {}
