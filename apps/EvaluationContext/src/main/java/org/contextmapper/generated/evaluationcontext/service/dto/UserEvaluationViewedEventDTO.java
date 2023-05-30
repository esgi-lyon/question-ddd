package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.UserEvaluationViewedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserEvaluationViewedEventDTO implements Serializable {

    private Long id;

    private EvaluationDTO evaluation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EvaluationDTO getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(EvaluationDTO evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserEvaluationViewedEventDTO)) {
            return false;
        }

        UserEvaluationViewedEventDTO userEvaluationViewedEventDTO = (UserEvaluationViewedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userEvaluationViewedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserEvaluationViewedEventDTO{" +
            "id=" + getId() +
            ", evaluation=" + getEvaluation() +
            "}";
    }
}
