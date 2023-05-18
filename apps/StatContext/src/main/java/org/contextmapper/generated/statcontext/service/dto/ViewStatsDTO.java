package org.contextmapper.generated.statcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.statcontext.domain.ViewStats} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ViewStatsDTO implements Serializable {

    private Long id;

    private StatisticSubjectUserDTO user;

    private StatisticSubjectQuestionDTO question;

    private StatisticSubjectTagDTO tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public StatisticSubjectTagDTO getTag() {
        return tag;
    }

    public void setTag(StatisticSubjectTagDTO tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewStatsDTO)) {
            return false;
        }

        ViewStatsDTO viewStatsDTO = (ViewStatsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, viewStatsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ViewStatsDTO{" +
            "id=" + getId() +
            ", user=" + getUser() +
            ", question=" + getQuestion() +
            ", tag=" + getTag() +
            "}";
    }
}
