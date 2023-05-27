package org.contextmapper.generated.answercontext.service.mapper;

import org.contextmapper.generated.answercontext.domain.QuestionSentId;
import org.contextmapper.generated.answercontext.domain.TagChoicesListCommand;
import org.contextmapper.generated.answercontext.service.dto.QuestionSentIdDTO;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagChoicesListCommand} and its DTO {@link TagChoicesListCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface TagChoicesListCommandMapper extends EntityMapper<TagChoicesListCommandDTO, TagChoicesListCommand> {
    @Mapping(target = "questionSent", source = "questionSent", qualifiedByName = "questionSentIdId")
    TagChoicesListCommandDTO toDto(TagChoicesListCommand s);

    @Named("questionSentIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QuestionSentIdDTO toDtoQuestionSentIdId(QuestionSentId questionSentId);
}
