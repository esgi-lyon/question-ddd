package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.Answer;
import org.contextmapper.generated.answercontext.domain.TagChoicesListedEvent;
import org.contextmapper.generated.answercontext.service.dto.AnswerDTO;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagChoicesListedEvent} and its DTO {@link TagChoicesListedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagChoicesListedEventMapper extends EntityMapper<TagChoicesListedEventDTO, TagChoicesListedEvent> {
    @Mapping(target = "answerCreated", source = "answerCreated", qualifiedByName = "answerId")
    TagChoicesListedEventDTO toDto(TagChoicesListedEvent s);

    @Named("answerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnswerDTO toDtoAnswerId(Answer answer);
}
