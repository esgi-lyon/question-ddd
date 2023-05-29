package org.contextmapper.generated.answercontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TagChoicesListedEvent.
 */
@Entity
@Table(name = "tag_choices_listed_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TagChoicesListedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties(value = { "question", "answeredTag", "userEmail" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Answer answerCreated;

    @OneToMany(mappedBy = "tagChoicesListedEvent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "tagChoicesListedEvent" }, allowSetters = true)
    private Set<AvailableAnswer> tags = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TagChoicesListedEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Answer getAnswerCreated() {
        return this.answerCreated;
    }

    public void setAnswerCreated(Answer answer) {
        this.answerCreated = answer;
    }

    public TagChoicesListedEvent answerCreated(Answer answer) {
        this.setAnswerCreated(answer);
        return this;
    }

    public Set<AvailableAnswer> getTags() {
        return this.tags;
    }

    public void setTags(Set<AvailableAnswer> availableAnswers) {
        if (this.tags != null) {
            this.tags.forEach(i -> i.setTagChoicesListedEvent(null));
        }
        if (availableAnswers != null) {
            availableAnswers.forEach(i -> i.setTagChoicesListedEvent(this));
        }
        this.tags = availableAnswers;
    }

    public TagChoicesListedEvent tags(Set<AvailableAnswer> availableAnswers) {
        this.setTags(availableAnswers);
        return this;
    }

    public TagChoicesListedEvent addTags(AvailableAnswer availableAnswer) {
        this.tags.add(availableAnswer);
        availableAnswer.setTagChoicesListedEvent(this);
        return this;
    }

    public TagChoicesListedEvent removeTags(AvailableAnswer availableAnswer) {
        this.tags.remove(availableAnswer);
        availableAnswer.setTagChoicesListedEvent(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagChoicesListedEvent)) {
            return false;
        }
        return id != null && id.equals(((TagChoicesListedEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TagChoicesListedEvent{" +
            "id=" + getId() +
            "}";
    }
}
