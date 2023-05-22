package org.contextmapper.generated.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A EvaluationId.
 */
@Table("evaluation_id")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvaluationId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("evaluation_id")
    private Integer evaluationId;

    @Transient
    @JsonIgnoreProperties(value = { "tagId", "evaluations" }, allowSetters = true)
    private LeaderBoard leaderBoard;

    @Column("leader_board_id")
    private Long leaderBoardId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EvaluationId id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEvaluationId() {
        return this.evaluationId;
    }

    public EvaluationId evaluationId(Integer evaluationId) {
        this.setEvaluationId(evaluationId);
        return this;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public LeaderBoard getLeaderBoard() {
        return this.leaderBoard;
    }

    public void setLeaderBoard(LeaderBoard leaderBoard) {
        this.leaderBoard = leaderBoard;
        this.leaderBoardId = leaderBoard != null ? leaderBoard.getId() : null;
    }

    public EvaluationId leaderBoard(LeaderBoard leaderBoard) {
        this.setLeaderBoard(leaderBoard);
        return this;
    }

    public Long getLeaderBoardId() {
        return this.leaderBoardId;
    }

    public void setLeaderBoardId(Long leaderBoard) {
        this.leaderBoardId = leaderBoard;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvaluationId)) {
            return false;
        }
        return id != null && id.equals(((EvaluationId) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvaluationId{" +
            "id=" + getId() +
            ", evaluationId=" + getEvaluationId() +
            "}";
    }
}
