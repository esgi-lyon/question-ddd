package org.contextmapper.generated.questioncontext.service.mapper;

import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.domain.QuestionResourceTagInfos;
import org.contextmapper.generated.questioncontext.domain.ResourceWaitingForAssociationEvent;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagInfosDTO;
import org.contextmapper.generated.questioncontext.service.dto.ResourceWaitingForAssociationEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResourceWaitingForAssociationEvent} and its DTO {@link ResourceWaitingForAssociationEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResourceWaitingForAssociationEventMapper
    extends EntityMapper<ResourceWaitingForAssociationEventDTO, ResourceWaitingForAssociationEvent> {
    @Mapping(target = "questionId", source = "questionId", qualifiedByName = "questionResourceId")
    @Mapping(target = "tagInfos", source = "tagInfos", qualifiedByName = "questionResourceTagInfosId")
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
