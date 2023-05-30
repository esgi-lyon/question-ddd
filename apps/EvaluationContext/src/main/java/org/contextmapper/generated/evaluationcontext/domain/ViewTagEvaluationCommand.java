package org.contextmapper.generated.evaluationcontext.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ViewTagEvaluationCommand.
 */
@Entity
@Table(name = "view_tag_evaluation_command")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ViewTagEvaluationCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private EvaluationTag tag;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ViewTagEvaluationCommand id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EvaluationTag getTag() {
        return this.tag;
    }

    public void setTag(EvaluationTag evaluationTag) {
        this.tag = evaluationTag;
    }

    public ViewTagEvaluationCommand tag(EvaluationTag evaluationTag) {
        this.setTag(evaluationTag);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewTagEvaluationCommand)) {
            return false;
        }
        return id != null && id.equals(((ViewTagEvaluationCommand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewTagEvaluationCommand{" +
            "id=" + getId() +
            "}";
    }
}
