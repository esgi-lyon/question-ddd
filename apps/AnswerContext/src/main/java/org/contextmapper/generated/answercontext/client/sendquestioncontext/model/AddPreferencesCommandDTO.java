package org.contextmapper.generated.answercontext.client.sendquestioncontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.UserPreferencesTagInfosDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * AddPreferencesCommandDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-29T11:22:11.558807+02:00[Europe/Paris]")
public class AddPreferencesCommandDTO {

  private Long id;

  private UserPreferencesTagInfosDTO preferences;

  public AddPreferencesCommandDTO id(Long id) {
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

  public AddPreferencesCommandDTO preferences(UserPreferencesTagInfosDTO preferences) {
    this.preferences = preferences;
    return this;
  }

  /**
   * Get preferences
   * @return preferences
  */
  @Valid 
  @Schema(name = "preferences", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("preferences")
  public UserPreferencesTagInfosDTO getPreferences() {
    return preferences;
  }

  public void setPreferences(UserPreferencesTagInfosDTO preferences) {
    this.preferences = preferences;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddPreferencesCommandDTO addPreferencesCommandDTO = (AddPreferencesCommandDTO) o;
    return Objects.equals(this.id, addPreferencesCommandDTO.id) &&
        Objects.equals(this.preferences, addPreferencesCommandDTO.preferences);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, preferences);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddPreferencesCommandDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    preferences: ").append(toIndentedString(preferences)).append("\n");
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

