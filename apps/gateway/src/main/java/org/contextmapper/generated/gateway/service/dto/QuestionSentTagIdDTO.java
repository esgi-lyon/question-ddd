package org.contextmapper.generated.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.gateway.domain.QuestionSentTagId} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionSentTagIdDTO implements Serializable {

    private Long id;

    private Integer tagId;

    private QuestionSentDTO questionSent;

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

    public QuestionSentDTO getQuestionSent() {
        return questionSent;
    }

    public void setQuestionSent(QuestionSentDTO questionSent) {
        this.questionSent = questionSent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionSentTagIdDTO)) {
            return false;
        }

        QuestionSentTagIdDTO questionSentTagIdDTO = (QuestionSentTagIdDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionSentTagIdDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionSentTagIdDTO{" +
            "id=" + getId() +
            ", tagId=" + getTagId() +
            ", questionSent=" + getQuestionSent() +
            "}";
    }
}
