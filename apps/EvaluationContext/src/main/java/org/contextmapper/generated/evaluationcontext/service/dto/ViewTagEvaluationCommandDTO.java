package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.ViewTagEvaluationCommand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ViewTagEvaluationCommandDTO implements Serializable {

    private Long id;

    private EvaluationTagDTO tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EvaluationTagDTO getTag() {
        return tag;
    }

    public void setTag(EvaluationTagDTO tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewTagEvaluationCommandDTO)) {
            return false;
        }

        ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO = (ViewTagEvaluationCommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, viewTagEvaluationCommandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewTagEvaluationCommandDTO{" +
            "id=" + getId() +
            ", tag=" + getTag() +
            "}";
    }
}
