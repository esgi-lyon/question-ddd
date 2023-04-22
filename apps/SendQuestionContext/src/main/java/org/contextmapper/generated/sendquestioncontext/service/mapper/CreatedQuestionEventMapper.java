package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.CreatedQuestionEvent;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreatedQuestionEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreatedQuestionEvent} and its DTO {@link CreatedQuestionEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreatedQuestionEventMapper extends EntityMapper<CreatedQuestionEventDTO, CreatedQuestionEvent> {}
