package org.contextmapper.generated.skillcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.skillcontext.domain.CreateTagCommand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreateTagCommandDTO implements Serializable {

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
        if (!(o instanceof CreateTagCommandDTO)) {
            return false;
        }

        CreateTagCommandDTO createTagCommandDTO = (CreateTagCommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, createTagCommandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreateTagCommandDTO{" +
            "id=" + getId() +
            ", tag=" + getTag() +
            "}";
    }
}
