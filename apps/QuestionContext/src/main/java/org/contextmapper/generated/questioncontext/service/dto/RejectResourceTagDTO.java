package org.contextmapper.generated.questioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.questioncontext.domain.RejectResourceTag} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RejectResourceTagDTO implements Serializable {

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
        if (!(o instanceof RejectResourceTagDTO)) {
            return false;
        }

        RejectResourceTagDTO rejectResourceTagDTO = (RejectResourceTagDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, rejectResourceTagDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RejectResourceTagDTO{" +
            "id=" + getId() +
            ", questionId=" + getQuestionId() +
            "}";
    }
}
