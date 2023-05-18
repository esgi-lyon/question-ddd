package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.AnsweredTag;
import org.contextmapper.generated.answercontext.service.dto.AnsweredTagDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnsweredTag} and its DTO {@link AnsweredTagDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnsweredTagMapper extends EntityMapper<AnsweredTagDTO, AnsweredTag> {}
