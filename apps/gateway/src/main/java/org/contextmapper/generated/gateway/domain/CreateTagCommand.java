package org.contextmapper.generated.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A CreateTagCommand.
 */
@Table("create_tag_command")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreateTagCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private Tag tag;

    @Column("tag_id")
    private Long tagId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CreateTagCommand id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tag getTag() {
        return this.tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
        this.tagId = tag != null ? tag.getId() : null;
    }

    public CreateTagCommand tag(Tag tag) {
        this.setTag(tag);
        return this;
    }

    public Long getTagId() {
        return this.tagId;
    }

    public void setTagId(Long tag) {
        this.tagId = tag;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateTagCommand)) {
            return false;
        }
        return id != null && id.equals(((CreateTagCommand) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreateTagCommand{" +
            "id=" + getId() +
            "}";
    }
}
