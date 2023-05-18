package org.contextmapper.generated.sendquestioncontext.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import org.contextmapper.generated.sendquestioncontext.domain.enumeration.QuestionNotificationStatus;

/**
 * A DTO for the {@link org.contextmapper.generated.sendquestioncontext.domain.QuestionSent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionSentDTO implements Serializable {

    private Long id;

    private Integer resource;

    private LocalDate sentDate;

    private LocalDate viewedDate;

    private LocalDate answeredDate;

    private QuestionNotificationStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getResource() {
        return resource;
    }

    public void setResource(Integer resource) {
        this.resource = resource;
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

    public QuestionNotificationStatus getStatus() {
        return status;
    }

    public void setStatus(QuestionNotificationStatus status) {
        this.status = status;
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
            ", resource=" + getResource() +
            ", sentDate='" + getSentDate() + "'" +
            ", viewedDate='" + getViewedDate() + "'" +
            ", answeredDate='" + getAnsweredDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
