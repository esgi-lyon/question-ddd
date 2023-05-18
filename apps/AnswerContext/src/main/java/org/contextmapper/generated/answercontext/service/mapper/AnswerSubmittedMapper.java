package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.Answer;
import org.contextmapper.generated.answercontext.domain.AnswerSubmitted;
import org.contextmapper.generated.answercontext.service.dto.AnswerDTO;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmittedDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnswerSubmitted} and its DTO {@link AnswerSubmittedDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnswerSubmittedMapper extends EntityMapper<AnswerSubmittedDTO, AnswerSubmitted> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "answerId")
    AnswerSubmittedDTO toDto(AnswerSubmitted s);

    @Named("answerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnswerDTO toDtoAnswerId(Answer answer);
}
