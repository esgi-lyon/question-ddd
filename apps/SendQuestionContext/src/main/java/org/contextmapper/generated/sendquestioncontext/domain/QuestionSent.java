package org.contextmapper.generated.sendquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.contextmapper.generated.sendquestioncontext.domain.enumeration.QuestionNotificationStatus;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A QuestionSent.
 */
@Entity
@Table(name = "question_sent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionSent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "sent_date")
    private LocalDate sentDate;

    @Column(name = "viewed_date")
    private LocalDate viewedDate;

    @Column(name = "answered_date")
    private LocalDate answeredDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private QuestionNotificationStatus status;

    @OneToOne
    @JoinColumn(unique = true)
    private ResourceId resourceId;

    @OneToMany(mappedBy = "questionSent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "questionSent" }, allowSetters = true)
    private Set<QuestionSentTagId> tags = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public QuestionSent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSentDate() {
        return this.sentDate;
    }

    public QuestionSent sentDate(LocalDate sentDate) {
        this.setSentDate(sentDate);
        return this;
    }

    public void setSentDate(LocalDate sentDate) {
        this.sentDate = sentDate;
    }

    public LocalDate getViewedDate() {
        return this.viewedDate;
    }

    public QuestionSent viewedDate(LocalDate viewedDate) {
        this.setViewedDate(viewedDate);
        return this;
    }

    public void setViewedDate(LocalDate viewedDate) {
        this.viewedDate = viewedDate;
    }

    public LocalDate getAnsweredDate() {
        return this.answeredDate;
    }

    public QuestionSent answeredDate(LocalDate answeredDate) {
        this.setAnsweredDate(answeredDate);
        return this;
    }

    public void setAnsweredDate(LocalDate answeredDate) {
        this.answeredDate = answeredDate;
    }

    public QuestionNotificationStatus getStatus() {
        return this.status;
    }

    public QuestionSent status(QuestionNotificationStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(QuestionNotificationStatus status) {
        this.status = status;
    }

    public ResourceId getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(ResourceId resourceId) {
        this.resourceId = resourceId;
    }

    public QuestionSent resourceId(ResourceId resourceId) {
        this.setResourceId(resourceId);
        return this;
    }

    public Set<QuestionSentTagId> getTags() {
        return this.tags;
    }

    public void setTags(Set<QuestionSentTagId> questionSentTagIds) {
        if (this.tags != null) {
            this.tags.forEach(i -> i.setQuestionSent(null));
        }
        if (questionSentTagIds != null) {
            questionSentTagIds.forEach(i -> i.setQuestionSent(this));
        }
        this.tags = questionSentTagIds;
    }

    public QuestionSent tags(Set<QuestionSentTagId> questionSentTagIds) {
        this.setTags(questionSentTagIds);
        return this;
    }

    public QuestionSent addTags(QuestionSentTagId questionSentTagId) {
        this.tags.add(questionSentTagId);
        questionSentTagId.setQuestionSent(this);
        return this;
    }

    public QuestionSent removeTags(QuestionSentTagId questionSentTagId) {
        this.tags.remove(questionSentTagId);
        questionSentTagId.setQuestionSent(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionSent)) {
            return false;
        }
        return id != null && id.equals(((QuestionSent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionSent{" +
            "id=" + getId() +
            ", sentDate='" + getSentDate() + "'" +
            ", viewedDate='" + getViewedDate() + "'" +
            ", answeredDate='" + getAnsweredDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
