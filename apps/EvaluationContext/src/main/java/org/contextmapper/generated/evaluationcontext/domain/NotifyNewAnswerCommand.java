package org.contextmapper.generated.evaluationcontext.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NotifyNewAnswerCommand.
 */
@Entity
@Table(name = "notify_new_answer_command")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NotifyNewAnswerCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private EvaluatedAnswer answer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public NotifyNewAnswerCommand id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EvaluatedAnswer getAnswer() {
        return this.answer;
    }

    public void setAnswer(EvaluatedAnswer evaluatedAnswer) {
        this.answer = evaluatedAnswer;
    }

    public NotifyNewAnswerCommand answer(EvaluatedAnswer evaluatedAnswer) {
        this.setAnswer(evaluatedAnswer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotifyNewAnswerCommand)) {
            return false;
        }
        return id != null && id.equals(((NotifyNewAnswerCommand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotifyNewAnswerCommand{" +
            "id=" + getId() +
            "}";
    }
}
