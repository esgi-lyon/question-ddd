package org.contextmapper.generated.evaluationcontext.service.mapper;

import org.contextmapper.generated.evaluationcontext.domain.AwardedPoint;
import org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardedPointDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluatedAnswerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AwardedPoint} and its DTO {@link AwardedPointDTO}.
 */
@Mapper(componentModel = "spring")
public interface AwardedPointMapper extends EntityMapper<AwardedPointDTO, AwardedPoint> {
    @Mapping(target = "answer", source = "answer", qualifiedByName = "evaluatedAnswerId")
    AwardedPointDTO toDto(AwardedPoint s);

    @Named("evaluatedAnswerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EvaluatedAnswerDTO toDtoEvaluatedAnswerId(EvaluatedAnswer evaluatedAnswer);
}
