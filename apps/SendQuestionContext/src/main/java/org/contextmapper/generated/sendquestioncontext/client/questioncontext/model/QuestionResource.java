package org.contextmapper.generated.sendquestioncontext.client.questioncontext.model;

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
 * QuestionResource
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-27T13:17:37.306479+02:00[Europe/Paris]")
public class QuestionResource {

  private Long id;

  private String questionContent;

  /**
   * Gets or Sets questionState
   */
  public enum QuestionStateEnum {
    WAITING("WAITING"),
    
    ASSOCIATED("ASSOCIATED"),
    
    REFUSED("REFUSED");

    private String value;

    QuestionStateEnum(String value) {
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
    public static QuestionStateEnum fromValue(String value) {
      for (QuestionStateEnum b : QuestionStateEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private QuestionStateEnum questionState;

  /**
   * Gets or Sets resourceType
   */
  public enum ResourceTypeEnum {
    IMG_URL("IMG_URL"),
    
    URL("URL"),
    
    TEXT("TEXT");

    private String value;

    ResourceTypeEnum(String value) {
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
    public static ResourceTypeEnum fromValue(String value) {
      for (ResourceTypeEnum b : ResourceTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private ResourceTypeEnum resourceType;

  public QuestionResource id(Long id) {
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

  public QuestionResource questionContent(String questionContent) {
    this.questionContent = questionContent;
    return this;
  }

  /**
   * Get questionContent
   * @return questionContent
  */
  
  @Schema(name = "questionContent", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("questionContent")
  public String getQuestionContent() {
    return questionContent;
  }

  public void setQuestionContent(String questionContent) {
    this.questionContent = questionContent;
  }

  public QuestionResource questionState(QuestionStateEnum questionState) {
    this.questionState = questionState;
    return this;
  }

  /**
   * Get questionState
   * @return questionState
  */
  
  @Schema(name = "questionState", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("questionState")
  public QuestionStateEnum getQuestionState() {
    return questionState;
  }

  public void setQuestionState(QuestionStateEnum questionState) {
    this.questionState = questionState;
  }

  public QuestionResource resourceType(ResourceTypeEnum resourceType) {
    this.resourceType = resourceType;
    return this;
  }

  /**
   * Get resourceType
   * @return resourceType
  */
  
  @Schema(name = "resourceType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("resourceType")
  public ResourceTypeEnum getResourceType() {
    return resourceType;
  }

  public void setResourceType(ResourceTypeEnum resourceType) {
    this.resourceType = resourceType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuestionResource questionResource = (QuestionResource) o;
    return Objects.equals(this.id, questionResource.id) &&
        Objects.equals(this.questionContent, questionResource.questionContent) &&
        Objects.equals(this.questionState, questionResource.questionState) &&
        Objects.equals(this.resourceType, questionResource.resourceType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionContent, questionState, resourceType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuestionResource {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    questionContent: ").append(toIndentedString(questionContent)).append("\n");
    sb.append("    questionState: ").append(toIndentedString(questionState)).append("\n");
    sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
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

