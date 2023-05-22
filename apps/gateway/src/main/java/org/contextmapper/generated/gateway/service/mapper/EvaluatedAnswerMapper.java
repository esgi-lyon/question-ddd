package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.EvaluatedAnswer;
import org.contextmapper.generated.gateway.service.dto.EvaluatedAnswerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EvaluatedAnswer} and its DTO {@link EvaluatedAnswerDTO}.
 */
@Mapper(componentModel = "spring")
public interface EvaluatedAnswerMapper extends EntityMapper<EvaluatedAnswerDTO, EvaluatedAnswer> {}
