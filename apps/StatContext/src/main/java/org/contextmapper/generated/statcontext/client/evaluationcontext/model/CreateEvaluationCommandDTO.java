package org.contextmapper.generated.statcontext.client.evaluationcontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.contextmapper.generated.statcontext.client.evaluationcontext.model.EvaluatedAnswerDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * CreateEvaluationCommandDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T11:22:06.881122+02:00[Europe/Paris]")
public class CreateEvaluationCommandDTO {

  private Long id;

  /**
   * Gets or Sets difficultyLevel
   */
  public enum DifficultyLevelEnum {
    EASY("EASY"),
    
    MEDIUM("MEDIUM"),
    
    HARD("HARD");

    private String value;

    DifficultyLevelEnum(String value) {
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
    public static DifficultyLevelEnum fromValue(String value) {
      for (DifficultyLevelEnum b : DifficultyLevelEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private DifficultyLevelEnum difficultyLevel;

  private EvaluatedAnswerDTO answer;

  public CreateEvaluationCommandDTO id(Long id) {
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

  public CreateEvaluationCommandDTO difficultyLevel(DifficultyLevelEnum difficultyLevel) {
    this.difficultyLevel = difficultyLevel;
    return this;
  }

  /**
   * Get difficultyLevel
   * @return difficultyLevel
  */
  
  @Schema(name = "difficultyLevel", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("difficultyLevel")
  public DifficultyLevelEnum getDifficultyLevel() {
    return difficultyLevel;
  }

  public void setDifficultyLevel(DifficultyLevelEnum difficultyLevel) {
    this.difficultyLevel = difficultyLevel;
  }

  public CreateEvaluationCommandDTO answer(EvaluatedAnswerDTO answer) {
    this.answer = answer;
    return this;
  }

  /**
   * Get answer
   * @return answer
  */
  @Valid 
  @Schema(name = "answer", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("answer")
  public EvaluatedAnswerDTO getAnswer() {
    return answer;
  }

  public void setAnswer(EvaluatedAnswerDTO answer) {
    this.answer = answer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateEvaluationCommandDTO createEvaluationCommandDTO = (CreateEvaluationCommandDTO) o;
    return Objects.equals(this.id, createEvaluationCommandDTO.id) &&
        Objects.equals(this.difficultyLevel, createEvaluationCommandDTO.difficultyLevel) &&
        Objects.equals(this.answer, createEvaluationCommandDTO.answer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, difficultyLevel, answer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateEvaluationCommandDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    difficultyLevel: ").append(toIndentedString(difficultyLevel)).append("\n");
    sb.append("    answer: ").append(toIndentedString(answer)).append("\n");
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

