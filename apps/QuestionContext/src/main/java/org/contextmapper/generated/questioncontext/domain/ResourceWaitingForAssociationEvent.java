package org.contextmapper.generated.questioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ResourceWaitingForAssociationEvent.
 */
@Entity
@Table(name = "resource_waiting_for_association_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResourceWaitingForAssociationEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties(value = { "tagInfos" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private QuestionResource questionId;

    @OneToOne
    @JoinColumn(unique = true)
    private QuestionResourceTagInfos tagInfos;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ResourceWaitingForAssociationEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionResource getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(QuestionResource questionResource) {
        this.questionId = questionResource;
    }

    public ResourceWaitingForAssociationEvent questionId(QuestionResource questionResource) {
        this.setQuestionId(questionResource);
        return this;
    }

    public QuestionResourceTagInfos getTagInfos() {
        return this.tagInfos;
    }

    public void setTagInfos(QuestionResourceTagInfos questionResourceTagInfos) {
        this.tagInfos = questionResourceTagInfos;
    }

    public ResourceWaitingForAssociationEvent tagInfos(QuestionResourceTagInfos questionResourceTagInfos) {
        this.setTagInfos(questionResourceTagInfos);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceWaitingForAssociationEvent)) {
            return false;
        }
        return id != null && id.equals(((ResourceWaitingForAssociationEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResourceWaitingForAssociationEvent{" +
            "id=" + getId() +
            "}";
    }
}
