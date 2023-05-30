package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.ViewQuestionEvaluationCommand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ViewQuestionEvaluationCommandDTO implements Serializable {

    private Long id;

    private EvaluationQuestionDTO question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EvaluationQuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(EvaluationQuestionDTO question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewQuestionEvaluationCommandDTO)) {
            return false;
        }

        ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO = (ViewQuestionEvaluationCommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, viewQuestionEvaluationCommandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewQuestionEvaluationCommandDTO{" +
            "id=" + getId() +
            ", question=" + getQuestion() +
            "}";
    }
}
