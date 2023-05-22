package org.contextmapper.generated.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.gateway.domain.QuestionStatsViewedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionStatsViewedEventDTO implements Serializable {

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
        if (!(o instanceof QuestionStatsViewedEventDTO)) {
            return false;
        }

        QuestionStatsViewedEventDTO questionStatsViewedEventDTO = (QuestionStatsViewedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionStatsViewedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionStatsViewedEventDTO{" +
            "id=" + getId() +
            ", question=" + getQuestion() +
            "}";
    }
}
