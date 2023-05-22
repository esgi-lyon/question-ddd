package org.contextmapper.generated.skillcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.skillcontext.domain.CategoryId} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CategoryIdDTO implements Serializable {

    private Long id;

    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryIdDTO)) {
            return false;
        }

        CategoryIdDTO categoryIdDTO = (CategoryIdDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, categoryIdDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryIdDTO{" +
            "id=" + getId() +
            ", categoryId=" + getCategoryId() +
            "}";
    }
}
