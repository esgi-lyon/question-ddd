package org.contextmapper.generated.questioncontext.service.mapper;

import org.contextmapper.generated.questioncontext.domain.QuestionResource;
import org.contextmapper.generated.questioncontext.domain.QuestionResourceTagInfos;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagInfosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionResource} and its DTO {@link QuestionResourceDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionResourceMapper extends EntityMapper<QuestionResourceDTO, QuestionResource> {
    @Mapping(target = "tagInfos", source = "tagInfos", qualifiedByName = "questionResourceTagInfosId")
    QuestionResourceDTO toDto(QuestionResource s);

    @Named("questionResourceTagInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionResourceTagInfosDTO toDtoQuestionResourceTagInfosId(QuestionResourceTagInfos questionResourceTagInfos);
}
