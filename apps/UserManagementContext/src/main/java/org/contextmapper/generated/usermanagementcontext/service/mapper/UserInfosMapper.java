package org.contextmapper.generated.usermanagementcontext.service.mapper;

import org.contextmapper.generated.usermanagementcontext.domain.UserInfos;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserInfosDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserInfos} and its DTO {@link UserInfosDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserInfosMapper extends EntityMapper<UserInfosDTO, UserInfos> {}
