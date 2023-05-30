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
 * NotifiedUsersDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:13:14.383620+02:00[Europe/Paris]")
public class NotifiedUsersDTO {

  private Long id;

  private QuestionSentDTO question;

  public NotifiedUsersDTO id(Long id) {
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

  public NotifiedUsersDTO question(QuestionSentDTO question) {
    this.question = question;
    return this;
  }

  /**
   * Get question
   * @return question
  */
  @Valid 
  @Schema(name = "question", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("question")
  public QuestionSentDTO getQuestion() {
    return question;
  }

  public void setQuestion(QuestionSentDTO question) {
    this.question = question;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NotifiedUsersDTO notifiedUsersDTO = (NotifiedUsersDTO) o;
    return Objects.equals(this.id, notifiedUsersDTO.id) &&
        Objects.equals(this.question, notifiedUsersDTO.question);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, question);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NotifiedUsersDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    question: ").append(toIndentedString(question)).append("\n");
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

