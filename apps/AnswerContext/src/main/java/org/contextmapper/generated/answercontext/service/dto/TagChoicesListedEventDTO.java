package org.contextmapper.generated.answercontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.answercontext.domain.TagChoicesListedEvent} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TagChoicesListedEventDTO implements Serializable {

    private Long id;

    private AnswerDTO answerCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnswerDTO getAnswerCreated() {
        return answerCreated;
    }

    public void setAnswerCreated(AnswerDTO answerCreated) {
        this.answerCreated = answerCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagChoicesListedEventDTO)) {
            return false;
        }

        TagChoicesListedEventDTO tagChoicesListedEventDTO = (TagChoicesListedEventDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tagChoicesListedEventDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TagChoicesListedEventDTO{" +
            "id=" + getId() +
            ", answerCreated=" + getAnswerCreated() +
            "}";
    }
}
