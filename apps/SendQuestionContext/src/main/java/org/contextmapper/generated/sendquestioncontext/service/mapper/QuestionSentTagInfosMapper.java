package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagInfos;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagInfosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionSentTagInfos} and its DTO {@link QuestionSentTagInfosDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionSentTagInfosMapper extends EntityMapper<QuestionSentTagInfosDTO, QuestionSentTagInfos> {
    @Mapping(target = "questionSent", source = "questionSent", qualifiedByName = "questionSentId")
    QuestionSentTagInfosDTO toDto(QuestionSentTagInfos s);

    @Named("questionSentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionSentDTO toDtoQuestionSentId(QuestionSent questionSent);
}
