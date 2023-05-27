package org.contextmapper.generated.sendquestioncontext.service.mapper;

import org.contextmapper.generated.sendquestioncontext.domain.PreferencesAddedEvent;
import org.contextmapper.generated.sendquestioncontext.domain.UserPreferences;
import org.contextmapper.generated.sendquestioncontext.service.dto.PreferencesAddedEventDTO;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserPreferencesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PreferencesAddedEvent} and its DTO {@link PreferencesAddedEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface PreferencesAddedEventMapper extends EntityMapper<PreferencesAddedEventDTO, PreferencesAddedEvent> {
    @Mapping(target = "preferences", source = "preferences", qualifiedByName = "userPreferencesId")
    PreferencesAddedEventDTO toDto(PreferencesAddedEvent s);

    @Named("userPreferencesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserPreferencesDTO toDtoUserPreferencesId(UserPreferences userPreferences);
}
