package org.contextmapper.generated.answercontext.client.sendquestioncontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.QuestionSentQuestionResourceTagId;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * CreateQuestionCommand
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-25T18:07:12.588883+02:00[Europe/Paris]")
public class CreateQuestionCommand {

  private Long id;

  private QuestionSentQuestionResourceTagId resource;

  public CreateQuestionCommand id(Long id) {
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

  public CreateQuestionCommand resource(QuestionSentQuestionResourceTagId resource) {
    this.resource = resource;
    return this;
  }

  /**
   * Get resource
   * @return resource
  */
  @Valid 
  @Schema(name = "resource", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("resource")
  public QuestionSentQuestionResourceTagId getResource() {
    return resource;
  }

  public void setResource(QuestionSentQuestionResourceTagId resource) {
    this.resource = resource;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateQuestionCommand createQuestionCommand = (CreateQuestionCommand) o;
    return Objects.equals(this.id, createQuestionCommand.id) &&
        Objects.equals(this.resource, createQuestionCommand.resource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, resource);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateQuestionCommand {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
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

