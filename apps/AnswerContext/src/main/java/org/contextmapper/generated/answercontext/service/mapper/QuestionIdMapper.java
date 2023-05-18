package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.QuestionId;
import org.contextmapper.generated.answercontext.service.dto.QuestionIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionId} and its DTO {@link QuestionIdDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionIdMapper extends EntityMapper<QuestionIdDTO, QuestionId> {}
