package org.contextmapper.generated.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A AnswerSubmitCommand.
 */
@Table("answer_submit_command")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnswerSubmitCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private Answer answer;

    @Column("answer_id")
    private Long answerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AnswerSubmitCommand id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Answer getAnswer() {
        return this.answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
        this.answerId = answer != null ? answer.getId() : null;
    }

    public AnswerSubmitCommand answer(Answer answer) {
        this.setAnswer(answer);
        return this;
    }

    public Long getAnswerId() {
        return this.answerId;
    }

    public void setAnswerId(Long answer) {
        this.answerId = answer;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerSubmitCommand)) {
            return false;
        }
        return id != null && id.equals(((AnswerSubmitCommand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerSubmitCommand{" +
            "id=" + getId() +
            "}";
    }
}
