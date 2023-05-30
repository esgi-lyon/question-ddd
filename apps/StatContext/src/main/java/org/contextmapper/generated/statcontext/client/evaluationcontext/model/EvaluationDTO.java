package org.contextmapper.generated.statcontext.client.evaluationcontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.contextmapper.generated.statcontext.client.evaluationcontext.model.AnsweringUserDTO;
import org.contextmapper.generated.statcontext.client.evaluationcontext.model.EvaluationQuestionDTO;
import org.contextmapper.generated.statcontext.client.evaluationcontext.model.EvaluationTagDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * EvaluationDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T11:17:53.389461+02:00[Europe/Paris]")
public class EvaluationDTO {

  private Long id;

  private Integer score;

  private String evaluatorMail;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    VALID("VALID"),
    
    INVALID("INVALID"),
    
    OPENED("OPENED");

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

  /**
   * Gets or Sets answeredQuestionDifficultyLevel
   */
  public enum AnsweredQuestionDifficultyLevelEnum {
    EASY("EASY"),
    
    MEDIUM("MEDIUM"),
    
    HARD("HARD");

    private String value;

    AnsweredQuestionDifficultyLevelEnum(String value) {
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
    public static AnsweredQuestionDifficultyLevelEnum fromValue(String value) {
      for (AnsweredQuestionDifficultyLevelEnum b : AnsweredQuestionDifficultyLevelEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private AnsweredQuestionDifficultyLevelEnum answeredQuestionDifficultyLevel;

  private EvaluationTagDTO tag;

  private EvaluationQuestionDTO question;

  private AnsweringUserDTO user;

  public EvaluationDTO id(Long id) {
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

  public EvaluationDTO score(Integer score) {
    this.score = score;
    return this;
  }

  /**
   * Get score
   * @return score
  */
  
  @Schema(name = "score", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("score")
  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public EvaluationDTO evaluatorMail(String evaluatorMail) {
    this.evaluatorMail = evaluatorMail;
    return this;
  }

  /**
   * Get evaluatorMail
   * @return evaluatorMail
  */
  
  @Schema(name = "evaluatorMail", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("evaluatorMail")
  public String getEvaluatorMail() {
    return evaluatorMail;
  }

  public void setEvaluatorMail(String evaluatorMail) {
    this.evaluatorMail = evaluatorMail;
  }

  public EvaluationDTO status(StatusEnum status) {
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

  public EvaluationDTO answeredQuestionDifficultyLevel(AnsweredQuestionDifficultyLevelEnum answeredQuestionDifficultyLevel) {
    this.answeredQuestionDifficultyLevel = answeredQuestionDifficultyLevel;
    return this;
  }

  /**
   * Get answeredQuestionDifficultyLevel
   * @return answeredQuestionDifficultyLevel
  */
  
  @Schema(name = "answeredQuestionDifficultyLevel", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("answeredQuestionDifficultyLevel")
  public AnsweredQuestionDifficultyLevelEnum getAnsweredQuestionDifficultyLevel() {
    return answeredQuestionDifficultyLevel;
  }

  public void setAnsweredQuestionDifficultyLevel(AnsweredQuestionDifficultyLevelEnum answeredQuestionDifficultyLevel) {
    this.answeredQuestionDifficultyLevel = answeredQuestionDifficultyLevel;
  }

  public EvaluationDTO tag(EvaluationTagDTO tag) {
    this.tag = tag;
    return this;
  }

  /**
   * Get tag
   * @return tag
  */
  @Valid 
  @Schema(name = "tag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tag")
  public EvaluationTagDTO getTag() {
    return tag;
  }

  public void setTag(EvaluationTagDTO tag) {
    this.tag = tag;
  }

  public EvaluationDTO question(EvaluationQuestionDTO question) {
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
  public EvaluationQuestionDTO getQuestion() {
    return question;
  }

  public void setQuestion(EvaluationQuestionDTO question) {
    this.question = question;
  }

  public EvaluationDTO user(AnsweringUserDTO user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  */
  @Valid 
  @Schema(name = "user", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("user")
  public AnsweringUserDTO getUser() {
    return user;
  }

  public void setUser(AnsweringUserDTO user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EvaluationDTO evaluationDTO = (EvaluationDTO) o;
    return Objects.equals(this.id, evaluationDTO.id) &&
        Objects.equals(this.score, evaluationDTO.score) &&
        Objects.equals(this.evaluatorMail, evaluationDTO.evaluatorMail) &&
        Objects.equals(this.status, evaluationDTO.status) &&
        Objects.equals(this.answeredQuestionDifficultyLevel, evaluationDTO.answeredQuestionDifficultyLevel) &&
        Objects.equals(this.tag, evaluationDTO.tag) &&
        Objects.equals(this.question, evaluationDTO.question) &&
        Objects.equals(this.user, evaluationDTO.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, score, evaluatorMail, status, answeredQuestionDifficultyLevel, tag, question, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EvaluationDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
    sb.append("    evaluatorMail: ").append(toIndentedString(evaluatorMail)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    answeredQuestionDifficultyLevel: ").append(toIndentedString(answeredQuestionDifficultyLevel)).append("\n");
    sb.append("    tag: ").append(toIndentedString(tag)).append("\n");
    sb.append("    question: ").append(toIndentedString(question)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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

