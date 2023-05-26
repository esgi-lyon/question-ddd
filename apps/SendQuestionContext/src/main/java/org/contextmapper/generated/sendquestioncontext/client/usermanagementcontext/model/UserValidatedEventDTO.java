package org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.model.UserInfosDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * UserValidatedEventDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-26T09:24:08.668735+02:00[Europe/Paris]")
public class UserValidatedEventDTO {

  private Long id;

  private UserInfosDTO userInfos;

  public UserValidatedEventDTO id(Long id) {
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

  public UserValidatedEventDTO userInfos(UserInfosDTO userInfos) {
    this.userInfos = userInfos;
    return this;
  }

  /**
   * Get userInfos
   * @return userInfos
  */
  @Valid 
  @Schema(name = "userInfos", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userInfos")
  public UserInfosDTO getUserInfos() {
    return userInfos;
  }

  public void setUserInfos(UserInfosDTO userInfos) {
    this.userInfos = userInfos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserValidatedEventDTO userValidatedEventDTO = (UserValidatedEventDTO) o;
    return Objects.equals(this.id, userValidatedEventDTO.id) &&
        Objects.equals(this.userInfos, userValidatedEventDTO.userInfos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userInfos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserValidatedEventDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userInfos: ").append(toIndentedString(userInfos)).append("\n");
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
