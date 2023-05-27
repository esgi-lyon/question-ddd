package org.contextmapper.generated.sendquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.sendquestioncontext.domain.AddPreferencesCommand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AddPreferencesCommandDTO implements Serializable {

    private Long id;

    private UserPreferencesTagInfosDTO preferences;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserPreferencesTagInfosDTO getPreferences() {
        return preferences;
    }

    public void setPreferences(UserPreferencesTagInfosDTO preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddPreferencesCommandDTO)) {
            return false;
        }

        AddPreferencesCommandDTO addPreferencesCommandDTO = (AddPreferencesCommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, addPreferencesCommandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddPreferencesCommandDTO{" +
            "id=" + getId() +
            ", preferences=" + getPreferences() +
            "}";
    }
}
