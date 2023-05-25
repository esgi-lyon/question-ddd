package org.contextmapper.generated.answercontext.client.sendquestioncontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * PrepareQuestionsCommand
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-25T18:07:12.588883+02:00[Europe/Paris]")
public class PrepareQuestionsCommand {

  private Long id;

  private Integer tagToPrepareQuestions;

  public PrepareQuestionsCommand id(Long id) {
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

  public PrepareQuestionsCommand tagToPrepareQuestions(Integer tagToPrepareQuestions) {
    this.tagToPrepareQuestions = tagToPrepareQuestions;
    return this;
  }

  /**
   * Get tagToPrepareQuestions
   * @return tagToPrepareQuestions
  */
  
  @Schema(name = "tagToPrepareQuestions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tagToPrepareQuestions")
  public Integer getTagToPrepareQuestions() {
    return tagToPrepareQuestions;
  }

  public void setTagToPrepareQuestions(Integer tagToPrepareQuestions) {
    this.tagToPrepareQuestions = tagToPrepareQuestions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PrepareQuestionsCommand prepareQuestionsCommand = (PrepareQuestionsCommand) o;
    return Objects.equals(this.id, prepareQuestionsCommand.id) &&
        Objects.equals(this.tagToPrepareQuestions, prepareQuestionsCommand.tagToPrepareQuestions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tagToPrepareQuestions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PrepareQuestionsCommand {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tagToPrepareQuestions: ").append(toIndentedString(tagToPrepareQuestions)).append("\n");
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

