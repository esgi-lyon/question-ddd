package org.contextmapper.generated.sendquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.sendquestioncontext.domain.NotifiedQuestion} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NotifiedQuestionDTO implements Serializable {

    private Long id;

    private QuestionSentDTO questionResource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionSentDTO getQuestionResource() {
        return questionResource;
    }

    public void setQuestionResource(QuestionSentDTO questionResource) {
        this.questionResource = questionResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotifiedQuestionDTO)) {
            return false;
        }

        NotifiedQuestionDTO notifiedQuestionDTO = (NotifiedQuestionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, notifiedQuestionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotifiedQuestionDTO{" +
            "id=" + getId() +
            ", questionResource=" + getQuestionResource() +
            "}";
    }
}
