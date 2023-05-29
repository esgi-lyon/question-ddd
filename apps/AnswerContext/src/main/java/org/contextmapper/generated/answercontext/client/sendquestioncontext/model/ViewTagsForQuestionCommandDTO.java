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
 * ViewTagsForQuestionCommandDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-29T11:22:11.558807+02:00[Europe/Paris]")
public class ViewTagsForQuestionCommandDTO {

  private Long id;

  private QuestionSentDTO questionToSend;

  public ViewTagsForQuestionCommandDTO id(Long id) {
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

  public ViewTagsForQuestionCommandDTO questionToSend(QuestionSentDTO questionToSend) {
    this.questionToSend = questionToSend;
    return this;
  }

  /**
   * Get questionToSend
   * @return questionToSend
  */
  @Valid 
  @Schema(name = "questionToSend", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("questionToSend")
  public QuestionSentDTO getQuestionToSend() {
    return questionToSend;
  }

  public void setQuestionToSend(QuestionSentDTO questionToSend) {
    this.questionToSend = questionToSend;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ViewTagsForQuestionCommandDTO viewTagsForQuestionCommandDTO = (ViewTagsForQuestionCommandDTO) o;
    return Objects.equals(this.id, viewTagsForQuestionCommandDTO.id) &&
        Objects.equals(this.questionToSend, viewTagsForQuestionCommandDTO.questionToSend);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionToSend);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ViewTagsForQuestionCommandDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    questionToSend: ").append(toIndentedString(questionToSend)).append("\n");
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

