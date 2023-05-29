package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.LeaderBoard;
import org.contextmapper.generated.statcontext.domain.LeaderBoardEntry;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectUser;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardDTO;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardEntryDTO;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LeaderBoardEntry} and its DTO {@link LeaderBoardEntryDTO}.
 */
@Mapper(componentModel = "spring")
public interface LeaderBoardEntryMapper extends EntityMapper<LeaderBoardEntryDTO, LeaderBoardEntry> {
    @Mapping(target = "users", source = "users", qualifiedByName = "statisticSubjectUserId")
    @Mapping(target = "leaderBoard", source = "leaderBoard", qualifiedByName = "leaderBoardId")
    LeaderBoardEntryDTO toDto(LeaderBoardEntry s);

    @Named("statisticSubjectUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectUserDTO toDtoStatisticSubjectUserId(StatisticSubjectUser statisticSubjectUser);

    @Named("leaderBoardId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    LeaderBoardDTO toDtoLeaderBoardId(LeaderBoard leaderBoard);
}
