package org.contextmapper.generated.answercontext.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Answer.
 */
@Entity
@Table(name = "answer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "jhi_user")
    private Integer user;

    @OneToOne
    @JoinColumn(unique = true)
    private QuestionId question;

    @OneToOne
    @JoinColumn(unique = true)
    private AnsweredTag answeredTag;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Answer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUser() {
        return this.user;
    }

    public Answer user(Integer user) {
        this.setUser(user);
        return this;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public QuestionId getQuestion() {
        return this.question;
    }

    public void setQuestion(QuestionId questionId) {
        this.question = questionId;
    }

    public Answer question(QuestionId questionId) {
        this.setQuestion(questionId);
        return this;
    }

    public AnsweredTag getAnsweredTag() {
        return this.answeredTag;
    }

    public void setAnsweredTag(AnsweredTag answeredTag) {
        this.answeredTag = answeredTag;
    }

    public Answer answeredTag(AnsweredTag answeredTag) {
        this.setAnsweredTag(answeredTag);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Answer)) {
            return false;
        }
        return id != null && id.equals(((Answer) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Answer{" +
            "id=" + getId() +
            ", user=" + getUser() +
            "}";
    }
}
