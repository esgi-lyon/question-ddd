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
 * NotifiedQuestionDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-25T18:07:12.588883+02:00[Europe/Paris]")
public class NotifiedQuestionDTO {

  private Long id;

  private QuestionSentDTO questionResource;

  public NotifiedQuestionDTO id(Long id) {
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

  public NotifiedQuestionDTO questionResource(QuestionSentDTO questionResource) {
    this.questionResource = questionResource;
    return this;
  }

  /**
   * Get questionResource
   * @return questionResource
  */
  @Valid 
  @Schema(name = "questionResource", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("questionResource")
  public QuestionSentDTO getQuestionResource() {
    return questionResource;
  }

  public void setQuestionResource(QuestionSentDTO questionResource) {
    this.questionResource = questionResource;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NotifiedQuestionDTO notifiedQuestionDTO = (NotifiedQuestionDTO) o;
    return Objects.equals(this.id, notifiedQuestionDTO.id) &&
        Objects.equals(this.questionResource, notifiedQuestionDTO.questionResource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionResource);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NotifiedQuestionDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    questionResource: ").append(toIndentedString(questionResource)).append("\n");
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

