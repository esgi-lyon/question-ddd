package org.contextmapper.generated.statcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.statcontext.domain.QuestionStatsViewedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionStatsViewedEventDTO implements Serializable {

    private Long id;

    private EvaluationStatsDTO stat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EvaluationStatsDTO getStat() {
        return stat;
    }

    public void setStat(EvaluationStatsDTO stat) {
        this.stat = stat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionStatsViewedEventDTO)) {
            return false;
        }

        QuestionStatsViewedEventDTO questionStatsViewedEventDTO = (QuestionStatsViewedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionStatsViewedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionStatsViewedEventDTO{" +
            "id=" + getId() +
            ", stat=" + getStat() +
            "}";
    }
}
