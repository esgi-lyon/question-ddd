package org.contextmapper.generated.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A CreatedQuestionEvent.
 */
@Table("created_question_event")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreatedQuestionEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private QuestionSent questionAndTag;

    @Column("question_and_tag_id")
    private Long questionAndTagId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CreatedQuestionEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionSent getQuestionAndTag() {
        return this.questionAndTag;
    }

    public void setQuestionAndTag(QuestionSent questionSent) {
        this.questionAndTag = questionSent;
        this.questionAndTagId = questionSent != null ? questionSent.getId() : null;
    }

    public CreatedQuestionEvent questionAndTag(QuestionSent questionSent) {
        this.setQuestionAndTag(questionSent);
        return this;
    }

    public Long getQuestionAndTagId() {
        return this.questionAndTagId;
    }

    public void setQuestionAndTagId(Long questionSent) {
        this.questionAndTagId = questionSent;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreatedQuestionEvent)) {
            return false;
        }
        return id != null && id.equals(((CreatedQuestionEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreatedQuestionEvent{" +
            "id=" + getId() +
            "}";
    }
}
