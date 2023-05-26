package org.contextmapper.generated.evaluationcontext.client.answercontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.evaluationcontext.client.answercontext.model.QuestionSentId;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * TagChoicesListCommand
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-26T09:42:18.133357+02:00[Europe/Paris]")
public class TagChoicesListCommand {

  private Long id;

  private QuestionSentId questionSent;

  public TagChoicesListCommand id(Long id) {
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

  public TagChoicesListCommand questionSent(QuestionSentId questionSent) {
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
  public QuestionSentId getQuestionSent() {
    return questionSent;
  }

  public void setQuestionSent(QuestionSentId questionSent) {
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
    TagChoicesListCommand tagChoicesListCommand = (TagChoicesListCommand) o;
    return Objects.equals(this.id, tagChoicesListCommand.id) &&
        Objects.equals(this.questionSent, tagChoicesListCommand.questionSent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionSent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TagChoicesListCommand {\n");
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

