package org.contextmapper.generated.usermanagementcontext.service.mapper;

import org.contextmapper.generated.usermanagementcontext.domain.UserInfos;
import org.contextmapper.generated.usermanagementcontext.domain.UserWaitingForValidationEvent;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserWaitingForValidationEventDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserWaitingForValidationEvent} and its DTO {@link UserWaitingForValidationEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserWaitingForValidationEventMapper extends EntityMapper<UserWaitingForValidationEventDTO, UserWaitingForValidationEvent> {
    @Mapping(target = "userInfos", source = "userInfos", qualifiedByName = "userInfosId")
    UserWaitingForValidationEventDTO toDto(UserWaitingForValidationEvent s);

    @Named("userInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserInfosDTO toDtoUserInfosId(UserInfos userInfos);
}
