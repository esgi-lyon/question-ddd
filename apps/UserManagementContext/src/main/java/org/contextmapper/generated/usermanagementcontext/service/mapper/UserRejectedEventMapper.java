package org.contextmapper.generated.usermanagementcontext.service.mapper;

import org.contextmapper.generated.usermanagementcontext.domain.UserInfos;
import org.contextmapper.generated.usermanagementcontext.domain.UserRejectedEvent;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserRejectedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserRejectedEvent} and its DTO {@link UserRejectedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserRejectedEventMapper extends EntityMapper<UserRejectedEventDTO, UserRejectedEvent> {
    @Mapping(target = "userInfos", source = "userInfos", qualifiedByName = "userInfosId")
    UserRejectedEventDTO toDto(UserRejectedEvent s);

    @Named("userInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserInfosDTO toDtoUserInfosId(UserInfos userInfos);
}
