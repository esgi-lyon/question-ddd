package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.DifficultyLevel;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.CreateEvaluationCommand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreateEvaluationCommandDTO implements Serializable {

    private Long id;

    private DifficultyLevel difficultyLevel;

    private EvaluatedAnswerDTO answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public EvaluatedAnswerDTO getAnswer() {
        return answer;
    }

    public void setAnswer(EvaluatedAnswerDTO answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateEvaluationCommandDTO)) {
            return false;
        }

        CreateEvaluationCommandDTO createEvaluationCommandDTO = (CreateEvaluationCommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, createEvaluationCommandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreateEvaluationCommandDTO{" +
            "id=" + getId() +
            ", difficultyLevel='" + getDifficultyLevel() + "'" +
            ", answer=" + getAnswer() +
            "}";
    }
}
