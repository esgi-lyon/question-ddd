package org.contextmapper.generated.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.contextmapper.generated.gateway.domain.QuestionSentTagInfos} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuestionSentTagInfosDTO implements Serializable {

    private Long id;

    private Integer tagId;

    private String name;

    private QuestionDTO question;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDTO question) {
        this.question = question;
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
            ", name='" + getName() + "'" +
            ", question=" + getQuestion() +
            "}";
    }
}
