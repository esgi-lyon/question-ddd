package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionSent} and its DTO {@link QuestionSentDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionSentMapper extends EntityMapper<QuestionSentDTO, QuestionSent> {}
