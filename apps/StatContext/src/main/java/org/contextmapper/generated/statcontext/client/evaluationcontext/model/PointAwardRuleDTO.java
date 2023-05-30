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
 * PointAwardRuleDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:14:28.995550+02:00[Europe/Paris]")
public class PointAwardRuleDTO {

  private Long id;

  private Integer scoreEvolution;

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

  public PointAwardRuleDTO id(Long id) {
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

  public PointAwardRuleDTO scoreEvolution(Integer scoreEvolution) {
    this.scoreEvolution = scoreEvolution;
    return this;
  }

  /**
   * Get scoreEvolution
   * @return scoreEvolution
  */
  
  @Schema(name = "scoreEvolution", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scoreEvolution")
  public Integer getScoreEvolution() {
    return scoreEvolution;
  }

  public void setScoreEvolution(Integer scoreEvolution) {
    this.scoreEvolution = scoreEvolution;
  }

  public PointAwardRuleDTO difficultyLevel(DifficultyLevelEnum difficultyLevel) {
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

  public PointAwardRuleDTO userLevel(UserLevelEnum userLevel) {
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
    PointAwardRuleDTO pointAwardRuleDTO = (PointAwardRuleDTO) o;
    return Objects.equals(this.id, pointAwardRuleDTO.id) &&
        Objects.equals(this.scoreEvolution, pointAwardRuleDTO.scoreEvolution) &&
        Objects.equals(this.difficultyLevel, pointAwardRuleDTO.difficultyLevel) &&
        Objects.equals(this.userLevel, pointAwardRuleDTO.userLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, scoreEvolution, difficultyLevel, userLevel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PointAwardRuleDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    scoreEvolution: ").append(toIndentedString(scoreEvolution)).append("\n");
    sb.append("    difficultyLevel: ").append(toIndentedString(difficultyLevel)).append("\n");
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

