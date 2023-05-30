package org.contextmapper.generated.statcontext.client.evaluationcontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * AnsweringUserDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T11:17:53.389461+02:00[Europe/Paris]")
public class AnsweringUserDTO {

  private Long id;

  private String mail;

  public AnsweringUserDTO id(Long id) {
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

  public AnsweringUserDTO mail(String mail) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnsweringUserDTO answeringUserDTO = (AnsweringUserDTO) o;
    return Objects.equals(this.id, answeringUserDTO.id) &&
        Objects.equals(this.mail, answeringUserDTO.mail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, mail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnsweringUserDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
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

