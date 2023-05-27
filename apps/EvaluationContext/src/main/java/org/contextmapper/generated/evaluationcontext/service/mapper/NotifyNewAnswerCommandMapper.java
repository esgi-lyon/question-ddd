package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer;
import org.contextmapper.generated.evaluationcontext.domain.NotifyNewAnswerCommand;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluatedAnswerDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.NotifyNewAnswerCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotifyNewAnswerCommand} and its DTO {@link NotifyNewAnswerCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface NotifyNewAnswerCommandMapper extends EntityMapper<NotifyNewAnswerCommandDTO, NotifyNewAnswerCommand> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "evaluatedAnswerId")
    NotifyNewAnswerCommandDTO toDto(NotifyNewAnswerCommand s);

    @Named("evaluatedAnswerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluatedAnswerDTO toDtoEvaluatedAnswerId(EvaluatedAnswer evaluatedAnswer);
}
