package org.contextmapper.generated.sendquestioncontext.client.questioncontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.model.QuestionResourceTagInfosDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * QuestionResourceDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-25T13:29:24.478064+02:00[Europe/Paris]")
public class QuestionResourceDTO {

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

  private QuestionResourceTagInfosDTO tagId;

  public QuestionResourceDTO id(Long id) {
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

  public QuestionResourceDTO questionContent(String questionContent) {
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

  public QuestionResourceDTO questionState(QuestionStateEnum questionState) {
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

  public QuestionResourceDTO resourceType(ResourceTypeEnum resourceType) {
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

  public QuestionResourceDTO tagId(QuestionResourceTagInfosDTO tagId) {
    this.tagId = tagId;
    return this;
  }

  /**
   * Get tagId
   * @return tagId
  */
  @Valid 
  @Schema(name = "tagId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tagId")
  public QuestionResourceTagInfosDTO getTagId() {
    return tagId;
  }

  public void setTagId(QuestionResourceTagInfosDTO tagId) {
    this.tagId = tagId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuestionResourceDTO questionResourceDTO = (QuestionResourceDTO) o;
    return Objects.equals(this.id, questionResourceDTO.id) &&
        Objects.equals(this.questionContent, questionResourceDTO.questionContent) &&
        Objects.equals(this.questionState, questionResourceDTO.questionState) &&
        Objects.equals(this.resourceType, questionResourceDTO.resourceType) &&
        Objects.equals(this.tagId, questionResourceDTO.tagId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionContent, questionState, resourceType, tagId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuestionResourceDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    questionContent: ").append(toIndentedString(questionContent)).append("\n");
    sb.append("    questionState: ").append(toIndentedString(questionState)).append("\n");
    sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
    sb.append("    tagId: ").append(toIndentedString(tagId)).append("\n");
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

