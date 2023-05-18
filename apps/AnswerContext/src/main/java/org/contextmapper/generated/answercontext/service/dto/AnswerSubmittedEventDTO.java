package org.contextmapper.generated.answercontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.answercontext.domain.AnswerSubmittedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnswerSubmittedEventDTO implements Serializable {

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
        if (!(o instanceof AnswerSubmittedEventDTO)) {
            return false;
        }

        AnswerSubmittedEventDTO answerSubmittedEventDTO = (AnswerSubmittedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, answerSubmittedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerSubmittedEventDTO{" +
            "id=" + getId() +
            ", answer=" + getAnswer() +
            "}";
    }
}
