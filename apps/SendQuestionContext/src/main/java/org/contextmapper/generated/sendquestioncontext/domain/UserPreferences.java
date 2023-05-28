package org.contextmapper.generated.sendquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UserPreferences.
 */
@Entity
@Table(name = "user_preferences")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserPreferences implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties(value = { "notifiedUsers" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private UserWithPreferencesId user;

    @OneToMany(mappedBy = "userPreferences")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "userPreferences" }, allowSetters = true)
    private Set<UserPreferencesTagInfos> preferences = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UserPreferences id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserWithPreferencesId getUser() {
        return this.user;
    }

    public void setUser(UserWithPreferencesId userWithPreferencesId) {
        this.user = userWithPreferencesId;
    }

    public UserPreferences user(UserWithPreferencesId userWithPreferencesId) {
        this.setUser(userWithPreferencesId);
        return this;
    }

    public Set<UserPreferencesTagInfos> getPreferences() {
        return this.preferences;
    }

    public void setPreferences(Set<UserPreferencesTagInfos> userPreferencesTagInfos) {
        if (this.preferences != null) {
            this.preferences.forEach(i -> i.setUserPreferences(null));
        }
        if (userPreferencesTagInfos != null) {
            userPreferencesTagInfos.forEach(i -> i.setUserPreferences(this));
        }
        this.preferences = userPreferencesTagInfos;
    }

    public UserPreferences preferences(Set<UserPreferencesTagInfos> userPreferencesTagInfos) {
        this.setPreferences(userPreferencesTagInfos);
        return this;
    }

    public UserPreferences addPreferences(UserPreferencesTagInfos userPreferencesTagInfos) {
        this.preferences.add(userPreferencesTagInfos);
        userPreferencesTagInfos.setUserPreferences(this);
        return this;
    }

    public UserPreferences removePreferences(UserPreferencesTagInfos userPreferencesTagInfos) {
        this.preferences.remove(userPreferencesTagInfos);
        userPreferencesTagInfos.setUserPreferences(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserPreferences)) {
            return false;
        }
        return id != null && id.equals(((UserPreferences) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserPreferences{" +
            "id=" + getId() +
            "}";
    }
}
