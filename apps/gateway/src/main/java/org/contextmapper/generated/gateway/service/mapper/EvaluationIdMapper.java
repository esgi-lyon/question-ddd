package org.contextmapper.generated.gateway.service.mapper;

import org.contextmapper.generated.gateway.domain.EvaluationId;
import org.contextmapper.generated.gateway.domain.LeaderBoard;
import org.contextmapper.generated.gateway.service.dto.EvaluationIdDTO;
import org.contextmapper.generated.gateway.service.dto.LeaderBoardDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EvaluationId} and its DTO {@link EvaluationIdDTO}.
 */
@Mapper(componentModel = "spring")
public interface EvaluationIdMapper extends EntityMapper<EvaluationIdDTO, EvaluationId> {
    @Mapping(target = "leaderBoard", source = "leaderBoard", qualifiedByName = "leaderBoardId")
    EvaluationIdDTO toDto(EvaluationId s);

    @Named("leaderBoardId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    LeaderBoardDTO toDtoLeaderBoardId(LeaderBoard leaderBoard);
}
