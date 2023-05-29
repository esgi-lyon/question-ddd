package org.contextmapper.generated.sendquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A QuestionSentTagInfosViewedEvent.
 */
@Entity
@Table(name = "question_sent_tag_infos_viewed_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionSentTagInfosViewedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "questionSentTagInfosViewedEvent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "questionSent", "questionSentTagInfosViewedEvent" }, allowSetters = true)
    private Set<QuestionSentTagInfos> tagInfos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public QuestionSentTagInfosViewedEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<QuestionSentTagInfos> getTagInfos() {
        return this.tagInfos;
    }

    public void setTagInfos(Set<QuestionSentTagInfos> questionSentTagInfos) {
        if (this.tagInfos != null) {
            this.tagInfos.forEach(i -> i.setQuestionSentTagInfosViewedEvent(null));
        }
        if (questionSentTagInfos != null) {
            questionSentTagInfos.forEach(i -> i.setQuestionSentTagInfosViewedEvent(this));
        }
        this.tagInfos = questionSentTagInfos;
    }

    public QuestionSentTagInfosViewedEvent tagInfos(Set<QuestionSentTagInfos> questionSentTagInfos) {
        this.setTagInfos(questionSentTagInfos);
        return this;
    }

    public QuestionSentTagInfosViewedEvent addTagInfos(QuestionSentTagInfos questionSentTagInfos) {
        this.tagInfos.add(questionSentTagInfos);
        questionSentTagInfos.setQuestionSentTagInfosViewedEvent(this);
        return this;
    }

    public QuestionSentTagInfosViewedEvent removeTagInfos(QuestionSentTagInfos questionSentTagInfos) {
        this.tagInfos.remove(questionSentTagInfos);
        questionSentTagInfos.setQuestionSentTagInfosViewedEvent(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionSentTagInfosViewedEvent)) {
            return false;
        }
        return id != null && id.equals(((QuestionSentTagInfosViewedEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionSentTagInfosViewedEvent{" +
            "id=" + getId() +
            "}";
    }
}
