package org.contextmapper.generated.sendquestioncontext.client.questioncontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.model.QuestionResource;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ValidateResourceTagLinkageCommand
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-25T13:29:24.478064+02:00[Europe/Paris]")
public class ValidateResourceTagLinkageCommand {

  private Long id;

  private QuestionResource questionId;

  public ValidateResourceTagLinkageCommand id(Long id) {
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

  public ValidateResourceTagLinkageCommand questionId(QuestionResource questionId) {
    this.questionId = questionId;
    return this;
  }

  /**
   * Get questionId
   * @return questionId
  */
  @Valid 
  @Schema(name = "questionId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("questionId")
  public QuestionResource getQuestionId() {
    return questionId;
  }

  public void setQuestionId(QuestionResource questionId) {
    this.questionId = questionId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand = (ValidateResourceTagLinkageCommand) o;
    return Objects.equals(this.id, validateResourceTagLinkageCommand.id) &&
        Objects.equals(this.questionId, validateResourceTagLinkageCommand.questionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidateResourceTagLinkageCommand {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    questionId: ").append(toIndentedString(questionId)).append("\n");
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

