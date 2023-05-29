package org.contextmapper.generated.sendquestioncontext.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagInfos} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionSentTagInfosDTO implements Serializable {

    private Long id;

    private Long tagId;

    private String tagName;

    private QuestionSentDTO questionSent;

    private QuestionSentTagInfosViewedEventDTO questionSentTagInfosViewedEvent;

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

    public QuestionSentDTO getQuestionSent() {
        return questionSent;
    }

    public void setQuestionSent(QuestionSentDTO questionSent) {
        this.questionSent = questionSent;
    }

    public QuestionSentTagInfosViewedEventDTO getQuestionSentTagInfosViewedEvent() {
        return questionSentTagInfosViewedEvent;
    }

    public void setQuestionSentTagInfosViewedEvent(QuestionSentTagInfosViewedEventDTO questionSentTagInfosViewedEvent) {
        this.questionSentTagInfosViewedEvent = questionSentTagInfosViewedEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuestionSentTagInfosDTO)) {
            return false;
        }

        QuestionSentTagInfosDTO questionSentTagInfosDTO = (QuestionSentTagInfosDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, questionSentTagInfosDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuestionSentTagInfosDTO{" +
            "id=" + getId() +
            ", tagId=" + getTagId() +
            ", tagName='" + getTagName() + "'" +
            ", questionSent=" + getQuestionSent() +
            ", questionSentTagInfosViewedEvent=" + getQuestionSentTagInfosViewedEvent() +
            "}";
    }
}
