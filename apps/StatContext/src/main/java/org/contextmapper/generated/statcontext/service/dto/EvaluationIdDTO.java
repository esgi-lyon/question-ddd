package org.contextmapper.generated.statcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.statcontext.domain.EvaluationId} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvaluationIdDTO implements Serializable {

    private Long id;

    private Long evaluationId;

    private EvaluationStatsDTO evaluationStats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public EvaluationStatsDTO getEvaluationStats() {
        return evaluationStats;
    }

    public void setEvaluationStats(EvaluationStatsDTO evaluationStats) {
        this.evaluationStats = evaluationStats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvaluationIdDTO)) {
            return false;
        }

        EvaluationIdDTO evaluationIdDTO = (EvaluationIdDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, evaluationIdDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvaluationIdDTO{" +
            "id=" + getId() +
            ", evaluationId=" + getEvaluationId() +
            ", evaluationStats=" + getEvaluationStats() +
            "}";
    }
}
