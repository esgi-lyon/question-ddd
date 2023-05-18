package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.UserPreferences;
import org.contextmapper.generated.sendquestioncontext.domain.UserWithPreferencesId;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserPreferencesDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserWithPreferencesIdDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserPreferences} and its DTO {@link UserPreferencesDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserPreferencesMapper extends EntityMapper<UserPreferencesDTO, UserPreferences> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userWithPreferencesIdId")
    UserPreferencesDTO toDto(UserPreferences s);

    @Named("userWithPreferencesIdId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserWithPreferencesIdDTO toDtoUserWithPreferencesIdId(UserWithPreferencesId userWithPreferencesId);
}
