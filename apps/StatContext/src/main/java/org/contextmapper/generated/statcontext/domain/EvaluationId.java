package org.contextmapper.generated.statcontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EvaluationId.
 */
@Entity
@Table(name = "evaluation_id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvaluationId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "evaluation_id")
    private Long evaluationId;

    @ManyToOne
    @JsonIgnoreProperties(value = { "evaluations" }, allowSetters = true)
    private EvaluationStats evaluationStats;

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

    public Long getEvaluationId() {
        return this.evaluationId;
    }

    public EvaluationId evaluationId(Long evaluationId) {
        this.setEvaluationId(evaluationId);
        return this;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public EvaluationStats getEvaluationStats() {
        return this.evaluationStats;
    }

    public void setEvaluationStats(EvaluationStats evaluationStats) {
        this.evaluationStats = evaluationStats;
    }

    public EvaluationId evaluationStats(EvaluationStats evaluationStats) {
        this.setEvaluationStats(evaluationStats);
        return this;
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
