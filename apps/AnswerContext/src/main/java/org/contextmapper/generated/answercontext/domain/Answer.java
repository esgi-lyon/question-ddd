package org.contextmapper.generated.answercontext.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.contextmapper.generated.answercontext.domain.enumeration.AnswerState;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_state")
    private AnswerState answerState;

    @OneToOne
    @JoinColumn(unique = true)
    private QuestionSentId question;

    @OneToOne
    @JoinColumn(unique = true)
    private AnsweredTag answeredTag;

    @OneToOne
    @JoinColumn(unique = true)
    private UserEmail userEmail;

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

    public AnswerState getAnswerState() {
        return this.answerState;
    }

    public Answer answerState(AnswerState answerState) {
        this.setAnswerState(answerState);
        return this;
    }

    public void setAnswerState(AnswerState answerState) {
        this.answerState = answerState;
    }

    public QuestionSentId getQuestion() {
        return this.question;
    }

    public void setQuestion(QuestionSentId questionSentId) {
        this.question = questionSentId;
    }

    public Answer question(QuestionSentId questionSentId) {
        this.setQuestion(questionSentId);
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

    public UserEmail getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(UserEmail userEmail) {
        this.userEmail = userEmail;
    }

    public Answer userEmail(UserEmail userEmail) {
        this.setUserEmail(userEmail);
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
            ", answerState='" + getAnswerState() + "'" +
            "}";
    }
}
