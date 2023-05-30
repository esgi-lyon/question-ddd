package org.contextmapper.generated.evaluationcontext.client.answercontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.evaluationcontext.client.answercontext.model.TagChoicesListedEventDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * AvailableAnswerDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:13:50.158415+02:00[Europe/Paris]")
public class AvailableAnswerDTO {

  private Long id;

  private Long tagId;

  private String tagName;

  private TagChoicesListedEventDTO tagChoicesListedEvent;

  public AvailableAnswerDTO id(Long id) {
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

  public AvailableAnswerDTO tagId(Long tagId) {
    this.tagId = tagId;
    return this;
  }

  /**
   * Get tagId
   * @return tagId
  */
  
  @Schema(name = "tagId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tagId")
  public Long getTagId() {
    return tagId;
  }

  public void setTagId(Long tagId) {
    this.tagId = tagId;
  }

  public AvailableAnswerDTO tagName(String tagName) {
    this.tagName = tagName;
    return this;
  }

  /**
   * Get tagName
   * @return tagName
  */
  
  @Schema(name = "tagName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tagName")
  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  public AvailableAnswerDTO tagChoicesListedEvent(TagChoicesListedEventDTO tagChoicesListedEvent) {
    this.tagChoicesListedEvent = tagChoicesListedEvent;
    return this;
  }

  /**
   * Get tagChoicesListedEvent
   * @return tagChoicesListedEvent
  */
  @Valid 
  @Schema(name = "tagChoicesListedEvent", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tagChoicesListedEvent")
  public TagChoicesListedEventDTO getTagChoicesListedEvent() {
    return tagChoicesListedEvent;
  }

  public void setTagChoicesListedEvent(TagChoicesListedEventDTO tagChoicesListedEvent) {
    this.tagChoicesListedEvent = tagChoicesListedEvent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AvailableAnswerDTO availableAnswerDTO = (AvailableAnswerDTO) o;
    return Objects.equals(this.id, availableAnswerDTO.id) &&
        Objects.equals(this.tagId, availableAnswerDTO.tagId) &&
        Objects.equals(this.tagName, availableAnswerDTO.tagName) &&
        Objects.equals(this.tagChoicesListedEvent, availableAnswerDTO.tagChoicesListedEvent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tagId, tagName, tagChoicesListedEvent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AvailableAnswerDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tagId: ").append(toIndentedString(tagId)).append("\n");
    sb.append("    tagName: ").append(toIndentedString(tagName)).append("\n");
    sb.append("    tagChoicesListedEvent: ").append(toIndentedString(tagChoicesListedEvent)).append("\n");
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

