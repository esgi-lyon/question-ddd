package org.contextmapper.generated.gateway.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A AnswerCheckedEvent.
 */
@Table("answer_checked_event")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnswerCheckedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private EvaluatedAnswer answer;

    @Column("answer_id")
    private Long answerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AnswerCheckedEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EvaluatedAnswer getAnswer() {
        return this.answer;
    }

    public void setAnswer(EvaluatedAnswer evaluatedAnswer) {
        this.answer = evaluatedAnswer;
        this.answerId = evaluatedAnswer != null ? evaluatedAnswer.getId() : null;
    }

    public AnswerCheckedEvent answer(EvaluatedAnswer evaluatedAnswer) {
        this.setAnswer(evaluatedAnswer);
        return this;
    }

    public Long getAnswerId() {
        return this.answerId;
    }

    public void setAnswerId(Long evaluatedAnswer) {
        this.answerId = evaluatedAnswer;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerCheckedEvent)) {
            return false;
        }
        return id != null && id.equals(((AnswerCheckedEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerCheckedEvent{" +
            "id=" + getId() +
            "}";
    }
}
