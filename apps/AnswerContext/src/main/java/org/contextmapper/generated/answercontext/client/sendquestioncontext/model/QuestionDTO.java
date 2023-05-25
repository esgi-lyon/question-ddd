package org.contextmapper.generated.answercontext.client.sendquestioncontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.QuestionSentTagInfosDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * QuestionDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-25T18:07:12.588883+02:00[Europe/Paris]")
public class QuestionDTO {

  private Long id;

  private QuestionSentTagInfosDTO resourceCorrectTag;

  public QuestionDTO id(Long id) {
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

  public QuestionDTO resourceCorrectTag(QuestionSentTagInfosDTO resourceCorrectTag) {
    this.resourceCorrectTag = resourceCorrectTag;
    return this;
  }

  /**
   * Get resourceCorrectTag
   * @return resourceCorrectTag
  */
  @Valid 
  @Schema(name = "resourceCorrectTag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("resourceCorrectTag")
  public QuestionSentTagInfosDTO getResourceCorrectTag() {
    return resourceCorrectTag;
  }

  public void setResourceCorrectTag(QuestionSentTagInfosDTO resourceCorrectTag) {
    this.resourceCorrectTag = resourceCorrectTag;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuestionDTO questionDTO = (QuestionDTO) o;
    return Objects.equals(this.id, questionDTO.id) &&
        Objects.equals(this.resourceCorrectTag, questionDTO.resourceCorrectTag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, resourceCorrectTag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuestionDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceCorrectTag: ").append(toIndentedString(resourceCorrectTag)).append("\n");
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

