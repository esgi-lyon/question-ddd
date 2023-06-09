package org.contextmapper.generated.answercontext.client.sendquestioncontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.NotifiedUsersDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * UserWithPreferencesIdDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:13:14.383620+02:00[Europe/Paris]")
public class UserWithPreferencesIdDTO {

  private Long id;

  private String mail;

  private NotifiedUsersDTO notifiedUsers;

  public UserWithPreferencesIdDTO id(Long id) {
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

  public UserWithPreferencesIdDTO mail(String mail) {
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

  public UserWithPreferencesIdDTO notifiedUsers(NotifiedUsersDTO notifiedUsers) {
    this.notifiedUsers = notifiedUsers;
    return this;
  }

  /**
   * Get notifiedUsers
   * @return notifiedUsers
  */
  @Valid 
  @Schema(name = "notifiedUsers", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("notifiedUsers")
  public NotifiedUsersDTO getNotifiedUsers() {
    return notifiedUsers;
  }

  public void setNotifiedUsers(NotifiedUsersDTO notifiedUsers) {
    this.notifiedUsers = notifiedUsers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserWithPreferencesIdDTO userWithPreferencesIdDTO = (UserWithPreferencesIdDTO) o;
    return Objects.equals(this.id, userWithPreferencesIdDTO.id) &&
        Objects.equals(this.mail, userWithPreferencesIdDTO.mail) &&
        Objects.equals(this.notifiedUsers, userWithPreferencesIdDTO.notifiedUsers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, mail, notifiedUsers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserWithPreferencesIdDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
    sb.append("    notifiedUsers: ").append(toIndentedString(notifiedUsers)).append("\n");
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

