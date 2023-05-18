package org.contextmapper.generated.sendquestioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Question.
 */
@Entity
@Table(name = "question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties(value = { "question" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private QuestionSentTagInfos resourceCorrectTag;

    @OneToMany(mappedBy = "question")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "question" }, allowSetters = true)
    private Set<QuestionSentTagInfos> tags = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Question id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionSentTagInfos getResourceCorrectTag() {
        return this.resourceCorrectTag;
    }

    public void setResourceCorrectTag(QuestionSentTagInfos questionSentTagInfos) {
        this.resourceCorrectTag = questionSentTagInfos;
    }

    public Question resourceCorrectTag(QuestionSentTagInfos questionSentTagInfos) {
        this.setResourceCorrectTag(questionSentTagInfos);
        return this;
    }

    public Set<QuestionSentTagInfos> getTags() {
        return this.tags;
    }

    public void setTags(Set<QuestionSentTagInfos> questionSentTagInfos) {
        if (this.tags != null) {
            this.tags.forEach(i -> i.setQuestion(null));
        }
        if (questionSentTagInfos != null) {
            questionSentTagInfos.forEach(i -> i.setQuestion(this));
        }
        this.tags = questionSentTagInfos;
    }

    public Question tags(Set<QuestionSentTagInfos> questionSentTagInfos) {
        this.setTags(questionSentTagInfos);
        return this;
    }

    public Question addTags(QuestionSentTagInfos questionSentTagInfos) {
        this.tags.add(questionSentTagInfos);
        questionSentTagInfos.setQuestion(this);
        return this;
    }

    public Question removeTags(QuestionSentTagInfos questionSentTagInfos) {
        this.tags.remove(questionSentTagInfos);
        questionSentTagInfos.setQuestion(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        return id != null && id.equals(((Question) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Question{" +
            "id=" + getId() +
            "}";
    }
}
