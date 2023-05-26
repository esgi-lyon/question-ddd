package org.contextmapper.generated.answercontext.client.sendquestioncontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * QuestionSent
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-26T09:39:32.961922+02:00[Europe/Paris]")
public class QuestionSent {

  private Long id;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate sentDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate viewedDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate answeredDate;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    SENT("SENT"),
    
    VIEWED("VIEWED"),
    
    ANSWERED("ANSWERED");

    private String value;

    StatusEnum(String value) {
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
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private StatusEnum status;

  public QuestionSent id(Long id) {
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

  public QuestionSent sentDate(LocalDate sentDate) {
    this.sentDate = sentDate;
    return this;
  }

  /**
   * Get sentDate
   * @return sentDate
  */
  @Valid 
  @Schema(name = "sentDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sentDate")
  public LocalDate getSentDate() {
    return sentDate;
  }

  public void setSentDate(LocalDate sentDate) {
    this.sentDate = sentDate;
  }

  public QuestionSent viewedDate(LocalDate viewedDate) {
    this.viewedDate = viewedDate;
    return this;
  }

  /**
   * Get viewedDate
   * @return viewedDate
  */
  @Valid 
  @Schema(name = "viewedDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("viewedDate")
  public LocalDate getViewedDate() {
    return viewedDate;
  }

  public void setViewedDate(LocalDate viewedDate) {
    this.viewedDate = viewedDate;
  }

  public QuestionSent answeredDate(LocalDate answeredDate) {
    this.answeredDate = answeredDate;
    return this;
  }

  /**
   * Get answeredDate
   * @return answeredDate
  */
  @Valid 
  @Schema(name = "answeredDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("answeredDate")
  public LocalDate getAnsweredDate() {
    return answeredDate;
  }

  public void setAnsweredDate(LocalDate answeredDate) {
    this.answeredDate = answeredDate;
  }

  public QuestionSent status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuestionSent questionSent = (QuestionSent) o;
    return Objects.equals(this.id, questionSent.id) &&
        Objects.equals(this.sentDate, questionSent.sentDate) &&
        Objects.equals(this.viewedDate, questionSent.viewedDate) &&
        Objects.equals(this.answeredDate, questionSent.answeredDate) &&
        Objects.equals(this.status, questionSent.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, sentDate, viewedDate, answeredDate, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuestionSent {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    sentDate: ").append(toIndentedString(sentDate)).append("\n");
    sb.append("    viewedDate: ").append(toIndentedString(viewedDate)).append("\n");
    sb.append("    answeredDate: ").append(toIndentedString(answeredDate)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

