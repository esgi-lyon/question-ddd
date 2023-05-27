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
 * UserViewedEventDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-27T16:47:18.111395+02:00[Europe/Paris]")
public class UserViewedEventDTO {

  private Long id;

  private UserInfosDTO userInfos;

  public UserViewedEventDTO id(Long id) {
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

  public UserViewedEventDTO userInfos(UserInfosDTO userInfos) {
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
    UserViewedEventDTO userViewedEventDTO = (UserViewedEventDTO) o;
    return Objects.equals(this.id, userViewedEventDTO.id) &&
        Objects.equals(this.userInfos, userViewedEventDTO.userInfos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userInfos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserViewedEventDTO {\n");
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

