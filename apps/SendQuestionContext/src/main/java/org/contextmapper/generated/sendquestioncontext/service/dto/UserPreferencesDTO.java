package org.contextmapper.generated.sendquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.sendquestioncontext.domain.UserPreferences} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserPreferencesDTO implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserPreferencesDTO)) {
            return false;
        }

        UserPreferencesDTO userPreferencesDTO = (UserPreferencesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userPreferencesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserPreferencesDTO{" +
            "id=" + getId() +
            "}";
    }
}
