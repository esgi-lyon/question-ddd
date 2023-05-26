package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.AvailableAnswer;
import org.contextmapper.generated.answercontext.domain.TagChoicesListedEvent;
import org.contextmapper.generated.answercontext.service.dto.AvailableAnswerDTO;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AvailableAnswer} and its DTO {@link AvailableAnswerDTO}.
 */
@Mapper(componentModel = "spring")
public interface AvailableAnswerMapper extends EntityMapper<AvailableAnswerDTO, AvailableAnswer> {
    @Mapping(target = "tagChoicesListedEvent", source = "tagChoicesListedEvent", qualifiedByName = "tagChoicesListedEventId")
    AvailableAnswerDTO toDto(AvailableAnswer s);

    @Named("tagChoicesListedEventId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TagChoicesListedEventDTO toDtoTagChoicesListedEventId(TagChoicesListedEvent tagChoicesListedEvent);
}
