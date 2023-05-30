package org.contextmapper.generated.statcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.statcontext.domain.EvaluationStats} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvaluationStatsDTO implements Serializable {

    private Long id;

    private Integer total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvaluationStatsDTO)) {
            return false;
        }

        EvaluationStatsDTO evaluationStatsDTO = (EvaluationStatsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, evaluationStatsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvaluationStatsDTO{" +
            "id=" + getId() +
            ", total=" + getTotal() +
            "}";
    }
}
