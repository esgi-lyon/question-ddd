package org.contextmapper.generated.answercontext.client.sendquestioncontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.UserPreferencesDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * UserPreferencesTagInfosDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:13:14.383620+02:00[Europe/Paris]")
public class UserPreferencesTagInfosDTO {

  private Long id;

  private Long tagId;

  private UserPreferencesDTO userPreferences;

  public UserPreferencesTagInfosDTO id(Long id) {
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

  public UserPreferencesTagInfosDTO tagId(Long tagId) {
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

  public UserPreferencesTagInfosDTO userPreferences(UserPreferencesDTO userPreferences) {
    this.userPreferences = userPreferences;
    return this;
  }

  /**
   * Get userPreferences
   * @return userPreferences
  */
  @Valid 
  @Schema(name = "userPreferences", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userPreferences")
  public UserPreferencesDTO getUserPreferences() {
    return userPreferences;
  }

  public void setUserPreferences(UserPreferencesDTO userPreferences) {
    this.userPreferences = userPreferences;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserPreferencesTagInfosDTO userPreferencesTagInfosDTO = (UserPreferencesTagInfosDTO) o;
    return Objects.equals(this.id, userPreferencesTagInfosDTO.id) &&
        Objects.equals(this.tagId, userPreferencesTagInfosDTO.tagId) &&
        Objects.equals(this.userPreferences, userPreferencesTagInfosDTO.userPreferences);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tagId, userPreferences);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserPreferencesTagInfosDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tagId: ").append(toIndentedString(tagId)).append("\n");
    sb.append("    userPreferences: ").append(toIndentedString(userPreferences)).append("\n");
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

