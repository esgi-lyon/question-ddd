package org.contextmapper.generated.sendquestioncontext.client.skillcontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.sendquestioncontext.client.skillcontext.model.TagDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * CreateTagCommandDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:12:46.072606+02:00[Europe/Paris]")
public class CreateTagCommandDTO {

  private Long id;

  private TagDTO tag;

  public CreateTagCommandDTO id(Long id) {
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

  public CreateTagCommandDTO tag(TagDTO tag) {
    this.tag = tag;
    return this;
  }

  /**
   * Get tag
   * @return tag
  */
  @Valid 
  @Schema(name = "tag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tag")
  public TagDTO getTag() {
    return tag;
  }

  public void setTag(TagDTO tag) {
    this.tag = tag;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateTagCommandDTO createTagCommandDTO = (CreateTagCommandDTO) o;
    return Objects.equals(this.id, createTagCommandDTO.id) &&
        Objects.equals(this.tag, createTagCommandDTO.tag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateTagCommandDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tag: ").append(toIndentedString(tag)).append("\n");
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

