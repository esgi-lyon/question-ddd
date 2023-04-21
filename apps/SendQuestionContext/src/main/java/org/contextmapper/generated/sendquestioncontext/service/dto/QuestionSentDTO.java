package org.contextmapper.generated.sendquestioncontext.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.sendquestioncontext.domain.QuestionSent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionSentDTO implements Serializable {

    private Long id;

    private LocalDate sentDate;

    private LocalDate viewedDate;

    private LocalDate answeredDate;

    private QuestionDTO question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDate sentDate) {
        this.sentDate = sentDate;
    }

    public LocalDate getViewedDate() {
        return viewedDate;
    }

    public void setViewedDate(LocalDate viewedDate) {
        this.viewedDate = viewedDate;
    }

    public LocalDate getAnsweredDate() {
        return answeredDate;
    }

    public void setAnsweredDate(LocalDate answeredDate) {
        this.answeredDate = answeredDate;
    }

    public QuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDTO question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionSentDTO)) {
            return false;
        }

        QuestionSentDTO questionSentDTO = (QuestionSentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionSentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionSentDTO{" +
            "id=" + getId() +
            ", sentDate='" + getSentDate() + "'" +
            ", viewedDate='" + getViewedDate() + "'" +
            ", answeredDate='" + getAnsweredDate() + "'" +
            ", question=" + getQuestion() +
            "}";
    }
}
