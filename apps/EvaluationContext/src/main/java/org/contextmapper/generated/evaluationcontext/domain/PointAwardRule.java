package org.contextmapper.generated.evaluationcontext.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.DifficultyLevel;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.UserLevel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PointAwardRule.
 */
@Entity
@Table(name = "point_award_rule")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PointAwardRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "score_evolution")
    private Integer scoreEvolution;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty_level")
    private DifficultyLevel difficultyLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_level")
    private UserLevel userLevel;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PointAwardRule id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScoreEvolution() {
        return this.scoreEvolution;
    }

    public PointAwardRule scoreEvolution(Integer scoreEvolution) {
        this.setScoreEvolution(scoreEvolution);
        return this;
    }

    public void setScoreEvolution(Integer scoreEvolution) {
        this.scoreEvolution = scoreEvolution;
    }

    public DifficultyLevel getDifficultyLevel() {
        return this.difficultyLevel;
    }

    public PointAwardRule difficultyLevel(DifficultyLevel difficultyLevel) {
        this.setDifficultyLevel(difficultyLevel);
        return this;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public UserLevel getUserLevel() {
        return this.userLevel;
    }

    public PointAwardRule userLevel(UserLevel userLevel) {
        this.setUserLevel(userLevel);
        return this;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PointAwardRule)) {
            return false;
        }
        return id != null && id.equals(((PointAwardRule) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PointAwardRule{" +
            "id=" + getId() +
            ", scoreEvolution=" + getScoreEvolution() +
            ", difficultyLevel='" + getDifficultyLevel() + "'" +
            ", userLevel='" + getUserLevel() + "'" +
            "}";
    }
}
