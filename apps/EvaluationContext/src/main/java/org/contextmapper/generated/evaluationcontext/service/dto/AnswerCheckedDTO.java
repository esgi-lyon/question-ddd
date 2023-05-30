package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.AnswerChecked} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnswerCheckedDTO implements Serializable {

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
        if (!(o instanceof AnswerCheckedDTO)) {
            return false;
        }

        AnswerCheckedDTO answerCheckedDTO = (AnswerCheckedDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, answerCheckedDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerCheckedDTO{" +
            "id=" + getId() +
            ", answer=" + getAnswer() +
            "}";
    }
}
