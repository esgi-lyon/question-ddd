package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.AwardedPoint} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AwardedPointDTO implements Serializable {

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
        if (!(o instanceof AwardedPointDTO)) {
            return false;
        }

        AwardedPointDTO awardedPointDTO = (AwardedPointDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, awardedPointDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AwardedPointDTO{" +
            "id=" + getId() +
            ", answer=" + getAnswer() +
            "}";
    }
}
