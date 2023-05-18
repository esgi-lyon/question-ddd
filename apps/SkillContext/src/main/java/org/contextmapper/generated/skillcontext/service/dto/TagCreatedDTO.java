package org.contextmapper.generated.skillcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.skillcontext.domain.TagCreated} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TagCreatedDTO implements Serializable {

    private Long id;

    private TagInfosDTO tagId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TagInfosDTO getTagId() {
        return tagId;
    }

    public void setTagId(TagInfosDTO tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagCreatedDTO)) {
            return false;
        }

        TagCreatedDTO tagCreatedDTO = (TagCreatedDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tagCreatedDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TagCreatedDTO{" +
            "id=" + getId() +
            ", tagId=" + getTagId() +
            "}";
    }
}
