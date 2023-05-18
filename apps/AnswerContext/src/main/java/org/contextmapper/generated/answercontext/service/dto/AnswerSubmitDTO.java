package org.contextmapper.generated.answercontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.answercontext.domain.AnswerSubmit} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnswerSubmitDTO implements Serializable {

    private Long id;

    private AnswerDTO answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnswerDTO getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerDTO answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerSubmitDTO)) {
            return false;
        }

        AnswerSubmitDTO answerSubmitDTO = (AnswerSubmitDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, answerSubmitDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerSubmitDTO{" +
            "id=" + getId() +
            ", answer=" + getAnswer() +
            "}";
    }
}
