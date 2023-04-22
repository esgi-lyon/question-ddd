package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.AnswerCheckedEvent;
import org.contextmapper.generated.evaluationcontext.service.dto.AnswerCheckedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnswerCheckedEvent} and its DTO {@link AnswerCheckedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnswerCheckedEventMapper extends EntityMapper<AnswerCheckedEventDTO, AnswerCheckedEvent> {}
