package org.contextmapper.generated.sendquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AddPreferencesCommand.
 */
@Entity
@Table(name = "add_preferences_command")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AddPreferencesCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties(value = { "userPreferences" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private UserPreferencesTagInfos preferences;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AddPreferencesCommand id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserPreferencesTagInfos getPreferences() {
        return this.preferences;
    }

    public void setPreferences(UserPreferencesTagInfos userPreferencesTagInfos) {
        this.preferences = userPreferencesTagInfos;
    }

    public AddPreferencesCommand preferences(UserPreferencesTagInfos userPreferencesTagInfos) {
        this.setPreferences(userPreferencesTagInfos);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddPreferencesCommand)) {
            return false;
        }
        return id != null && id.equals(((AddPreferencesCommand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddPreferencesCommand{" +
            "id=" + getId() +
            "}";
    }
}
