package org.contextmapper.generated.questioncontext.service.mapper;

import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.domain.QuestionResourceTagInfos;
import org.contextmapper.generated.questioncontext.domain.ResourceAcceptedAssociationEvent;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagInfosDTO;
import org.contextmapper.generated.questioncontext.service.dto.ResourceAcceptedAssociationEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResourceAcceptedAssociationEvent} and its DTO {@link ResourceAcceptedAssociationEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResourceAcceptedAssociationEventMapper
    extends EntityMapper<ResourceAcceptedAssociationEventDTO, ResourceAcceptedAssociationEvent> {
    @Mapping(target = "questionId", source = "questionId", qualifiedByName = "questionResourceId")
    @Mapping(target = "tagInfos", source = "tagInfos", qualifiedByName = "questionResourceTagInfosId")
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
