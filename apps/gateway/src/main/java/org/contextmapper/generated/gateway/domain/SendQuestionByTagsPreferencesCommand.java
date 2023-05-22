package org.contextmapper.generated.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A SendQuestionByTagsPreferencesCommand.
 */
@Table("send_question_by_tags_preferences_command")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SendQuestionByTagsPreferencesCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private QuestionSent questionSent;

    @Column("question_sent_id")
    private Long questionSentId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SendQuestionByTagsPreferencesCommand id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionSent getQuestionSent() {
        return this.questionSent;
    }

    public void setQuestionSent(QuestionSent questionSent) {
        this.questionSent = questionSent;
        this.questionSentId = questionSent != null ? questionSent.getId() : null;
    }

    public SendQuestionByTagsPreferencesCommand questionSent(QuestionSent questionSent) {
        this.setQuestionSent(questionSent);
        return this;
    }

    public Long getQuestionSentId() {
        return this.questionSentId;
    }

    public void setQuestionSentId(Long questionSent) {
        this.questionSentId = questionSent;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SendQuestionByTagsPreferencesCommand)) {
            return false;
        }
        return id != null && id.equals(((SendQuestionByTagsPreferencesCommand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SendQuestionByTagsPreferencesCommand{" +
            "id=" + getId() +
            "}";
    }
}
