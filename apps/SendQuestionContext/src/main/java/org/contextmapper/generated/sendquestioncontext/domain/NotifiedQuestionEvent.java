package org.contextmapper.generated.sendquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NotifiedQuestionEvent.
 */
@Entity
@Table(name = "notified_question_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NotifiedQuestionEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties(value = { "question", "users" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private NotifiedUsers questionResource;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public NotifiedQuestionEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotifiedUsers getQuestionResource() {
        return this.questionResource;
    }

    public void setQuestionResource(NotifiedUsers notifiedUsers) {
        this.questionResource = notifiedUsers;
    }

    public NotifiedQuestionEvent questionResource(NotifiedUsers notifiedUsers) {
        this.setQuestionResource(notifiedUsers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotifiedQuestionEvent)) {
            return false;
        }
        return id != null && id.equals(((NotifiedQuestionEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotifiedQuestionEvent{" +
            "id=" + getId() +
            "}";
    }
}
