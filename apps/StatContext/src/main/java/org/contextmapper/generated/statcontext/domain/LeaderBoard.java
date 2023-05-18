package org.contextmapper.generated.statcontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.contextmapper.generated.statcontext.domain.enumeration.DifficultyLevel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LeaderBoard.
 */
@Entity
@Table(name = "leader_board")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LeaderBoard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level")
    private DifficultyLevel difficultyLevel;

    @OneToOne
    @JoinColumn(unique = true)
    private StatisticSubjectTag tagId;

    @OneToMany(mappedBy = "leaderBoard")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "leaderBoard" }, allowSetters = true)
    private Set<EvaluationId> evaluations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LeaderBoard id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DifficultyLevel getDifficultyLevel() {
        return this.difficultyLevel;
    }

    public LeaderBoard difficultyLevel(DifficultyLevel difficultyLevel) {
        this.setDifficultyLevel(difficultyLevel);
        return this;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public StatisticSubjectTag getTagId() {
        return this.tagId;
    }

    public void setTagId(StatisticSubjectTag statisticSubjectTag) {
        this.tagId = statisticSubjectTag;
    }

    public LeaderBoard tagId(StatisticSubjectTag statisticSubjectTag) {
        this.setTagId(statisticSubjectTag);
        return this;
    }

    public Set<EvaluationId> getEvaluations() {
        return this.evaluations;
    }

    public void setEvaluations(Set<EvaluationId> evaluationIds) {
        if (this.evaluations != null) {
            this.evaluations.forEach(i -> i.setLeaderBoard(null));
        }
        if (evaluationIds != null) {
            evaluationIds.forEach(i -> i.setLeaderBoard(this));
        }
        this.evaluations = evaluationIds;
    }

    public LeaderBoard evaluations(Set<EvaluationId> evaluationIds) {
        this.setEvaluations(evaluationIds);
        return this;
    }

    public LeaderBoard addEvaluation(EvaluationId evaluationId) {
        this.evaluations.add(evaluationId);
        evaluationId.setLeaderBoard(this);
        return this;
    }

    public LeaderBoard removeEvaluation(EvaluationId evaluationId) {
        this.evaluations.remove(evaluationId);
        evaluationId.setLeaderBoard(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LeaderBoard)) {
            return false;
        }
        return id != null && id.equals(((LeaderBoard) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeaderBoard{" +
            "id=" + getId() +
            ", difficultyLevel='" + getDifficultyLevel() + "'" +
            "}";
    }
}
