package org.contextmapper.generated.sendquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SendQuestionByTagsPreferences.
 */
@Entity
@Table(name = "send_question_by_tags_preferences")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SendQuestionByTagsPreferences implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties(value = { "tags" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private QuestionSent questionSent;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SendQuestionByTagsPreferences id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionSent getQuestionSent() {
        return this.questionSent;
    }

    public void setQuestionSent(QuestionSent questionSent) {
        this.questionSent = questionSent;
    }

    public SendQuestionByTagsPreferences questionSent(QuestionSent questionSent) {
        this.setQuestionSent(questionSent);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SendQuestionByTagsPreferences)) {
            return false;
        }
        return id != null && id.equals(((SendQuestionByTagsPreferences) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SendQuestionByTagsPreferences{" +
            "id=" + getId() +
            "}";
    }
}
