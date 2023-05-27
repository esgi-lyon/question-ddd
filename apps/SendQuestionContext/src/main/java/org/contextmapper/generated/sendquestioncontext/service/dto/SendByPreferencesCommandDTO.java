package org.contextmapper.generated.sendquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.sendquestioncontext.domain.SendByPreferencesCommand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SendByPreferencesCommandDTO implements Serializable {

    private Long id;

    private QuestionSentDTO questionToSend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionSentDTO getQuestionToSend() {
        return questionToSend;
    }

    public void setQuestionToSend(QuestionSentDTO questionToSend) {
        this.questionToSend = questionToSend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SendByPreferencesCommandDTO)) {
            return false;
        }

        SendByPreferencesCommandDTO sendByPreferencesCommandDTO = (SendByPreferencesCommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sendByPreferencesCommandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SendByPreferencesCommandDTO{" +
            "id=" + getId() +
            ", questionToSend=" + getQuestionToSend() +
            "}";
    }
}
