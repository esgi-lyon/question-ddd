package org.contextmapper.generated.questioncontext.service.mapper;

import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionResource} and its DTO {@link QuestionResourceDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionResourceMapper extends EntityMapper<QuestionResourceDTO, QuestionResource> {}
