package org.contextmapper.generated.answercontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.answercontext.domain.AvailableAnswer} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AvailableAnswerDTO implements Serializable {

    private Long id;

    private Long tagId;

    private String tagName;

    private TagChoicesListedEventDTO tagChoicesListedEvent;

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

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public TagChoicesListedEventDTO getTagChoicesListedEvent() {
        return tagChoicesListedEvent;
    }

    public void setTagChoicesListedEvent(TagChoicesListedEventDTO tagChoicesListedEvent) {
        this.tagChoicesListedEvent = tagChoicesListedEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AvailableAnswerDTO)) {
            return false;
        }

        AvailableAnswerDTO availableAnswerDTO = (AvailableAnswerDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, availableAnswerDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AvailableAnswerDTO{" +
            "id=" + getId() +
            ", tagId=" + getTagId() +
            ", tagName='" + getTagName() + "'" +
            ", tagChoicesListedEvent=" + getTagChoicesListedEvent() +
            "}";
    }
}
