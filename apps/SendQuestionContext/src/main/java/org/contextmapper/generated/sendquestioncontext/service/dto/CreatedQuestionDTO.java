package org.contextmapper.generated.sendquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.sendquestioncontext.domain.CreatedQuestion} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreatedQuestionDTO implements Serializable {

    private Long id;

    private QuestionSentDTO questionAndTag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionSentDTO getQuestionAndTag() {
        return questionAndTag;
    }

    public void setQuestionAndTag(QuestionSentDTO questionAndTag) {
        this.questionAndTag = questionAndTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreatedQuestionDTO)) {
            return false;
        }

        CreatedQuestionDTO createdQuestionDTO = (CreatedQuestionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, createdQuestionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreatedQuestionDTO{" +
            "id=" + getId() +
            ", questionAndTag=" + getQuestionAndTag() +
            "}";
    }
}
