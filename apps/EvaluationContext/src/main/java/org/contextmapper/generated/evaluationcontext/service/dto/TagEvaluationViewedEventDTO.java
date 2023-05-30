package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.TagEvaluationViewedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TagEvaluationViewedEventDTO implements Serializable {

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
        if (!(o instanceof TagEvaluationViewedEventDTO)) {
            return false;
        }

        TagEvaluationViewedEventDTO tagEvaluationViewedEventDTO = (TagEvaluationViewedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tagEvaluationViewedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TagEvaluationViewedEventDTO{" +
            "id=" + getId() +
            ", evaluation=" + getEvaluation() +
            "}";
    }
}
