package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.QuestionStatsViewedEvent;
import org.contextmapper.generated.statcontext.service.dto.QuestionStatsViewedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionStatsViewedEvent} and its DTO {@link QuestionStatsViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionStatsViewedEventMapper extends EntityMapper<QuestionStatsViewedEventDTO, QuestionStatsViewedEvent> {}
