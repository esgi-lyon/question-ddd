package org.contextmapper.generated.questioncontext.service.mapper;

import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.domain.RejectResourceTag;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.RejectResourceTagDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RejectResourceTag} and its DTO {@link RejectResourceTagDTO}.
 */
@Mapper(componentModel = "spring")
public interface RejectResourceTagMapper extends EntityMapper<RejectResourceTagDTO, RejectResourceTag> {
    @Mapping(target = "questionId", source = "questionId", qualifiedByName = "questionResourceId")
    RejectResourceTagDTO toDto(RejectResourceTag s);

    @Named("questionResourceId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceDTO toDtoQuestionResourceId(QuestionResource questionResource);
}
