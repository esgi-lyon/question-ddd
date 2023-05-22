package org.contextmapper.generated.statcontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.statcontext.domain.StatisticSubjectTag} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class StatisticSubjectTagDTO implements Serializable {

    private Long id;

    private Long tagId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StatisticSubjectTagDTO)) {
            return false;
        }

        StatisticSubjectTagDTO statisticSubjectTagDTO = (StatisticSubjectTagDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, statisticSubjectTagDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StatisticSubjectTagDTO{" +
            "id=" + getId() +
            ", tagId=" + getTagId() +
            "}";
    }
}
