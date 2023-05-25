package org.contextmapper.generated.questioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.contextmapper.generated.questioncontext.domain.enumeration.States;
import org.contextmapper.generated.questioncontext.domain.enumeration.Types;

/**
 * A DTO for the {@link org.contextmapper.generated.questioncontext.domain.QuestionResource} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionResourceDTO implements Serializable {

    private Long id;

    private String questionContent;

    private States questionState;

    private Types resourceType;

    private QuestionResourceTagInfosDTO tagInfos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public States getQuestionState() {
        return questionState;
    }

    public void setQuestionState(States questionState) {
        this.questionState = questionState;
    }

    public Types getResourceType() {
        return resourceType;
    }

    public void setResourceType(Types resourceType) {
        this.resourceType = resourceType;
    }

    public QuestionResourceTagInfosDTO getTagInfos() {
        return tagInfos;
    }

    public void setTagInfos(QuestionResourceTagInfosDTO tagInfos) {
        this.tagInfos = tagInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionResourceDTO)) {
            return false;
        }

        QuestionResourceDTO questionResourceDTO = (QuestionResourceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionResourceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionResourceDTO{" +
            "id=" + getId() +
            ", questionContent='" + getQuestionContent() + "'" +
            ", questionState='" + getQuestionState() + "'" +
            ", resourceType='" + getResourceType() + "'" +
            ", tagInfos=" + getTagInfos() +
            "}";
    }
}
