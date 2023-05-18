package org.contextmapper.generated.questioncontext.service.mapper;

import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.domain.QuestionResourceTagInfos;
import org.contextmapper.generated.questioncontext.domain.ResourceAcceptedAssociation;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagInfosDTO;
import org.contextmapper.generated.questioncontext.service.dto.ResourceAcceptedAssociationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResourceAcceptedAssociation} and its DTO {@link ResourceAcceptedAssociationDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResourceAcceptedAssociationMapper extends EntityMapper<ResourceAcceptedAssociationDTO, ResourceAcceptedAssociation> {
    @Mapping(target = "questionId", source = "questionId", qualifiedByName = "questionResourceId")
    @Mapping(target = "tagId", source = "tagId", qualifiedByName = "questionResourceTagInfosId")
    ResourceAcceptedAssociationDTO toDto(ResourceAcceptedAssociation s);

    @Named("questionResourceId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceDTO toDtoQuestionResourceId(QuestionResource questionResource);

    @Named("questionResourceTagInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceTagInfosDTO toDtoQuestionResourceTagInfosId(QuestionResourceTagInfos questionResourceTagInfos);
}
