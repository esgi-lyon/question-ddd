package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.AwardedPointEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AwardedPointEventDTO implements Serializable {

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
        if (!(o instanceof AwardedPointEventDTO)) {
            return false;
        }

        AwardedPointEventDTO awardedPointEventDTO = (AwardedPointEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, awardedPointEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AwardedPointEventDTO{" +
            "id=" + getId() +
            ", answer=" + getAnswer() +
            "}";
    }
}
