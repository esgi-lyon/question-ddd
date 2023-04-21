package org.contextmapper.generated.questioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.questioncontext.domain.QuestionResource} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionResourceDTO implements Serializable {

    private Long id;

    private String questionContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionResourceDTO)) {
            return false;
        }

        QuestionResourceDTO questionResourceDTO = (QuestionResourceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionResourceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionResourceDTO{" +
            "id=" + getId() +
            ", questionContent='" + getQuestionContent() + "'" +
            "}";
    }
}
