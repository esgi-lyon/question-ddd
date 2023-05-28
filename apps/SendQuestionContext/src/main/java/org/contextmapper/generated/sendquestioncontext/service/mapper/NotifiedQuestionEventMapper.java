package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.NotifiedQuestionEvent;
import org.contextmapper.generated.sendquestioncontext.domain.NotifiedUsers;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedQuestionEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedUsersDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotifiedQuestionEvent} and its DTO {@link NotifiedQuestionEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface NotifiedQuestionEventMapper extends EntityMapper<NotifiedQuestionEventDTO, NotifiedQuestionEvent> {
    @Mapping(target = "questionResource", source = "questionResource", qualifiedByName = "notifiedUsersId")
    NotifiedQuestionEventDTO toDto(NotifiedQuestionEvent s);

    @Named("notifiedUsersId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NotifiedUsersDTO toDtoNotifiedUsersId(NotifiedUsers notifiedUsers);
}
