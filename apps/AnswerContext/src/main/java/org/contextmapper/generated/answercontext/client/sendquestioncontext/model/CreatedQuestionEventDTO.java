package org.contextmapper.generated.answercontext.client.sendquestioncontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.QuestionSentDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * CreatedQuestionEventDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:13:14.383620+02:00[Europe/Paris]")
public class CreatedQuestionEventDTO {

  private Long id;

  private QuestionSentDTO questionAndTag;

  public CreatedQuestionEventDTO id(Long id) {
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

  public CreatedQuestionEventDTO questionAndTag(QuestionSentDTO questionAndTag) {
    this.questionAndTag = questionAndTag;
    return this;
  }

  /**
   * Get questionAndTag
   * @return questionAndTag
  */
  @Valid 
  @Schema(name = "questionAndTag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("questionAndTag")
  public QuestionSentDTO getQuestionAndTag() {
    return questionAndTag;
  }

  public void setQuestionAndTag(QuestionSentDTO questionAndTag) {
    this.questionAndTag = questionAndTag;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreatedQuestionEventDTO createdQuestionEventDTO = (CreatedQuestionEventDTO) o;
    return Objects.equals(this.id, createdQuestionEventDTO.id) &&
        Objects.equals(this.questionAndTag, createdQuestionEventDTO.questionAndTag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionAndTag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreatedQuestionEventDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    questionAndTag: ").append(toIndentedString(questionAndTag)).append("\n");
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

