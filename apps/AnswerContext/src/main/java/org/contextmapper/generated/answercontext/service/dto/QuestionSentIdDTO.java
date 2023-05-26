package org.contextmapper.generated.answercontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.answercontext.domain.QuestionSentId} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionSentIdDTO implements Serializable {

    private Long id;

    private Long questionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionSentIdDTO)) {
            return false;
        }

        QuestionSentIdDTO questionSentIdDTO = (QuestionSentIdDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionSentIdDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionSentIdDTO{" +
            "id=" + getId() +
            ", questionId=" + getQuestionId() +
            "}";
    }
}
