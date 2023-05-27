package org.contextmapper.generated.sendquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionCommand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PrepareQuestionCommandDTO implements Serializable {

    private Long id;

    private Long resourceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PrepareQuestionCommandDTO)) {
            return false;
        }

        PrepareQuestionCommandDTO prepareQuestionCommandDTO = (PrepareQuestionCommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, prepareQuestionCommandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrepareQuestionCommandDTO{" +
            "id=" + getId() +
            ", resourceId=" + getResourceId() +
            "}";
    }
}
