package org.contextmapper.generated.answercontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.answercontext.domain.TagChoicesListed} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TagChoicesListedDTO implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagChoicesListedDTO)) {
            return false;
        }

        TagChoicesListedDTO tagChoicesListedDTO = (TagChoicesListedDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tagChoicesListedDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TagChoicesListedDTO{" +
            "id=" + getId() +
            "}";
    }
}
