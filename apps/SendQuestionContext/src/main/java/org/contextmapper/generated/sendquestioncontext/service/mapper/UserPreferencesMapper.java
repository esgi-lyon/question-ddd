package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.UserPreferences;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserPreferencesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserPreferences} and its DTO {@link UserPreferencesDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserPreferencesMapper extends EntityMapper<UserPreferencesDTO, UserPreferences> {}
