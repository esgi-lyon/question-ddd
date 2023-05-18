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

    private Integer tag;

    private States questionState;

    private Types resourceType;

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

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
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
            ", tag=" + getTag() +
            ", questionState='" + getQuestionState() + "'" +
            ", resourceType='" + getResourceType() + "'" +
            "}";
    }
}
