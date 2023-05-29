package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.StatisticSubjectTag;
import org.contextmapper.generated.statcontext.domain.ViewLeaderBoardCommand;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectTagDTO;
import org.contextmapper.generated.statcontext.service.dto.ViewLeaderBoardCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ViewLeaderBoardCommand} and its DTO {@link ViewLeaderBoardCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface ViewLeaderBoardCommandMapper extends EntityMapper<ViewLeaderBoardCommandDTO, ViewLeaderBoardCommand> {
    @Mapping(target = "tag", source = "tag", qualifiedByName = "statisticSubjectTagId")
    ViewLeaderBoardCommandDTO toDto(ViewLeaderBoardCommand s);

    @Named("statisticSubjectTagId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectTagDTO toDtoStatisticSubjectTagId(StatisticSubjectTag statisticSubjectTag);
}
