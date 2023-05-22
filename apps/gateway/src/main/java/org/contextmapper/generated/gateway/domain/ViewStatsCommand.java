package org.contextmapper.generated.gateway.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ViewStatsCommand.
 */
@Table("view_stats_command")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ViewStatsCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private StatisticSubjectUser user;

    @Transient
    private StatisticSubjectQuestion question;

    @Transient
    private StatisticSubjectTag tag;

    @Column("user_id")
    private Long userId;

    @Column("question_id")
    private Long questionId;

    @Column("tag_id")
    private Long tagId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ViewStatsCommand id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatisticSubjectUser getUser() {
        return this.user;
    }

    public void setUser(StatisticSubjectUser statisticSubjectUser) {
        this.user = statisticSubjectUser;
        this.userId = statisticSubjectUser != null ? statisticSubjectUser.getId() : null;
    }

    public ViewStatsCommand user(StatisticSubjectUser statisticSubjectUser) {
        this.setUser(statisticSubjectUser);
        return this;
    }

    public StatisticSubjectQuestion getQuestion() {
        return this.question;
    }

    public void setQuestion(StatisticSubjectQuestion statisticSubjectQuestion) {
        this.question = statisticSubjectQuestion;
        this.questionId = statisticSubjectQuestion != null ? statisticSubjectQuestion.getId() : null;
    }

    public ViewStatsCommand question(StatisticSubjectQuestion statisticSubjectQuestion) {
        this.setQuestion(statisticSubjectQuestion);
        return this;
    }

    public StatisticSubjectTag getTag() {
        return this.tag;
    }

    public void setTag(StatisticSubjectTag statisticSubjectTag) {
        this.tag = statisticSubjectTag;
        this.tagId = statisticSubjectTag != null ? statisticSubjectTag.getId() : null;
    }

    public ViewStatsCommand tag(StatisticSubjectTag statisticSubjectTag) {
        this.setTag(statisticSubjectTag);
        return this;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long statisticSubjectUser) {
        this.userId = statisticSubjectUser;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long statisticSubjectQuestion) {
        this.questionId = statisticSubjectQuestion;
    }

    public Long getTagId() {
        return this.tagId;
    }

    public void setTagId(Long statisticSubjectTag) {
        this.tagId = statisticSubjectTag;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewStatsCommand)) {
            return false;
        }
        return id != null && id.equals(((ViewStatsCommand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewStatsCommand{" +
            "id=" + getId() +
            "}";
    }
}
