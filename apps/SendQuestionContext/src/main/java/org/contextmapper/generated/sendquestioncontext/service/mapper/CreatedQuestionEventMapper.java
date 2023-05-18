package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.CreatedQuestionEvent;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreatedQuestionEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreatedQuestionEvent} and its DTO {@link CreatedQuestionEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreatedQuestionEventMapper extends EntityMapper<CreatedQuestionEventDTO, CreatedQuestionEvent> {
    @Mapping(target = "questionAndTag", source = "questionAndTag", qualifiedByName = "questionSentId")
    CreatedQuestionEventDTO toDto(CreatedQuestionEvent s);

    @Named("questionSentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionSentDTO toDtoQuestionSentId(QuestionSent questionSent);
}
