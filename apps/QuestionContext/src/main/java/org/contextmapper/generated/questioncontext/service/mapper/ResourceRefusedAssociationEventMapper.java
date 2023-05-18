package org.contextmapper.generated.questioncontext.service.mapper;

import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.domain.QuestionResourceTagInfos;
import org.contextmapper.generated.questioncontext.domain.ResourceRefusedAssociationEvent;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagInfosDTO;
import org.contextmapper.generated.questioncontext.service.dto.ResourceRefusedAssociationEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResourceRefusedAssociationEvent} and its DTO {@link ResourceRefusedAssociationEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResourceRefusedAssociationEventMapper
    extends EntityMapper<ResourceRefusedAssociationEventDTO, ResourceRefusedAssociationEvent> {
    @Mapping(target = "questionId", source = "questionId", qualifiedByName = "questionResourceId")
    @Mapping(target = "tagId", source = "tagId", qualifiedByName = "questionResourceTagInfosId")
    ResourceRefusedAssociationEventDTO toDto(ResourceRefusedAssociationEvent s);

    @Named("questionResourceId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceDTO toDtoQuestionResourceId(QuestionResource questionResource);

    @Named("questionResourceTagInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceTagInfosDTO toDtoQuestionResourceTagInfosId(QuestionResourceTagInfos questionResourceTagInfos);
}
