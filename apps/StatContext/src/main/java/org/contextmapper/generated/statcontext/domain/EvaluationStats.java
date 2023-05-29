package org.contextmapper.generated.statcontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EvaluationStats.
 */
@Entity
@Table(name = "evaluation_stats")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvaluationStats implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "evaluationStats")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "evaluationStats" }, allowSetters = true)
    private Set<EvaluationId> evaluations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EvaluationStats id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<EvaluationId> getEvaluations() {
        return this.evaluations;
    }

    public void setEvaluations(Set<EvaluationId> evaluationIds) {
        if (this.evaluations != null) {
            this.evaluations.forEach(i -> i.setEvaluationStats(null));
        }
        if (evaluationIds != null) {
            evaluationIds.forEach(i -> i.setEvaluationStats(this));
        }
        this.evaluations = evaluationIds;
    }

    public EvaluationStats evaluations(Set<EvaluationId> evaluationIds) {
        this.setEvaluations(evaluationIds);
        return this;
    }

    public EvaluationStats addEvaluation(EvaluationId evaluationId) {
        this.evaluations.add(evaluationId);
        evaluationId.setEvaluationStats(this);
        return this;
    }

    public EvaluationStats removeEvaluation(EvaluationId evaluationId) {
        this.evaluations.remove(evaluationId);
        evaluationId.setEvaluationStats(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvaluationStats)) {
            return false;
        }
        return id != null && id.equals(((EvaluationStats) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvaluationStats{" +
            "id=" + getId() +
            "}";
    }
}
