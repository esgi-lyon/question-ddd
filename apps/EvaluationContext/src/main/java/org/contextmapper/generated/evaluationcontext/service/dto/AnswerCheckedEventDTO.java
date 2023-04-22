package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.AnswerCheckedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnswerCheckedEventDTO implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerCheckedEventDTO)) {
            return false;
        }

        AnswerCheckedEventDTO answerCheckedEventDTO = (AnswerCheckedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, answerCheckedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerCheckedEventDTO{" +
            "id=" + getId() +
            "}";
    }
}
