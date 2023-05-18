package org.contextmapper.generated.sendquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.sendquestioncontext.domain.SendQuestionByTagsPreferences} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SendQuestionByTagsPreferencesDTO implements Serializable {

    private Long id;

    private QuestionSentDTO questionSent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionSentDTO getQuestionSent() {
        return questionSent;
    }

    public void setQuestionSent(QuestionSentDTO questionSent) {
        this.questionSent = questionSent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SendQuestionByTagsPreferencesDTO)) {
            return false;
        }

        SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO = (SendQuestionByTagsPreferencesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sendQuestionByTagsPreferencesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SendQuestionByTagsPreferencesDTO{" +
            "id=" + getId() +
            ", questionSent=" + getQuestionSent() +
            "}";
    }
}
