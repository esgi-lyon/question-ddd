package org.contextmapper.generated.usermanagementcontext.service.mapper;

import org.contextmapper.generated.usermanagementcontext.domain.UserInfos;
import org.contextmapper.generated.usermanagementcontext.domain.UserViewedEvent;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserViewedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserViewedEvent} and its DTO {@link UserViewedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserViewedEventMapper extends EntityMapper<UserViewedEventDTO, UserViewedEvent> {
    @Mapping(target = "userInfos", source = "userInfos", qualifiedByName = "userInfosId")
    UserViewedEventDTO toDto(UserViewedEvent s);

    @Named("userInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserInfosDTO toDtoUserInfosId(UserInfos userInfos);
}
