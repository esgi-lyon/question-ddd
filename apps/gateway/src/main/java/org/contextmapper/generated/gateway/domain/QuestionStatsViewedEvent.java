package org.contextmapper.generated.gateway.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A QuestionStatsViewedEvent.
 */
@Table("question_stats_viewed_event")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionStatsViewedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private StatisticSubjectQuestion question;

    @Column("question_id")
    private Long questionId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public QuestionStatsViewedEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatisticSubjectQuestion getQuestion() {
        return this.question;
    }

    public void setQuestion(StatisticSubjectQuestion statisticSubjectQuestion) {
        this.question = statisticSubjectQuestion;
        this.questionId = statisticSubjectQuestion != null ? statisticSubjectQuestion.getId() : null;
    }

    public QuestionStatsViewedEvent question(StatisticSubjectQuestion statisticSubjectQuestion) {
        this.setQuestion(statisticSubjectQuestion);
        return this;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long statisticSubjectQuestion) {
        this.questionId = statisticSubjectQuestion;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionStatsViewedEvent)) {
            return false;
        }
        return id != null && id.equals(((QuestionStatsViewedEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionStatsViewedEvent{" +
            "id=" + getId() +
            "}";
    }
}
