package org.contextmapper.generated.skillcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.skillcontext.domain.TagCreatedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TagCreatedEventDTO implements Serializable {

    private Long id;

    private TagDTO tagId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TagDTO getTagId() {
        return tagId;
    }

    public void setTagId(TagDTO tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagCreatedEventDTO)) {
            return false;
        }

        TagCreatedEventDTO tagCreatedEventDTO = (TagCreatedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tagCreatedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TagCreatedEventDTO{" +
            "id=" + getId() +
            ", tagId=" + getTagId() +
            "}";
    }
}
