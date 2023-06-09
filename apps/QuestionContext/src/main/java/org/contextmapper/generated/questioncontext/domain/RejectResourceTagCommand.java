package org.contextmapper.generated.questioncontext.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RejectResourceTagCommand.
 */
@Entity
@Table(name = "reject_resource_tag_command")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RejectResourceTagCommand implements Serializable {

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RejectResourceTagCommand id(Long id) {
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

    public RejectResourceTagCommand questionId(QuestionResource questionResource) {
        this.setQuestionId(questionResource);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RejectResourceTagCommand)) {
            return false;
        }
        return id != null && id.equals(((RejectResourceTagCommand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RejectResourceTagCommand{" +
            "id=" + getId() +
            "}";
    }
}
