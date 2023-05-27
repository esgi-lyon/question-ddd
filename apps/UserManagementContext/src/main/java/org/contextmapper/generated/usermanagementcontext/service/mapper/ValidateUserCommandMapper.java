package org.contextmapper.generated.usermanagementcontext.service.mapper;

import org.contextmapper.generated.usermanagementcontext.domain.UserInfos;
import org.contextmapper.generated.usermanagementcontext.domain.ValidateUserCommand;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.ValidateUserCommandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ValidateUserCommand} and its DTO {@link ValidateUserCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface ValidateUserCommandMapper extends EntityMapper<ValidateUserCommandDTO, ValidateUserCommand> {
    @Mapping(target = "userInfos", source = "userInfos", qualifiedByName = "userInfosId")
    ValidateUserCommandDTO toDto(ValidateUserCommand s);

    @Named("userInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserInfosDTO toDtoUserInfosId(UserInfos userInfos);
}
