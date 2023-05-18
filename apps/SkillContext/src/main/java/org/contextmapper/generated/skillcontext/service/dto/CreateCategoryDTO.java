package org.contextmapper.generated.skillcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.skillcontext.domain.CreateCategory} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreateCategoryDTO implements Serializable {

    private Long id;

    private CategoryDTO category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateCategoryDTO)) {
            return false;
        }

        CreateCategoryDTO createCategoryDTO = (CreateCategoryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, createCategoryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreateCategoryDTO{" +
            "id=" + getId() +
            ", category=" + getCategory() +
            "}";
    }
}
