package org.contextmapper.generated.questioncontext.service.mapper;

import org.contextmapper.generated.questioncontext.domain.CreateResourceCommand;
import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.service.dto.CreateResourceCommandDTO;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreateResourceCommand} and its DTO {@link CreateResourceCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreateResourceCommandMapper extends EntityMapper<CreateResourceCommandDTO, CreateResourceCommand> {
    @Mapping(target = "questionId", source = "questionId", qualifiedByName = "questionResourceId")
    CreateResourceCommandDTO toDto(CreateResourceCommand s);

    @Named("questionResourceId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceDTO toDtoQuestionResourceId(QuestionResource questionResource);
}
