package org.contextmapper.generated.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A QuestionSentTagInfos.
 */
@Table("question_sent_tag_infos")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionSentTagInfos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("tag_id")
    private Integer tagId;

    @Column("name")
    private String name;

    @Transient
    @JsonIgnoreProperties(value = { "resourceCorrectTag", "tags" }, allowSetters = true)
    private Question question;

    @Column("question_id")
    private Long questionId;

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

    public Integer getTagId() {
        return this.tagId;
    }

    public QuestionSentTagInfos tagId(Integer tagId) {
        this.setTagId(tagId);
        return this;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return this.name;
    }

    public QuestionSentTagInfos name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question) {
        this.question = question;
        this.questionId = question != null ? question.getId() : null;
    }

    public QuestionSentTagInfos question(Question question) {
        this.setQuestion(question);
        return this;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long question) {
        this.questionId = question;
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
            ", name='" + getName() + "'" +
            "}";
    }
}
