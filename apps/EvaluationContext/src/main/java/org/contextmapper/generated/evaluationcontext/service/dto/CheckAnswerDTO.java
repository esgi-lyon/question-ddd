package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.CheckAnswer} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CheckAnswerDTO implements Serializable {

    private Long id;

    private EvaluatedAnswerDTO answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EvaluatedAnswerDTO getAnswer() {
        return answer;
    }

    public void setAnswer(EvaluatedAnswerDTO answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CheckAnswerDTO)) {
            return false;
        }

        CheckAnswerDTO checkAnswerDTO = (CheckAnswerDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, checkAnswerDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CheckAnswerDTO{" +
            "id=" + getId() +
            ", answer=" + getAnswer() +
            "}";
    }
}
