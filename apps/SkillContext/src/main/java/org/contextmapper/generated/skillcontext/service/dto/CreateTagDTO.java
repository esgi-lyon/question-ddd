package org.contextmapper.generated.skillcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.skillcontext.domain.CreateTag} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreateTagDTO implements Serializable {

    private Long id;

    private TagDTO tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TagDTO getTag() {
        return tag;
    }

    public void setTag(TagDTO tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateTagDTO)) {
            return false;
        }

        CreateTagDTO createTagDTO = (CreateTagDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, createTagDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreateTagDTO{" +
            "id=" + getId() +
            ", tag=" + getTag() +
            "}";
    }
}
