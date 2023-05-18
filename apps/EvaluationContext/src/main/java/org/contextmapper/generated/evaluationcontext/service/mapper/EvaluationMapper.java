package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.AnsweringUser;
import org.contextmapper.generated.evaluationcontext.domain.Evaluation;
import org.contextmapper.generated.evaluationcontext.domain.EvaluationQuestion;
import org.contextmapper.generated.evaluationcontext.domain.EvaluationTag;
import org.contextmapper.generated.evaluationcontext.service.dto.AnsweringUserDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationQuestionDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationTagDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Evaluation} and its DTO {@link EvaluationDTO}.
 */
@Mapper(componentModel = "spring")
public interface EvaluationMapper extends EntityMapper<EvaluationDTO, Evaluation> {
    @Mapping(target = "tag", source = "tag", qualifiedByName = "evaluationTagId")
    @Mapping(target = "question", source = "question", qualifiedByName = "evaluationQuestionId")
    @Mapping(target = "user", source = "user", qualifiedByName = "answeringUserId")
    EvaluationDTO toDto(Evaluation s);

    @Named("evaluationTagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationTagDTO toDtoEvaluationTagId(EvaluationTag evaluationTag);

    @Named("evaluationQuestionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluationQuestionDTO toDtoEvaluationQuestionId(EvaluationQuestion evaluationQuestion);

    @Named("answeringUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnsweringUserDTO toDtoAnsweringUserId(AnsweringUser answeringUser);
}
