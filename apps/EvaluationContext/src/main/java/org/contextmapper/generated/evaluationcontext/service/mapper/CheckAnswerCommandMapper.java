package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.CheckAnswerCommand;
import org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer;
import org.contextmapper.generated.evaluationcontext.service.dto.CheckAnswerCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluatedAnswerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CheckAnswerCommand} and its DTO {@link CheckAnswerCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface CheckAnswerCommandMapper extends EntityMapper<CheckAnswerCommandDTO, CheckAnswerCommand> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "evaluatedAnswerId")
    CheckAnswerCommandDTO toDto(CheckAnswerCommand s);

    @Named("evaluatedAnswerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluatedAnswerDTO toDtoEvaluatedAnswerId(EvaluatedAnswer evaluatedAnswer);
}
