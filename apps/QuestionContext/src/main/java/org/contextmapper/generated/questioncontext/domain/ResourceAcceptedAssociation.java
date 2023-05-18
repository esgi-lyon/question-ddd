package org.contextmapper.generated.questioncontext.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ResourceAcceptedAssociation.
 */
@Entity
@Table(name = "resource_accepted_association")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResourceAcceptedAssociation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

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

    public ResourceAcceptedAssociation id(Long id) {
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

    public ResourceAcceptedAssociation questionId(QuestionResource questionResource) {
        this.setQuestionId(questionResource);
        return this;
    }

    public QuestionResourceTagInfos getTagId() {
        return this.tagId;
    }

    public void setTagId(QuestionResourceTagInfos questionResourceTagInfos) {
        this.tagId = questionResourceTagInfos;
    }

    public ResourceAcceptedAssociation tagId(QuestionResourceTagInfos questionResourceTagInfos) {
        this.setTagId(questionResourceTagInfos);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceAcceptedAssociation)) {
            return false;
        }
        return id != null && id.equals(((ResourceAcceptedAssociation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResourceAcceptedAssociation{" +
            "id=" + getId() +
            "}";
    }
}
