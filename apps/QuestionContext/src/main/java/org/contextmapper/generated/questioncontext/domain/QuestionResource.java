package org.contextmapper.generated.questioncontext.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.contextmapper.generated.questioncontext.domain.enumeration.States;
import org.contextmapper.generated.questioncontext.domain.enumeration.Types;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A QuestionResource.
 */
@Entity
@Table(name = "question_resource")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "question_content")
    private String questionContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_state")
    private States questionState;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type")
    private Types resourceType;

    @OneToOne
    @JoinColumn(unique = true)
    private QuestionResourceTagInfos tagInfos;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public QuestionResource id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionContent() {
        return this.questionContent;
    }

    public QuestionResource questionContent(String questionContent) {
        this.setQuestionContent(questionContent);
        return this;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public States getQuestionState() {
        return this.questionState;
    }

    public QuestionResource questionState(States questionState) {
        this.setQuestionState(questionState);
        return this;
    }

    public void setQuestionState(States questionState) {
        this.questionState = questionState;
    }

    public Types getResourceType() {
        return this.resourceType;
    }

    public QuestionResource resourceType(Types resourceType) {
        this.setResourceType(resourceType);
        return this;
    }

    public void setResourceType(Types resourceType) {
        this.resourceType = resourceType;
    }

    public QuestionResourceTagInfos getTagInfos() {
        return this.tagInfos;
    }

    public void setTagInfos(QuestionResourceTagInfos questionResourceTagInfos) {
        this.tagInfos = questionResourceTagInfos;
    }

    public QuestionResource tagInfos(QuestionResourceTagInfos questionResourceTagInfos) {
        this.setTagInfos(questionResourceTagInfos);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionResource)) {
            return false;
        }
        return id != null && id.equals(((QuestionResource) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionResource{" +
            "id=" + getId() +
            ", questionContent='" + getQuestionContent() + "'" +
            ", questionState='" + getQuestionState() + "'" +
            ", resourceType='" + getResourceType() + "'" +
            "}";
    }
}
