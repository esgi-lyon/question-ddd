package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.LeaderBoard;
import org.contextmapper.generated.statcontext.domain.LeaderBoardViewedEvent;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectTag;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardDTO;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardViewedEventDTO;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectTagDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LeaderBoardViewedEvent} and its DTO {@link LeaderBoardViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface LeaderBoardViewedEventMapper extends EntityMapper<LeaderBoardViewedEventDTO, LeaderBoardViewedEvent> {
    @Mapping(target = "tag", source = "tag", qualifiedByName = "statisticSubjectTagId")
    @Mapping(target = "newUserLeaderboard", source = "newUserLeaderboard", qualifiedByName = "leaderBoardId")
    LeaderBoardViewedEventDTO toDto(LeaderBoardViewedEvent s);

    @Named("statisticSubjectTagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectTagDTO toDtoStatisticSubjectTagId(StatisticSubjectTag statisticSubjectTag);

    @Named("leaderBoardId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    LeaderBoardDTO toDtoLeaderBoardId(LeaderBoard leaderBoard);
}
