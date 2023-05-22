package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.NotifiedQuestionEvent;
import org.contextmapper.generated.gateway.domain.QuestionSent;
import org.contextmapper.generated.gateway.service.dto.NotifiedQuestionEventDTO;
import org.contextmapper.generated.gateway.service.dto.QuestionSentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotifiedQuestionEvent} and its DTO {@link NotifiedQuestionEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface NotifiedQuestionEventMapper extends EntityMapper<NotifiedQuestionEventDTO, NotifiedQuestionEvent> {
    @Mapping(target = "questionResource", source = "questionResource", qualifiedByName = "questionSentId")
    NotifiedQuestionEventDTO toDto(NotifiedQuestionEvent s);

    @Named("questionSentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionSentDTO toDtoQuestionSentId(QuestionSent questionSent);
}
