package org.contextmapper.generated.gateway.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A CreateQuestionCommand.
 */
@Table("create_question_command")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreateQuestionCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private QuestionSentQuestionResourceTagId resource;

    @Column("resource_id")
    private Long resourceId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CreateQuestionCommand id(Long id) {
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
        this.resourceId = questionSentQuestionResourceTagId != null ? questionSentQuestionResourceTagId.getId() : null;
    }

    public CreateQuestionCommand resource(QuestionSentQuestionResourceTagId questionSentQuestionResourceTagId) {
        this.setResource(questionSentQuestionResourceTagId);
        return this;
    }

    public Long getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(Long questionSentQuestionResourceTagId) {
        this.resourceId = questionSentQuestionResourceTagId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateQuestionCommand)) {
            return false;
        }
        return id != null && id.equals(((CreateQuestionCommand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreateQuestionCommand{" +
            "id=" + getId() +
            "}";
    }
}
