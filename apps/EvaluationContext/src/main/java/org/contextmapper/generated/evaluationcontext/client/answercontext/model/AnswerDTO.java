package org.contextmapper.generated.evaluationcontext.client.answercontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.contextmapper.generated.evaluationcontext.client.answercontext.model.AnsweredTagDTO;
import org.contextmapper.generated.evaluationcontext.client.answercontext.model.QuestionSentIdDTO;
import org.contextmapper.generated.evaluationcontext.client.answercontext.model.UserIdDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * AnswerDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-26T09:42:18.133357+02:00[Europe/Paris]")
public class AnswerDTO {

  private Long id;

  /**
   * Gets or Sets answerState
   */
  public enum AnswerStateEnum {
    DONE("DONE"),
    
    OPEN("OPEN");

    private String value;

    AnswerStateEnum(String value) {
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
    public static AnswerStateEnum fromValue(String value) {
      for (AnswerStateEnum b : AnswerStateEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private AnswerStateEnum answerState;

  private QuestionSentIdDTO question;

  private AnsweredTagDTO answeredTag;

  private UserIdDTO userId;

  public AnswerDTO id(Long id) {
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

  public AnswerDTO answerState(AnswerStateEnum answerState) {
    this.answerState = answerState;
    return this;
  }

  /**
   * Get answerState
   * @return answerState
  */
  
  @Schema(name = "answerState", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("answerState")
  public AnswerStateEnum getAnswerState() {
    return answerState;
  }

  public void setAnswerState(AnswerStateEnum answerState) {
    this.answerState = answerState;
  }

  public AnswerDTO question(QuestionSentIdDTO question) {
    this.question = question;
    return this;
  }

  /**
   * Get question
   * @return question
  */
  @Valid 
  @Schema(name = "question", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("question")
  public QuestionSentIdDTO getQuestion() {
    return question;
  }

  public void setQuestion(QuestionSentIdDTO question) {
    this.question = question;
  }

  public AnswerDTO answeredTag(AnsweredTagDTO answeredTag) {
    this.answeredTag = answeredTag;
    return this;
  }

  /**
   * Get answeredTag
   * @return answeredTag
  */
  @Valid 
  @Schema(name = "answeredTag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("answeredTag")
  public AnsweredTagDTO getAnsweredTag() {
    return answeredTag;
  }

  public void setAnsweredTag(AnsweredTagDTO answeredTag) {
    this.answeredTag = answeredTag;
  }

  public AnswerDTO userId(UserIdDTO userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  @Valid 
  @Schema(name = "userId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userId")
  public UserIdDTO getUserId() {
    return userId;
  }

  public void setUserId(UserIdDTO userId) {
    this.userId = userId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnswerDTO answerDTO = (AnswerDTO) o;
    return Objects.equals(this.id, answerDTO.id) &&
        Objects.equals(this.answerState, answerDTO.answerState) &&
        Objects.equals(this.question, answerDTO.question) &&
        Objects.equals(this.answeredTag, answerDTO.answeredTag) &&
        Objects.equals(this.userId, answerDTO.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, answerState, question, answeredTag, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnswerDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    answerState: ").append(toIndentedString(answerState)).append("\n");
    sb.append("    question: ").append(toIndentedString(question)).append("\n");
    sb.append("    answeredTag: ").append(toIndentedString(answeredTag)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

