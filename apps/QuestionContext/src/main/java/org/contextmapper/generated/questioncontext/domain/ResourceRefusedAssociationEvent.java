package org.contextmapper.generated.questioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ResourceRefusedAssociationEvent.
 */
@Entity
@Table(name = "resource_refused_association_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResourceRefusedAssociationEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @JsonIgnoreProperties(value = { "tagId" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private QuestionResource questionId;

    @OneToOne
    @JoinColumn(unique = true)
    private QuestionResourceTagInfos tagId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ResourceRefusedAssociationEvent id(Long id) {
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

    public ResourceRefusedAssociationEvent questionId(QuestionResource questionResource) {
        this.setQuestionId(questionResource);
        return this;
    }

    public QuestionResourceTagInfos getTagId() {
        return this.tagId;
    }

    public void setTagId(QuestionResourceTagInfos questionResourceTagInfos) {
        this.tagId = questionResourceTagInfos;
    }

    public ResourceRefusedAssociationEvent tagId(QuestionResourceTagInfos questionResourceTagInfos) {
        this.setTagId(questionResourceTagInfos);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceRefusedAssociationEvent)) {
            return false;
        }
        return id != null && id.equals(((ResourceRefusedAssociationEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResourceRefusedAssociationEvent{" +
            "id=" + getId() +
            "}";
    }
}
