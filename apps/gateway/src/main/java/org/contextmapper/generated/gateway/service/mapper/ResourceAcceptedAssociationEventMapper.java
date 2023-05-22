package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.QuestionResource;
import org.contextmapper.generated.gateway.domain.QuestionResourceTagInfos;
import org.contextmapper.generated.gateway.domain.ResourceAcceptedAssociationEvent;
import org.contextmapper.generated.gateway.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.gateway.service.dto.QuestionResourceTagInfosDTO;
import org.contextmapper.generated.gateway.service.dto.ResourceAcceptedAssociationEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResourceAcceptedAssociationEvent} and its DTO {@link ResourceAcceptedAssociationEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResourceAcceptedAssociationEventMapper
    extends EntityMapper<ResourceAcceptedAssociationEventDTO, ResourceAcceptedAssociationEvent> {
    @Mapping(target = "questionId", source = "questionId", qualifiedByName = "questionResourceId")
    @Mapping(target = "tagId", source = "tagId", qualifiedByName = "questionResourceTagInfosId")
    ResourceAcceptedAssociationEventDTO toDto(ResourceAcceptedAssociationEvent s);

    @Named("questionResourceId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceDTO toDtoQuestionResourceId(QuestionResource questionResource);

    @Named("questionResourceTagInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceTagInfosDTO toDtoQuestionResourceTagInfosId(QuestionResourceTagInfos questionResourceTagInfos);
}
