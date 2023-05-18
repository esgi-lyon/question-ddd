package org.contextmapper.generated.evaluationcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.DifficultyLevel;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.Status;

/**
 * A DTO for the {@link org.contextmapper.generated.evaluationcontext.domain.Evaluation} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvaluationDTO implements Serializable {

    private Long id;

    private Integer score;

    private Status status;

    private DifficultyLevel answeredQuestionDifficultyLevel;

    private EvaluationTagDTO tag;

    private EvaluationQuestionDTO question;

    private AnsweringUserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public DifficultyLevel getAnsweredQuestionDifficultyLevel() {
        return answeredQuestionDifficultyLevel;
    }

    public void setAnsweredQuestionDifficultyLevel(DifficultyLevel answeredQuestionDifficultyLevel) {
        this.answeredQuestionDifficultyLevel = answeredQuestionDifficultyLevel;
    }

    public EvaluationTagDTO getTag() {
        return tag;
    }

    public void setTag(EvaluationTagDTO tag) {
        this.tag = tag;
    }

    public EvaluationQuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(EvaluationQuestionDTO question) {
        this.question = question;
    }

    public AnsweringUserDTO getUser() {
        return user;
    }

    public void setUser(AnsweringUserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvaluationDTO)) {
            return false;
        }

        EvaluationDTO evaluationDTO = (EvaluationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, evaluationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvaluationDTO{" +
            "id=" + getId() +
            ", score=" + getScore() +
            ", status='" + getStatus() + "'" +
            ", answeredQuestionDifficultyLevel='" + getAnsweredQuestionDifficultyLevel() + "'" +
            ", tag=" + getTag() +
            ", question=" + getQuestion() +
            ", user=" + getUser() +
            "}";
    }
}
