package org.contextmapper.generated.gateway.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A CategoryCreatedEvent.
 */
@Table("category_created_event")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CategoryCreatedEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Transient
    private CategoryId categoryId;

    @Column("category_id_id")
    private Long categoryIdId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CategoryCreatedEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryId getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(CategoryId categoryId) {
        this.categoryId = categoryId;
        this.categoryIdId = categoryId != null ? categoryId.getId() : null;
    }

    public CategoryCreatedEvent categoryId(CategoryId categoryId) {
        this.setCategoryId(categoryId);
        return this;
    }

    public Long getCategoryIdId() {
        return this.categoryIdId;
    }

    public void setCategoryIdId(Long categoryId) {
        this.categoryIdId = categoryId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryCreatedEvent)) {
            return false;
        }
        return id != null && id.equals(((CategoryCreatedEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryCreatedEvent{" +
            "id=" + getId() +
            "}";
    }
}
