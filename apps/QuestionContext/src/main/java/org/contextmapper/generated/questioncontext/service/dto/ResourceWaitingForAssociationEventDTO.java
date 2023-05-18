package org.contextmapper.generated.questioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.questioncontext.domain.ResourceWaitingForAssociationEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResourceWaitingForAssociationEventDTO implements Serializable {

    private Long id;

    private QuestionResourceDTO questionId;

    private QuestionResourceTagInfosDTO tagId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionResourceDTO getQuestionId() {
        return questionId;
    }

    public void setQuestionId(QuestionResourceDTO questionId) {
        this.questionId = questionId;
    }

    public QuestionResourceTagInfosDTO getTagId() {
        return tagId;
    }

    public void setTagId(QuestionResourceTagInfosDTO tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceWaitingForAssociationEventDTO)) {
            return false;
        }

        ResourceWaitingForAssociationEventDTO resourceWaitingForAssociationEventDTO = (ResourceWaitingForAssociationEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, resourceWaitingForAssociationEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResourceWaitingForAssociationEventDTO{" +
            "id=" + getId() +
            ", questionId=" + getQuestionId() +
            ", tagId=" + getTagId() +
            "}";
    }
}
