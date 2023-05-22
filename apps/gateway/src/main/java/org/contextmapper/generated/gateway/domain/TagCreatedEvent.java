package org.contextmapper.generated.gateway.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A TagCreatedEvent.
 */
@Table("tag_created_event")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TagCreatedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private TagInfos tagId;

    @Column("tag_id_id")
    private Long tagIdId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TagCreatedEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TagInfos getTagId() {
        return this.tagId;
    }

    public void setTagId(TagInfos tagInfos) {
        this.tagId = tagInfos;
        this.tagIdId = tagInfos != null ? tagInfos.getId() : null;
    }

    public TagCreatedEvent tagId(TagInfos tagInfos) {
        this.setTagId(tagInfos);
        return this;
    }

    public Long getTagIdId() {
        return this.tagIdId;
    }

    public void setTagIdId(Long tagInfos) {
        this.tagIdId = tagInfos;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagCreatedEvent)) {
            return false;
        }
        return id != null && id.equals(((TagCreatedEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TagCreatedEvent{" +
            "id=" + getId() +
            "}";
    }
}
