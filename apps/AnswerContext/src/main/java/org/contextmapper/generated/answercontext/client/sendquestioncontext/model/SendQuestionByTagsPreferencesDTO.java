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
 * SendQuestionByTagsPreferencesDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-25T18:07:12.588883+02:00[Europe/Paris]")
public class SendQuestionByTagsPreferencesDTO {

  private Long id;

  private QuestionSentDTO questionSent;

  public SendQuestionByTagsPreferencesDTO id(Long id) {
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

  public SendQuestionByTagsPreferencesDTO questionSent(QuestionSentDTO questionSent) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO = (SendQuestionByTagsPreferencesDTO) o;
    return Objects.equals(this.id, sendQuestionByTagsPreferencesDTO.id) &&
        Objects.equals(this.questionSent, sendQuestionByTagsPreferencesDTO.questionSent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionSent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SendQuestionByTagsPreferencesDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    questionSent: ").append(toIndentedString(questionSent)).append("\n");
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

