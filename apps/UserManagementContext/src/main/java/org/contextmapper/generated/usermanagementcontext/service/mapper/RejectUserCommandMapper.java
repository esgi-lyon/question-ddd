package org.contextmapper.generated.usermanagementcontext.service.mapper;

import org.contextmapper.generated.usermanagementcontext.domain.RejectUserCommand;
import org.contextmapper.generated.usermanagementcontext.domain.UserInfos;
import org.contextmapper.generated.usermanagementcontext.service.dto.RejectUserCommandDTO;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RejectUserCommand} and its DTO {@link RejectUserCommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface RejectUserCommandMapper extends EntityMapper<RejectUserCommandDTO, RejectUserCommand> {
    @Mapping(target = "userInfos", source = "userInfos", qualifiedByName = "userInfosId")
    RejectUserCommandDTO toDto(RejectUserCommand s);

    @Named("userInfosId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserInfosDTO toDtoUserInfosId(UserInfos userInfos);
}
