package org.contextmapper.generated.sendquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A QuestionSentTagId.
 */
@Entity
@Table(name = "question_sent_tag_id")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionSentTagId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "tag_id")
    private Integer tagId;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tags" }, allowSetters = true)
    private QuestionSent questionSent;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public QuestionSentTagId id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTagId() {
        return this.tagId;
    }

    public QuestionSentTagId tagId(Integer tagId) {
        this.setTagId(tagId);
        return this;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public QuestionSent getQuestionSent() {
        return this.questionSent;
    }

    public void setQuestionSent(QuestionSent questionSent) {
        this.questionSent = questionSent;
    }

    public QuestionSentTagId questionSent(QuestionSent questionSent) {
        this.setQuestionSent(questionSent);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionSentTagId)) {
            return false;
        }
        return id != null && id.equals(((QuestionSentTagId) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionSentTagId{" +
            "id=" + getId() +
            ", tagId=" + getTagId() +
            "}";
    }
}
