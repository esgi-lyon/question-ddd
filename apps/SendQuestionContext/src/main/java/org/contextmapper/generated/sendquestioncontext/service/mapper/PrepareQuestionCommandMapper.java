package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionCommand;
import org.contextmapper.generated.sendquestioncontext.service.dto.PrepareQuestionCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrepareQuestionCommand} and its DTO {@link PrepareQuestionCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface PrepareQuestionCommandMapper extends EntityMapper<PrepareQuestionCommandDTO, PrepareQuestionCommand> {}
