package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagId;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QuestionSentTagId} and its DTO {@link QuestionSentTagIdDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuestionSentTagIdMapper extends EntityMapper<QuestionSentTagIdDTO, QuestionSentTagId> {
    @Mapping(target = "questionSent", source = "questionSent", qualifiedByName = "questionSentId")
    QuestionSentTagIdDTO toDto(QuestionSentTagId s);

    @Named("questionSentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionSentDTO toDtoQuestionSentId(QuestionSent questionSent);
}
