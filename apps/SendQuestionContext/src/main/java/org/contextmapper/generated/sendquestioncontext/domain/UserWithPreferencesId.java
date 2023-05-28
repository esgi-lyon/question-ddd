package org.contextmapper.generated.sendquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UserWithPreferencesId.
 */
@Entity
@Table(name = "user_with_preferences_id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserWithPreferencesId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "mail")
    private String mail;

    @ManyToOne
    @JsonIgnoreProperties(value = { "question", "users" }, allowSetters = true)
    private NotifiedUsers notifiedUsers;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UserWithPreferencesId id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return this.mail;
    }

    public UserWithPreferencesId mail(String mail) {
        this.setMail(mail);
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public NotifiedUsers getNotifiedUsers() {
        return this.notifiedUsers;
    }

    public void setNotifiedUsers(NotifiedUsers notifiedUsers) {
        this.notifiedUsers = notifiedUsers;
    }

    public UserWithPreferencesId notifiedUsers(NotifiedUsers notifiedUsers) {
        this.setNotifiedUsers(notifiedUsers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserWithPreferencesId)) {
            return false;
        }
        return id != null && id.equals(((UserWithPreferencesId) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserWithPreferencesId{" +
            "id=" + getId() +
            ", mail='" + getMail() + "'" +
            "}";
    }
}
