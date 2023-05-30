package org.contextmapper.generated.evaluationcontext.client.answercontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.evaluationcontext.client.answercontext.model.AnswerDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * TagChoicesListedEventDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:13:50.158415+02:00[Europe/Paris]")
public class TagChoicesListedEventDTO {

  private Long id;

  private AnswerDTO answerCreated;

  public TagChoicesListedEventDTO id(Long id) {
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

  public TagChoicesListedEventDTO answerCreated(AnswerDTO answerCreated) {
    this.answerCreated = answerCreated;
    return this;
  }

  /**
   * Get answerCreated
   * @return answerCreated
  */
  @Valid 
  @Schema(name = "answerCreated", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("answerCreated")
  public AnswerDTO getAnswerCreated() {
    return answerCreated;
  }

  public void setAnswerCreated(AnswerDTO answerCreated) {
    this.answerCreated = answerCreated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TagChoicesListedEventDTO tagChoicesListedEventDTO = (TagChoicesListedEventDTO) o;
    return Objects.equals(this.id, tagChoicesListedEventDTO.id) &&
        Objects.equals(this.answerCreated, tagChoicesListedEventDTO.answerCreated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, answerCreated);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TagChoicesListedEventDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    answerCreated: ").append(toIndentedString(answerCreated)).append("\n");
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

