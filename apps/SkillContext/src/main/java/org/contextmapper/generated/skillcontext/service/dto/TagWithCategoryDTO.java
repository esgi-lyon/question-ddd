package org.contextmapper.generated.skillcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.skillcontext.domain.TagWithCategory} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TagWithCategoryDTO implements Serializable {

    private Long id;

    private String name;

    private Long category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagWithCategoryDTO)) {
            return false;
        }

        TagWithCategoryDTO tagWithCategoryDTO = (TagWithCategoryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tagWithCategoryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TagWithCategoryDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", category=" + getCategory() +
            "}";
    }
}
