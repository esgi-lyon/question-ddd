package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.Answer;
import org.contextmapper.generated.answercontext.domain.AnswerSubmitCommand;
import org.contextmapper.generated.answercontext.service.dto.AnswerDTO;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmitCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnswerSubmitCommand} and its DTO {@link AnswerSubmitCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnswerSubmitCommandMapper extends EntityMapper<AnswerSubmitCommandDTO, AnswerSubmitCommand> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "answerId")
    AnswerSubmitCommandDTO toDto(AnswerSubmitCommand s);

    @Named("answerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnswerDTO toDtoAnswerId(Answer answer);
}
