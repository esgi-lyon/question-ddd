package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.LeaderBoard;
import org.contextmapper.generated.statcontext.domain.StatisticSubjectTag;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardDTO;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectTagDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LeaderBoard} and its DTO {@link LeaderBoardDTO}.
 */
@Mapper(componentModel = "spring")
public interface LeaderBoardMapper extends EntityMapper<LeaderBoardDTO, LeaderBoard> {
    @Mapping(target = "tagId", source = "tagId", qualifiedByName = "statisticSubjectTagId")
    LeaderBoardDTO toDto(LeaderBoard s);

    @Named("statisticSubjectTagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectTagDTO toDtoStatisticSubjectTagId(StatisticSubjectTag statisticSubjectTag);
}
