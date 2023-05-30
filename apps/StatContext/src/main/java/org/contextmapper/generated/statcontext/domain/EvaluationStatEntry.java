package org.contextmapper.generated.statcontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EvaluationStatEntry.
 */
@Entity
@Table(name = "evaluation_stat_entry")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvaluationStatEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "evaluation_id")
    private Long evaluationId;

    @Column(name = "score")
    private Integer score;

    @OneToOne
    @JoinColumn(unique = true)
    private StatisticSubjectUser user;

    @OneToOne
    @JoinColumn(unique = true)
    private StatisticSubjectQuestion question;

    @ManyToOne
    @JsonIgnoreProperties(value = { "evaluations" }, allowSetters = true)
    private EvaluationStats evaluationStats;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EvaluationStatEntry id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEvaluationId() {
        return this.evaluationId;
    }

    public EvaluationStatEntry evaluationId(Long evaluationId) {
        this.setEvaluationId(evaluationId);
        return this;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Integer getScore() {
        return this.score;
    }

    public EvaluationStatEntry score(Integer score) {
        this.setScore(score);
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public StatisticSubjectUser getUser() {
        return this.user;
    }

    public void setUser(StatisticSubjectUser statisticSubjectUser) {
        this.user = statisticSubjectUser;
    }

    public EvaluationStatEntry user(StatisticSubjectUser statisticSubjectUser) {
        this.setUser(statisticSubjectUser);
        return this;
    }

    public StatisticSubjectQuestion getQuestion() {
        return this.question;
    }

    public void setQuestion(StatisticSubjectQuestion statisticSubjectQuestion) {
        this.question = statisticSubjectQuestion;
    }

    public EvaluationStatEntry question(StatisticSubjectQuestion statisticSubjectQuestion) {
        this.setQuestion(statisticSubjectQuestion);
        return this;
    }

    public EvaluationStats getEvaluationStats() {
        return this.evaluationStats;
    }

    public void setEvaluationStats(EvaluationStats evaluationStats) {
        this.evaluationStats = evaluationStats;
    }

    public EvaluationStatEntry evaluationStats(EvaluationStats evaluationStats) {
        this.setEvaluationStats(evaluationStats);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvaluationStatEntry)) {
            return false;
        }
        return id != null && id.equals(((EvaluationStatEntry) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvaluationStatEntry{" +
            "id=" + getId() +
            ", evaluationId=" + getEvaluationId() +
            ", score=" + getScore() +
            "}";
    }
}
