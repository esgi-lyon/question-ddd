package org.contextmapper.generated.answercontext.client.usermanagement.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.answercontext.client.usermanagement.model.UserInfos;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ValidateUserCommand
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-25T13:12:13.027502+02:00[Europe/Paris]")
public class ValidateUserCommand {

  private Long id;

  private UserInfos userInfos;

  public ValidateUserCommand id(Long id) {
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

  public ValidateUserCommand userInfos(UserInfos userInfos) {
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
  public UserInfos getUserInfos() {
    return userInfos;
  }

  public void setUserInfos(UserInfos userInfos) {
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
    ValidateUserCommand validateUserCommand = (ValidateUserCommand) o;
    return Objects.equals(this.id, validateUserCommand.id) &&
        Objects.equals(this.userInfos, validateUserCommand.userInfos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userInfos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidateUserCommand {\n");
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

