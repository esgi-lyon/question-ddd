package org.contextmapper.generated.sendquestioncontext.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PrepareQuestionsCommand.
 */
@Entity
@Table(name = "prepare_questions_command")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PrepareQuestionsCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "tag_to_prepare_questions")
    private Integer tagToPrepareQuestions;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PrepareQuestionsCommand id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTagToPrepareQuestions() {
        return this.tagToPrepareQuestions;
    }

    public PrepareQuestionsCommand tagToPrepareQuestions(Integer tagToPrepareQuestions) {
        this.setTagToPrepareQuestions(tagToPrepareQuestions);
        return this;
    }

    public void setTagToPrepareQuestions(Integer tagToPrepareQuestions) {
        this.tagToPrepareQuestions = tagToPrepareQuestions;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrepareQuestionsCommand)) {
            return false;
        }
        return id != null && id.equals(((PrepareQuestionsCommand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrepareQuestionsCommand{" +
            "id=" + getId() +
            ", tagToPrepareQuestions=" + getTagToPrepareQuestions() +
            "}";
    }
}
