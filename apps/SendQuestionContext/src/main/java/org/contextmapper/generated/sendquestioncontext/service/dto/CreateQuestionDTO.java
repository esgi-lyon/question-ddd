package org.contextmapper.generated.sendquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.sendquestioncontext.domain.CreateQuestion} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreateQuestionDTO implements Serializable {

    private Long id;

    private QuestionSentQuestionResourceTagIdDTO resource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionSentQuestionResourceTagIdDTO getResource() {
        return resource;
    }

    public void setResource(QuestionSentQuestionResourceTagIdDTO resource) {
        this.resource = resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateQuestionDTO)) {
            return false;
        }

        CreateQuestionDTO createQuestionDTO = (CreateQuestionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, createQuestionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreateQuestionDTO{" +
            "id=" + getId() +
            ", resource=" + getResource() +
            "}";
    }
}
