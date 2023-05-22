package org.contextmapper.generated.answercontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.answercontext.domain.Answer} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnswerDTO implements Serializable {

    private Long id;

    private QuestionIdDTO question;

    private AnsweredTagDTO answeredTag;

    private UserIdDTO userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionIdDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionIdDTO question) {
        this.question = question;
    }

    public AnsweredTagDTO getAnsweredTag() {
        return answeredTag;
    }

    public void setAnsweredTag(AnsweredTagDTO answeredTag) {
        this.answeredTag = answeredTag;
    }

    public UserIdDTO getUserId() {
        return userId;
    }

    public void setUserId(UserIdDTO userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnswerDTO)) {
            return false;
        }

        AnswerDTO answerDTO = (AnswerDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, answerDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnswerDTO{" +
            "id=" + getId() +
            ", question=" + getQuestion() +
            ", answeredTag=" + getAnsweredTag() +
            ", userId=" + getUserId() +
            "}";
    }
}
