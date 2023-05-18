package org.contextmapper.generated.sendquestioncontext.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CreateQuestion.
 */
@Entity
@Table(name = "create_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreateQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private QuestionSentQuestionResourceTagId resource;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CreateQuestion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionSentQuestionResourceTagId getResource() {
        return this.resource;
    }

    public void setResource(QuestionSentQuestionResourceTagId questionSentQuestionResourceTagId) {
        this.resource = questionSentQuestionResourceTagId;
    }

    public CreateQuestion resource(QuestionSentQuestionResourceTagId questionSentQuestionResourceTagId) {
        this.setResource(questionSentQuestionResourceTagId);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateQuestion)) {
            return false;
        }
        return id != null && id.equals(((CreateQuestion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreateQuestion{" +
            "id=" + getId() +
            "}";
    }
}
