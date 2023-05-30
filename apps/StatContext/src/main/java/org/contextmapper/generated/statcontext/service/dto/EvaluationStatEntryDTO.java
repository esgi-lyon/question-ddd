package org.contextmapper.generated.statcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.statcontext.domain.EvaluationStatEntry} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvaluationStatEntryDTO implements Serializable {

    private Long id;

    private Long evaluationId;

    private Integer score;

    private StatisticSubjectUserDTO user;

    private StatisticSubjectQuestionDTO question;

    private EvaluationStatsDTO evaluationStats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public StatisticSubjectUserDTO getUser() {
        return user;
    }

    public void setUser(StatisticSubjectUserDTO user) {
        this.user = user;
    }

    public StatisticSubjectQuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(StatisticSubjectQuestionDTO question) {
        this.question = question;
    }

    public EvaluationStatsDTO getEvaluationStats() {
        return evaluationStats;
    }

    public void setEvaluationStats(EvaluationStatsDTO evaluationStats) {
        this.evaluationStats = evaluationStats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvaluationStatEntryDTO)) {
            return false;
        }

        EvaluationStatEntryDTO evaluationStatEntryDTO = (EvaluationStatEntryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, evaluationStatEntryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvaluationStatEntryDTO{" +
            "id=" + getId() +
            ", evaluationId=" + getEvaluationId() +
            ", score=" + getScore() +
            ", user=" + getUser() +
            ", question=" + getQuestion() +
            ", evaluationStats=" + getEvaluationStats() +
            "}";
    }
}
