package org.contextmapper.generated.skillcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.skillcontext.domain.CategoryCreatedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CategoryCreatedEventDTO implements Serializable {

    private Long id;

    private CategoryDTO categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryDTO getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryDTO categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryCreatedEventDTO)) {
            return false;
        }

        CategoryCreatedEventDTO categoryCreatedEventDTO = (CategoryCreatedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, categoryCreatedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryCreatedEventDTO{" +
            "id=" + getId() +
            ", categoryId=" + getCategoryId() +
            "}";
    }
}
