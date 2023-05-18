package org.contextmapper.generated.statcontext.service.mapper;

import org.contextmapper.generated.statcontext.domain.StatisticSubjectUser;
import org.contextmapper.generated.statcontext.domain.UserStatsViewed;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectUserDTO;
import org.contextmapper.generated.statcontext.service.dto.UserStatsViewedDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserStatsViewed} and its DTO {@link UserStatsViewedDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserStatsViewedMapper extends EntityMapper<UserStatsViewedDTO, UserStatsViewed> {
    @Mapping(target = "user", source = "user", qualifiedByName = "statisticSubjectUserId")
    UserStatsViewedDTO toDto(UserStatsViewed s);

    @Named("statisticSubjectUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StatisticSubjectUserDTO toDtoStatisticSubjectUserId(StatisticSubjectUser statisticSubjectUser);
}
