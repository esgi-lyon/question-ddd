package org.contextmapper.generated.skillcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.skillcontext.domain.CreateCategoryCommand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreateCategoryCommandDTO implements Serializable {

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
        if (!(o instanceof CreateCategoryCommandDTO)) {
            return false;
        }

        CreateCategoryCommandDTO createCategoryCommandDTO = (CreateCategoryCommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, createCategoryCommandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreateCategoryCommandDTO{" +
            "id=" + getId() +
            ", category=" + getCategory() +
            "}";
    }
}
