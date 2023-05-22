package org.contextmapper.generated.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A AwardPointForEvaluationCommand.
 */
@Table("award_point_for_evaluation_command")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AwardPointForEvaluationCommand implements Serializable {

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

    public AwardPointForEvaluationCommand id(Long id) {
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

    public AwardPointForEvaluationCommand evaluation(Evaluation evaluation) {
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
        if (!(o instanceof AwardPointForEvaluationCommand)) {
            return false;
        }
        return id != null && id.equals(((AwardPointForEvaluationCommand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AwardPointForEvaluationCommand{" +
            "id=" + getId() +
            "}";
    }
}
