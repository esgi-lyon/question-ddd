package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.domain.SendQuestionByTagsPreferences;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.SendQuestionByTagsPreferencesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SendQuestionByTagsPreferences} and its DTO {@link SendQuestionByTagsPreferencesDTO}.
 */
@Mapper(componentModel = "spring")
public interface SendQuestionByTagsPreferencesMapper extends EntityMapper<SendQuestionByTagsPreferencesDTO, SendQuestionByTagsPreferences> {
    @Mapping(target = "questionSent", source = "questionSent", qualifiedByName = "questionSentId")
    SendQuestionByTagsPreferencesDTO toDto(SendQuestionByTagsPreferences s);

    @Named("questionSentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionSentDTO toDtoQuestionSentId(QuestionSent questionSent);
}
