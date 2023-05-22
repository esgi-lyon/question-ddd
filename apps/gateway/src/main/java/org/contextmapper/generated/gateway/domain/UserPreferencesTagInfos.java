package org.contextmapper.generated.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A UserPreferencesTagInfos.
 */
@Table("user_preferences_tag_infos")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserPreferencesTagInfos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("tag_id")
    private Integer tagId;

    @Column("name")
    private String name;

    @Transient
    @JsonIgnoreProperties(value = { "user", "preferences" }, allowSetters = true)
    private UserPreferences userPreferences;

    @Column("user_preferences_id")
    private Long userPreferencesId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UserPreferencesTagInfos id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTagId() {
        return this.tagId;
    }

    public UserPreferencesTagInfos tagId(Integer tagId) {
        this.setTagId(tagId);
        return this;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return this.name;
    }

    public UserPreferencesTagInfos name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserPreferences getUserPreferences() {
        return this.userPreferences;
    }

    public void setUserPreferences(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
        this.userPreferencesId = userPreferences != null ? userPreferences.getId() : null;
    }

    public UserPreferencesTagInfos userPreferences(UserPreferences userPreferences) {
        this.setUserPreferences(userPreferences);
        return this;
    }

    public Long getUserPreferencesId() {
        return this.userPreferencesId;
    }

    public void setUserPreferencesId(Long userPreferences) {
        this.userPreferencesId = userPreferences;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserPreferencesTagInfos)) {
            return false;
        }
        return id != null && id.equals(((UserPreferencesTagInfos) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserPreferencesTagInfos{" +
            "id=" + getId() +
            ", tagId=" + getTagId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
