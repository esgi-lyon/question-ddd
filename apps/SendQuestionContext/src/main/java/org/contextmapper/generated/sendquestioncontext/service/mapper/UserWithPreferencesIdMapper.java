package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.NotifiedUsers;
import org.contextmapper.generated.sendquestioncontext.domain.UserWithPreferencesId;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedUsersDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserWithPreferencesIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserWithPreferencesId} and its DTO {@link UserWithPreferencesIdDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserWithPreferencesIdMapper extends EntityMapper<UserWithPreferencesIdDTO, UserWithPreferencesId> {
    @Mapping(target = "notifiedUsers", source = "notifiedUsers", qualifiedByName = "notifiedUsersId")
    UserWithPreferencesIdDTO toDto(UserWithPreferencesId s);

    @Named("notifiedUsersId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NotifiedUsersDTO toDtoNotifiedUsersId(NotifiedUsers notifiedUsers);
}
