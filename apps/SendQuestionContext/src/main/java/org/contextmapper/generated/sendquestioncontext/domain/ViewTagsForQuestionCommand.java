package org.contextmapper.generated.sendquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ViewTagsForQuestionCommand.
 */
@Entity
@Table(name = "view_tags_for_question_command")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ViewTagsForQuestionCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties(value = { "resourceId", "tags" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private QuestionSent questionToSend;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ViewTagsForQuestionCommand id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionSent getQuestionToSend() {
        return this.questionToSend;
    }

    public void setQuestionToSend(QuestionSent questionSent) {
        this.questionToSend = questionSent;
    }

    public ViewTagsForQuestionCommand questionToSend(QuestionSent questionSent) {
        this.setQuestionToSend(questionSent);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewTagsForQuestionCommand)) {
            return false;
        }
        return id != null && id.equals(((ViewTagsForQuestionCommand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewTagsForQuestionCommand{" +
            "id=" + getId() +
            "}";
    }
}
