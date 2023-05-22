package org.contextmapper.generated.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A EvaluationCreatedEvent.
 */
@Table("evaluation_created_event")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvaluationCreatedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private Evaluation evaluation;

    @Column("evaluation_id")
    private Long evaluationId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EvaluationCreatedEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evaluation getEvaluation() {
        return this.evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
        this.evaluationId = evaluation != null ? evaluation.getId() : null;
    }

    public EvaluationCreatedEvent evaluation(Evaluation evaluation) {
        this.setEvaluation(evaluation);
        return this;
    }

    public Long getEvaluationId() {
        return this.evaluationId;
    }

    public void setEvaluationId(Long evaluation) {
        this.evaluationId = evaluation;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvaluationCreatedEvent)) {
            return false;
        }
        return id != null && id.equals(((EvaluationCreatedEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvaluationCreatedEvent{" +
            "id=" + getId() +
            "}";
    }
}
