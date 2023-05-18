package org.contextmapper.generated.statcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.statcontext.domain.EvaluationId} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvaluationIdDTO implements Serializable {

    private Long id;

    private Integer evaluationId;

    private LeaderBoardDTO leaderBoard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public LeaderBoardDTO getLeaderBoard() {
        return leaderBoard;
    }

    public void setLeaderBoard(LeaderBoardDTO leaderBoard) {
        this.leaderBoard = leaderBoard;
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
            ", leaderBoard=" + getLeaderBoard() +
            "}";
    }
}
