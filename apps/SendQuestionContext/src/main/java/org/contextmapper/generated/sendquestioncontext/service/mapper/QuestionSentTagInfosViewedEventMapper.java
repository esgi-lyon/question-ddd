package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagInfosViewedEvent;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagInfosViewedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionSentTagInfosViewedEvent} and its DTO {@link QuestionSentTagInfosViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionSentTagInfosViewedEventMapper
    extends EntityMapper<QuestionSentTagInfosViewedEventDTO, QuestionSentTagInfosViewedEvent> {}
