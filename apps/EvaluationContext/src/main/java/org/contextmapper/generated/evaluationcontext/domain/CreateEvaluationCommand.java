package org.contextmapper.generated.evaluationcontext.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.DifficultyLevel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CreateEvaluationCommand.
 */
@Entity
@Table(name = "create_evaluation_command")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreateEvaluationCommand implements Serializable {

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
    private EvaluatedAnswer answer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CreateEvaluationCommand id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DifficultyLevel getDifficultyLevel() {
        return this.difficultyLevel;
    }

    public CreateEvaluationCommand difficultyLevel(DifficultyLevel difficultyLevel) {
        this.setDifficultyLevel(difficultyLevel);
        return this;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public EvaluatedAnswer getAnswer() {
        return this.answer;
    }

    public void setAnswer(EvaluatedAnswer evaluatedAnswer) {
        this.answer = evaluatedAnswer;
    }

    public CreateEvaluationCommand answer(EvaluatedAnswer evaluatedAnswer) {
        this.setAnswer(evaluatedAnswer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateEvaluationCommand)) {
            return false;
        }
        return id != null && id.equals(((CreateEvaluationCommand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreateEvaluationCommand{" +
            "id=" + getId() +
            ", difficultyLevel='" + getDifficultyLevel() + "'" +
            "}";
    }
}
