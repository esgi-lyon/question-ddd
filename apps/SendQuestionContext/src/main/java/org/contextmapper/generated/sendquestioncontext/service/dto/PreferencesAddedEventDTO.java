package org.contextmapper.generated.sendquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.sendquestioncontext.domain.PreferencesAddedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PreferencesAddedEventDTO implements Serializable {

    private Long id;

    private UserPreferencesDTO preferences;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserPreferencesDTO getPreferences() {
        return preferences;
    }

    public void setPreferences(UserPreferencesDTO preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PreferencesAddedEventDTO)) {
            return false;
        }

        PreferencesAddedEventDTO preferencesAddedEventDTO = (PreferencesAddedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, preferencesAddedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PreferencesAddedEventDTO{" +
            "id=" + getId() +
            ", preferences=" + getPreferences() +
            "}";
    }
}
