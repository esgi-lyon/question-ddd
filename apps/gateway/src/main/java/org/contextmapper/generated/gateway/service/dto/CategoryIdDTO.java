package org.contextmapper.generated.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.gateway.domain.CategoryId} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CategoryIdDTO implements Serializable {

    private Long id;

    private Integer categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
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
