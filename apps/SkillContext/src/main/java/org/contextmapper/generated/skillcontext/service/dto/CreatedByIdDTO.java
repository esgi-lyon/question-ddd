package org.contextmapper.generated.skillcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.skillcontext.domain.CreatedById} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreatedByIdDTO implements Serializable {

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
        if (!(o instanceof CreatedByIdDTO)) {
            return false;
        }

        CreatedByIdDTO createdByIdDTO = (CreatedByIdDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, createdByIdDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreatedByIdDTO{" +
            "id=" + getId() +
            "}";
    }
}
