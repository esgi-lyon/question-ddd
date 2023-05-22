package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.Question;
import org.contextmapper.generated.gateway.domain.QuestionSentTagInfos;
import org.contextmapper.generated.gateway.service.dto.QuestionDTO;
import org.contextmapper.generated.gateway.service.dto.QuestionSentTagInfosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Question} and its DTO {@link QuestionDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionMapper extends EntityMapper<QuestionDTO, Question> {
    @Mapping(target = "resourceCorrectTag", source = "resourceCorrectTag", qualifiedByName = "questionSentTagInfosId")
    QuestionDTO toDto(Question s);

    @Named("questionSentTagInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionSentTagInfosDTO toDtoQuestionSentTagInfosId(QuestionSentTagInfos questionSentTagInfos);
}
