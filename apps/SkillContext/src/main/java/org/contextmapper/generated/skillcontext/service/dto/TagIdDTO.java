package org.contextmapper.generated.skillcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.skillcontext.domain.TagId} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TagIdDTO implements Serializable {

    private Long id;

    private Integer tagId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagIdDTO)) {
            return false;
        }

        TagIdDTO tagIdDTO = (TagIdDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tagIdDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TagIdDTO{" +
            "id=" + getId() +
            ", tagId=" + getTagId() +
            "}";
    }
}
