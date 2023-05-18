package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.Question;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagInfos;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagInfosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionSentTagInfos} and its DTO {@link QuestionSentTagInfosDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionSentTagInfosMapper extends EntityMapper<QuestionSentTagInfosDTO, QuestionSentTagInfos> {
    @Mapping(target = "question", source = "question", qualifiedByName = "questionId")
    QuestionSentTagInfosDTO toDto(QuestionSentTagInfos s);

    @Named("questionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionDTO toDtoQuestionId(Question question);
}
