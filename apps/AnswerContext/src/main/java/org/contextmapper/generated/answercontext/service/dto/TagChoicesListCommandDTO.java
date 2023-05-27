package org.contextmapper.generated.answercontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.answercontext.domain.TagChoicesListCommand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TagChoicesListCommandDTO implements Serializable {

    private Long id;

    private QuestionSentIdDTO questionSent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionSentIdDTO getQuestionSent() {
        return questionSent;
    }

    public void setQuestionSent(QuestionSentIdDTO questionSent) {
        this.questionSent = questionSent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagChoicesListCommandDTO)) {
            return false;
        }

        TagChoicesListCommandDTO tagChoicesListCommandDTO = (TagChoicesListCommandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tagChoicesListCommandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TagChoicesListCommandDTO{" +
            "id=" + getId() +
            ", questionSent=" + getQuestionSent() +
            "}";
    }
}
