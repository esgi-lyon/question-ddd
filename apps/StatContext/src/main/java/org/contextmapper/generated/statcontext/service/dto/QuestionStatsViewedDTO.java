package org.contextmapper.generated.statcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.statcontext.domain.QuestionStatsViewed} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionStatsViewedDTO implements Serializable {

    private Long id;

    private StatisticSubjectQuestionDTO question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatisticSubjectQuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(StatisticSubjectQuestionDTO question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionStatsViewedDTO)) {
            return false;
        }

        QuestionStatsViewedDTO questionStatsViewedDTO = (QuestionStatsViewedDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionStatsViewedDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionStatsViewedDTO{" +
            "id=" + getId() +
            ", question=" + getQuestion() +
            "}";
    }
}
