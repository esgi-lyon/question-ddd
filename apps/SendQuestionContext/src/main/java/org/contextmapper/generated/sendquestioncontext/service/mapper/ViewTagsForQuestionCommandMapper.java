package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.QuestionSent;
import org.contextmapper.generated.sendquestioncontext.domain.ViewTagsForQuestionCommand;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.ViewTagsForQuestionCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ViewTagsForQuestionCommand} and its DTO {@link ViewTagsForQuestionCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewTagsForQuestionCommandMapper extends EntityMapper<ViewTagsForQuestionCommandDTO, ViewTagsForQuestionCommand> {
    @Mapping(target = "questionToSend", source = "questionToSend", qualifiedByName = "questionSentId")
    ViewTagsForQuestionCommandDTO toDto(ViewTagsForQuestionCommand s);

    @Named("questionSentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionSentDTO toDtoQuestionSentId(QuestionSent questionSent);
}
