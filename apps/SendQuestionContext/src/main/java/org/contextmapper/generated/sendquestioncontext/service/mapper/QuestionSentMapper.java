package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.domain.ResourceId;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.ResourceIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionSent} and its DTO {@link QuestionSentDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionSentMapper extends EntityMapper<QuestionSentDTO, QuestionSent> {
    @Mapping(target = "resourceId", source = "resourceId", qualifiedByName = "resourceIdId")
    QuestionSentDTO toDto(QuestionSent s);

    @Named("resourceIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ResourceIdDTO toDtoResourceIdId(ResourceId resourceId);
}
