package org.contextmapper.generated.questioncontext.service.mapper;

import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.domain.QuestionResourceTagInfos;
import org.contextmapper.generated.questioncontext.domain.ResourceWaitingForAssociation;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagInfosDTO;
import org.contextmapper.generated.questioncontext.service.dto.ResourceWaitingForAssociationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResourceWaitingForAssociation} and its DTO {@link ResourceWaitingForAssociationDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResourceWaitingForAssociationMapper extends EntityMapper<ResourceWaitingForAssociationDTO, ResourceWaitingForAssociation> {
    @Mapping(target = "questionId", source = "questionId", qualifiedByName = "questionResourceId")
    @Mapping(target = "tagId", source = "tagId", qualifiedByName = "questionResourceTagInfosId")
    ResourceWaitingForAssociationDTO toDto(ResourceWaitingForAssociation s);

    @Named("questionResourceId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceDTO toDtoQuestionResourceId(QuestionResource questionResource);

    @Named("questionResourceTagInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceTagInfosDTO toDtoQuestionResourceTagInfosId(QuestionResourceTagInfos questionResourceTagInfos);
}
