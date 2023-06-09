package org.contextmapper.generated.answercontext.client.sendquestioncontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.QuestionSentDTO;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.QuestionSentTagInfosViewedEventDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * QuestionSentTagInfosDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:13:14.383620+02:00[Europe/Paris]")
public class QuestionSentTagInfosDTO {

  private Long id;

  private Long tagId;

  private String tagName;

  private QuestionSentDTO questionSent;

  private QuestionSentTagInfosViewedEventDTO questionSentTagInfosViewedEvent;

  public QuestionSentTagInfosDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public QuestionSentTagInfosDTO tagId(Long tagId) {
    this.tagId = tagId;
    return this;
  }

  /**
   * Get tagId
   * @return tagId
  */
  
  @Schema(name = "tagId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tagId")
  public Long getTagId() {
    return tagId;
  }

  public void setTagId(Long tagId) {
    this.tagId = tagId;
  }

  public QuestionSentTagInfosDTO tagName(String tagName) {
    this.tagName = tagName;
    return this;
  }

  /**
   * Get tagName
   * @return tagName
  */
  
  @Schema(name = "tagName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tagName")
  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  public QuestionSentTagInfosDTO questionSent(QuestionSentDTO questionSent) {
    this.questionSent = questionSent;
    return this;
  }

  /**
   * Get questionSent
   * @return questionSent
  */
  @Valid 
  @Schema(name = "questionSent", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("questionSent")
  public QuestionSentDTO getQuestionSent() {
    return questionSent;
  }

  public void setQuestionSent(QuestionSentDTO questionSent) {
    this.questionSent = questionSent;
  }

  public QuestionSentTagInfosDTO questionSentTagInfosViewedEvent(QuestionSentTagInfosViewedEventDTO questionSentTagInfosViewedEvent) {
    this.questionSentTagInfosViewedEvent = questionSentTagInfosViewedEvent;
    return this;
  }

  /**
   * Get questionSentTagInfosViewedEvent
   * @return questionSentTagInfosViewedEvent
  */
  @Valid 
  @Schema(name = "questionSentTagInfosViewedEvent", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("questionSentTagInfosViewedEvent")
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
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuestionSentTagInfosDTO questionSentTagInfosDTO = (QuestionSentTagInfosDTO) o;
    return Objects.equals(this.id, questionSentTagInfosDTO.id) &&
        Objects.equals(this.tagId, questionSentTagInfosDTO.tagId) &&
        Objects.equals(this.tagName, questionSentTagInfosDTO.tagName) &&
        Objects.equals(this.questionSent, questionSentTagInfosDTO.questionSent) &&
        Objects.equals(this.questionSentTagInfosViewedEvent, questionSentTagInfosDTO.questionSentTagInfosViewedEvent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tagId, tagName, questionSent, questionSentTagInfosViewedEvent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuestionSentTagInfosDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tagId: ").append(toIndentedString(tagId)).append("\n");
    sb.append("    tagName: ").append(toIndentedString(tagName)).append("\n");
    sb.append("    questionSent: ").append(toIndentedString(questionSent)).append("\n");
    sb.append("    questionSentTagInfosViewedEvent: ").append(toIndentedString(questionSentTagInfosViewedEvent)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

