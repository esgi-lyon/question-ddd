package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.QuestionResource;
import org.contextmapper.generated.gateway.domain.QuestionResourceTagInfos;
import org.contextmapper.generated.gateway.domain.ResourceWaitingForAssociationEvent;
import org.contextmapper.generated.gateway.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.gateway.service.dto.QuestionResourceTagInfosDTO;
import org.contextmapper.generated.gateway.service.dto.ResourceWaitingForAssociationEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResourceWaitingForAssociationEvent} and its DTO {@link ResourceWaitingForAssociationEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResourceWaitingForAssociationEventMapper
    extends EntityMapper<ResourceWaitingForAssociationEventDTO, ResourceWaitingForAssociationEvent> {
    @Mapping(target = "questionId", source = "questionId", qualifiedByName = "questionResourceId")
    @Mapping(target = "tagId", source = "tagId", qualifiedByName = "questionResourceTagInfosId")
    ResourceWaitingForAssociationEventDTO toDto(ResourceWaitingForAssociationEvent s);

    @Named("questionResourceId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceDTO toDtoQuestionResourceId(QuestionResource questionResource);

    @Named("questionResourceTagInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceTagInfosDTO toDtoQuestionResourceTagInfosId(QuestionResourceTagInfos questionResourceTagInfos);
}
