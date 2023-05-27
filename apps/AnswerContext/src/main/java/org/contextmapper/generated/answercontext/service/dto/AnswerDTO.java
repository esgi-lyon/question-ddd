package org.contextmapper.generated.answercontext.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.contextmapper.generated.answercontext.domain.enumeration.AnswerState;

/**
 * A DTO for the {@link org.contextmapper.generated.answercontext.domain.Answer} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnswerDTO implements Serializable {

    private Long id;

    private AnswerState answerState;

    private QuestionSentIdDTO question;

    private AnsweredTagDTO answeredTag;

    private UserEmailDTO userEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnswerState getAnswerState() {
        return answerState;
    }

    public void setAnswerState(AnswerState answerState) {
        this.answerState = answerState;
    }

    public QuestionSentIdDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionSentIdDTO question) {
        this.question = question;
    }

    public AnsweredTagDTO getAnsweredTag() {
        return answeredTag;
    }

    public void setAnsweredTag(AnsweredTagDTO answeredTag) {
        this.answeredTag = answeredTag;
    }

    public UserEmailDTO getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(UserEmailDTO userEmail) {
        this.userEmail = userEmail;
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
            ", answerState='" + getAnswerState() + "'" +
            ", question=" + getQuestion() +
            ", answeredTag=" + getAnsweredTag() +
            ", userEmail=" + getUserEmail() +
            "}";
    }
}
