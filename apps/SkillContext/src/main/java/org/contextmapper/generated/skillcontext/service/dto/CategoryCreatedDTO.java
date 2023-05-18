package org.contextmapper.generated.skillcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.skillcontext.domain.CategoryCreated} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CategoryCreatedDTO implements Serializable {

    private Long id;

    private CategoryIdDTO categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryIdDTO getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryIdDTO categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryCreatedDTO)) {
            return false;
        }

        CategoryCreatedDTO categoryCreatedDTO = (CategoryCreatedDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, categoryCreatedDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryCreatedDTO{" +
            "id=" + getId() +
            ", categoryId=" + getCategoryId() +
            "}";
    }
}
