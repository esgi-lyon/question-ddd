package org.contextmapper.generated.questioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.questioncontext.domain.QuestionResourceTag} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionResourceTagDTO implements Serializable {

    private Long id;

    private QuestionResourceDTO questionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionResourceDTO getQuestionId() {
        return questionId;
    }

    public void setQuestionId(QuestionResourceDTO questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionResourceTagDTO)) {
            return false;
        }

        QuestionResourceTagDTO questionResourceTagDTO = (QuestionResourceTagDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionResourceTagDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionResourceTagDTO{" +
            "id=" + getId() +
            ", questionId=" + getQuestionId() +
            "}";
    }
}
