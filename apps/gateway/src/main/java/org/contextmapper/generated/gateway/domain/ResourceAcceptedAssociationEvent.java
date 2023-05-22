package org.contextmapper.generated.gateway.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ResourceAcceptedAssociationEvent.
 */
@Table("resource_accepted_association_event")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResourceAcceptedAssociationEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private QuestionResource questionId;

    @Transient
    private QuestionResourceTagInfos tagId;

    @Column("question_id_id")
    private Long questionIdId;

    @Column("tag_id_id")
    private Long tagIdId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ResourceAcceptedAssociationEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionResource getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(QuestionResource questionResource) {
        this.questionId = questionResource;
        this.questionIdId = questionResource != null ? questionResource.getId() : null;
    }

    public ResourceAcceptedAssociationEvent questionId(QuestionResource questionResource) {
        this.setQuestionId(questionResource);
        return this;
    }

    public QuestionResourceTagInfos getTagId() {
        return this.tagId;
    }

    public void setTagId(QuestionResourceTagInfos questionResourceTagInfos) {
        this.tagId = questionResourceTagInfos;
        this.tagIdId = questionResourceTagInfos != null ? questionResourceTagInfos.getId() : null;
    }

    public ResourceAcceptedAssociationEvent tagId(QuestionResourceTagInfos questionResourceTagInfos) {
        this.setTagId(questionResourceTagInfos);
        return this;
    }

    public Long getQuestionIdId() {
        return this.questionIdId;
    }

    public void setQuestionIdId(Long questionResource) {
        this.questionIdId = questionResource;
    }

    public Long getTagIdId() {
        return this.tagIdId;
    }

    public void setTagIdId(Long questionResourceTagInfos) {
        this.tagIdId = questionResourceTagInfos;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceAcceptedAssociationEvent)) {
            return false;
        }
        return id != null && id.equals(((ResourceAcceptedAssociationEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResourceAcceptedAssociationEvent{" +
            "id=" + getId() +
            "}";
    }
}
