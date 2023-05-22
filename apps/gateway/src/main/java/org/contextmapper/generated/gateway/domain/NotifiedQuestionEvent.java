package org.contextmapper.generated.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A NotifiedQuestionEvent.
 */
@Table("notified_question_event")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NotifiedQuestionEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private QuestionSent questionResource;

    @Column("question_resource_id")
    private Long questionResourceId;

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

    public QuestionSent getQuestionResource() {
        return this.questionResource;
    }

    public void setQuestionResource(QuestionSent questionSent) {
        this.questionResource = questionSent;
        this.questionResourceId = questionSent != null ? questionSent.getId() : null;
    }

    public NotifiedQuestionEvent questionResource(QuestionSent questionSent) {
        this.setQuestionResource(questionSent);
        return this;
    }

    public Long getQuestionResourceId() {
        return this.questionResourceId;
    }

    public void setQuestionResourceId(Long questionSent) {
        this.questionResourceId = questionSent;
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
