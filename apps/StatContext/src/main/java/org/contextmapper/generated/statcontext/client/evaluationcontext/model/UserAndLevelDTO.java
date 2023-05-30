package org.contextmapper.generated.statcontext.client.evaluationcontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * UserAndLevelDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:14:28.995550+02:00[Europe/Paris]")
public class UserAndLevelDTO {

  private Long id;

  private String mail;

  /**
   * Gets or Sets userLevel
   */
  public enum UserLevelEnum {
    NEW("NEW"),
    
    REGULAR("REGULAR"),
    
    EXPERT("EXPERT");

    private String value;

    UserLevelEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static UserLevelEnum fromValue(String value) {
      for (UserLevelEnum b : UserLevelEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private UserLevelEnum userLevel;

  public UserAndLevelDTO id(Long id) {
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

  public UserAndLevelDTO mail(String mail) {
    this.mail = mail;
    return this;
  }

  /**
   * Get mail
   * @return mail
  */
  
  @Schema(name = "mail", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mail")
  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public UserAndLevelDTO userLevel(UserLevelEnum userLevel) {
    this.userLevel = userLevel;
    return this;
  }

  /**
   * Get userLevel
   * @return userLevel
  */
  
  @Schema(name = "userLevel", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userLevel")
  public UserLevelEnum getUserLevel() {
    return userLevel;
  }

  public void setUserLevel(UserLevelEnum userLevel) {
    this.userLevel = userLevel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserAndLevelDTO userAndLevelDTO = (UserAndLevelDTO) o;
    return Objects.equals(this.id, userAndLevelDTO.id) &&
        Objects.equals(this.mail, userAndLevelDTO.mail) &&
        Objects.equals(this.userLevel, userAndLevelDTO.userLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, mail, userLevel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserAndLevelDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
    sb.append("    userLevel: ").append(toIndentedString(userLevel)).append("\n");
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

