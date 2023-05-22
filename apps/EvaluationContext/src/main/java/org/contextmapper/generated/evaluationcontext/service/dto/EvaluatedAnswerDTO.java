package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvaluatedAnswerDTO implements Serializable {

    private Long id;

    private Long answerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvaluatedAnswerDTO)) {
            return false;
        }

        EvaluatedAnswerDTO evaluatedAnswerDTO = (EvaluatedAnswerDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, evaluatedAnswerDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvaluatedAnswerDTO{" +
            "id=" + getId() +
            ", answerId=" + getAnswerId() +
            "}";
    }
}
