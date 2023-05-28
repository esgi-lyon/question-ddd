package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.NotifiedUsers;
import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedUsersDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotifiedUsers} and its DTO {@link NotifiedUsersDTO}.
 */
@Mapper(componentModel = "spring")
public interface NotifiedUsersMapper extends EntityMapper<NotifiedUsersDTO, NotifiedUsers> {
    @Mapping(target = "question", source = "question", qualifiedByName = "questionSentId")
    NotifiedUsersDTO toDto(NotifiedUsers s);

    @Named("questionSentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionSentDTO toDtoQuestionSentId(QuestionSent questionSent);
}
