package org.contextmapper.generated.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.gateway.domain.UserPreferencesTagInfos} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserPreferencesTagInfosDTO implements Serializable {

    private Long id;

    private Integer tagId;

    private String name;

    private UserPreferencesDTO userPreferences;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserPreferencesDTO getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(UserPreferencesDTO userPreferences) {
        this.userPreferences = userPreferences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserPreferencesTagInfosDTO)) {
            return false;
        }

        UserPreferencesTagInfosDTO userPreferencesTagInfosDTO = (UserPreferencesTagInfosDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userPreferencesTagInfosDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserPreferencesTagInfosDTO{" +
            "id=" + getId() +
            ", tagId=" + getTagId() +
            ", name='" + getName() + "'" +
            ", userPreferences=" + getUserPreferences() +
            "}";
    }
}
