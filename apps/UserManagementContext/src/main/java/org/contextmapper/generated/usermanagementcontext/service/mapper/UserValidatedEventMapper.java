package org.contextmapper.generated.usermanagementcontext.service.mapper;

import org.contextmapper.generated.usermanagementcontext.domain.UserInfos;
import org.contextmapper.generated.usermanagementcontext.domain.UserValidatedEvent;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserValidatedEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserValidatedEvent} and its DTO {@link UserValidatedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserValidatedEventMapper extends EntityMapper<UserValidatedEventDTO, UserValidatedEvent> {
    @Mapping(target = "userInfos", source = "userInfos", qualifiedByName = "userInfosId")
    UserValidatedEventDTO toDto(UserValidatedEvent s);

    @Named("userInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserInfosDTO toDtoUserInfosId(UserInfos userInfos);
}
