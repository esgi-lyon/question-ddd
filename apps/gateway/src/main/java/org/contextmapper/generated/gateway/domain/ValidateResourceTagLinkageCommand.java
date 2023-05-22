package org.contextmapper.generated.gateway.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ValidateResourceTagLinkageCommand.
 */
@Table("validate_resource_tag_linkage_command")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ValidateResourceTagLinkageCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private QuestionResource questionId;

    @Column("question_id_id")
    private Long questionIdId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ValidateResourceTagLinkageCommand id(Long id) {
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

    public ValidateResourceTagLinkageCommand questionId(QuestionResource questionResource) {
        this.setQuestionId(questionResource);
        return this;
    }

    public Long getQuestionIdId() {
        return this.questionIdId;
    }

    public void setQuestionIdId(Long questionResource) {
        this.questionIdId = questionResource;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ValidateResourceTagLinkageCommand)) {
            return false;
        }
        return id != null && id.equals(((ValidateResourceTagLinkageCommand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ValidateResourceTagLinkageCommand{" +
            "id=" + getId() +
            "}";
    }
}
