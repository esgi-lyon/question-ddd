package org.contextmapper.generated.sendquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NotifiedUsers.
 */
@Entity
@Table(name = "notified_users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NotifiedUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties(value = { "resourceId", "tags" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private QuestionSent question;

    @OneToMany(mappedBy = "notifiedUsers")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "notifiedUsers" }, allowSetters = true)
    private Set<UserWithPreferencesId> users = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public NotifiedUsers id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionSent getQuestion() {
        return this.question;
    }

    public void setQuestion(QuestionSent questionSent) {
        this.question = questionSent;
    }

    public NotifiedUsers question(QuestionSent questionSent) {
        this.setQuestion(questionSent);
        return this;
    }

    public Set<UserWithPreferencesId> getUsers() {
        return this.users;
    }

    public void setUsers(Set<UserWithPreferencesId> userWithPreferencesIds) {
        if (this.users != null) {
            this.users.forEach(i -> i.setNotifiedUsers(null));
        }
        if (userWithPreferencesIds != null) {
            userWithPreferencesIds.forEach(i -> i.setNotifiedUsers(this));
        }
        this.users = userWithPreferencesIds;
    }

    public NotifiedUsers users(Set<UserWithPreferencesId> userWithPreferencesIds) {
        this.setUsers(userWithPreferencesIds);
        return this;
    }

    public NotifiedUsers addUser(UserWithPreferencesId userWithPreferencesId) {
        this.users.add(userWithPreferencesId);
        userWithPreferencesId.setNotifiedUsers(this);
        return this;
    }

    public NotifiedUsers removeUser(UserWithPreferencesId userWithPreferencesId) {
        this.users.remove(userWithPreferencesId);
        userWithPreferencesId.setNotifiedUsers(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotifiedUsers)) {
            return false;
        }
        return id != null && id.equals(((NotifiedUsers) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotifiedUsers{" +
            "id=" + getId() +
            "}";
    }
}
