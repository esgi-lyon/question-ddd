package org.contextmapper.generated.questioncontext.service.mapper;

import org.contextmapper.generated.questioncontext.domain.CreateResource;
import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.service.dto.CreateResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreateResource} and its DTO {@link CreateResourceDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreateResourceMapper extends EntityMapper<CreateResourceDTO, CreateResource> {
    @Mapping(target = "questionId", source = "questionId", qualifiedByName = "questionResourceId")
    CreateResourceDTO toDto(CreateResource s);

    @Named("questionResourceId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceDTO toDtoQuestionResourceId(QuestionResource questionResource);
}
