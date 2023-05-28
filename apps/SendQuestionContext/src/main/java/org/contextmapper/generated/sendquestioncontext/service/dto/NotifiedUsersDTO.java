package org.contextmapper.generated.sendquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.sendquestioncontext.domain.NotifiedUsers} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NotifiedUsersDTO implements Serializable {

    private Long id;

    private QuestionSentDTO question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionSentDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionSentDTO question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotifiedUsersDTO)) {
            return false;
        }

        NotifiedUsersDTO notifiedUsersDTO = (NotifiedUsersDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, notifiedUsersDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotifiedUsersDTO{" +
            "id=" + getId() +
            ", question=" + getQuestion() +
            "}";
    }
}
