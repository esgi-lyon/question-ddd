package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.Answer;
import org.contextmapper.generated.answercontext.domain.AnswerSubmit;
import org.contextmapper.generated.answercontext.service.dto.AnswerDTO;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnswerSubmit} and its DTO {@link AnswerSubmitDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnswerSubmitMapper extends EntityMapper<AnswerSubmitDTO, AnswerSubmit> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "answerId")
    AnswerSubmitDTO toDto(AnswerSubmit s);

    @Named("answerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnswerDTO toDtoAnswerId(Answer answer);
}
