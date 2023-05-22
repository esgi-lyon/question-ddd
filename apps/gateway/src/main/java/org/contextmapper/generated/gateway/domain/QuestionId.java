package org.contextmapper.generated.gateway.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A QuestionId.
 */
@Table("question_id")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("question_id")
    private Integer questionId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public QuestionId id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return this.questionId;
    }

    public QuestionId questionId(Integer questionId) {
        this.setQuestionId(questionId);
        return this;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionId)) {
            return false;
        }
        return id != null && id.equals(((QuestionId) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionId{" +
            "id=" + getId() +
            ", questionId=" + getQuestionId() +
            "}";
    }
}
