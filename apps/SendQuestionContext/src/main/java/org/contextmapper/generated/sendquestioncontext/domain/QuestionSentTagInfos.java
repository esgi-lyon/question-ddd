package org.contextmapper.generated.sendquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A QuestionSentTagInfos.
 */
@Entity
@Table(name = "question_sent_tag_infos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionSentTagInfos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToOne
    @JsonIgnoreProperties(value = { "resourceId", "tags" }, allowSetters = true)
    private QuestionSent questionSent;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tagInfos" }, allowSetters = true)
    private QuestionSentTagInfosViewedEvent questionSentTagInfosViewedEvent;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public QuestionSentTagInfos id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTagId() {
        return this.tagId;
    }

    public QuestionSentTagInfos tagId(Long tagId) {
        this.setTagId(tagId);
        return this;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return this.tagName;
    }

    public QuestionSentTagInfos tagName(String tagName) {
        this.setTagName(tagName);
        return this;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public QuestionSent getQuestionSent() {
        return this.questionSent;
    }

    public void setQuestionSent(QuestionSent questionSent) {
        this.questionSent = questionSent;
    }

    public QuestionSentTagInfos questionSent(QuestionSent questionSent) {
        this.setQuestionSent(questionSent);
        return this;
    }

    public QuestionSentTagInfosViewedEvent getQuestionSentTagInfosViewedEvent() {
        return this.questionSentTagInfosViewedEvent;
    }

    public void setQuestionSentTagInfosViewedEvent(QuestionSentTagInfosViewedEvent questionSentTagInfosViewedEvent) {
        this.questionSentTagInfosViewedEvent = questionSentTagInfosViewedEvent;
    }

    public QuestionSentTagInfos questionSentTagInfosViewedEvent(QuestionSentTagInfosViewedEvent questionSentTagInfosViewedEvent) {
        this.setQuestionSentTagInfosViewedEvent(questionSentTagInfosViewedEvent);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionSentTagInfos)) {
            return false;
        }
        return id != null && id.equals(((QuestionSentTagInfos) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionSentTagInfos{" +
            "id=" + getId() +
            ", tagId=" + getTagId() +
            ", tagName='" + getTagName() + "'" +
            "}";
    }
}
