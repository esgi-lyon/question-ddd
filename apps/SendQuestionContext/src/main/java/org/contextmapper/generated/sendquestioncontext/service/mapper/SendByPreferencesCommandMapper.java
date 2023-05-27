package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.domain.SendByPreferencesCommand;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.SendByPreferencesCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SendByPreferencesCommand} and its DTO {@link SendByPreferencesCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface SendByPreferencesCommandMapper extends EntityMapper<SendByPreferencesCommandDTO, SendByPreferencesCommand> {
    @Mapping(target = "questionToSend", source = "questionToSend", qualifiedByName = "questionSentId")
    SendByPreferencesCommandDTO toDto(SendByPreferencesCommand s);

    @Named("questionSentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionSentDTO toDtoQuestionSentId(QuestionSent questionSent);
}
