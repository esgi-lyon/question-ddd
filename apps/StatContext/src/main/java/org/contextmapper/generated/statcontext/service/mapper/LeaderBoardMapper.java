package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.LeaderBoard;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LeaderBoard} and its DTO {@link LeaderBoardDTO}.
 */
@Mapper(componentModel = "spring")
public interface LeaderBoardMapper extends EntityMapper<LeaderBoardDTO, LeaderBoard> {}
