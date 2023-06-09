package org.contextmapper.generated.sendquestioncontext.client.questioncontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.model.QuestionResourceDTO;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.model.QuestionResourceTagInfosDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ResourceAcceptedAssociationEventDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:12:21.347184+02:00[Europe/Paris]")
public class ResourceAcceptedAssociationEventDTO {

  private Long id;

  private QuestionResourceDTO questionId;

  private QuestionResourceTagInfosDTO tagInfos;

  public ResourceAcceptedAssociationEventDTO id(Long id) {
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

  public ResourceAcceptedAssociationEventDTO questionId(QuestionResourceDTO questionId) {
    this.questionId = questionId;
    return this;
  }

  /**
   * Get questionId
   * @return questionId
  */
  @Valid 
  @Schema(name = "questionId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("questionId")
  public QuestionResourceDTO getQuestionId() {
    return questionId;
  }

  public void setQuestionId(QuestionResourceDTO questionId) {
    this.questionId = questionId;
  }

  public ResourceAcceptedAssociationEventDTO tagInfos(QuestionResourceTagInfosDTO tagInfos) {
    this.tagInfos = tagInfos;
    return this;
  }

  /**
   * Get tagInfos
   * @return tagInfos
  */
  @Valid 
  @Schema(name = "tagInfos", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tagInfos")
  public QuestionResourceTagInfosDTO getTagInfos() {
    return tagInfos;
  }

  public void setTagInfos(QuestionResourceTagInfosDTO tagInfos) {
    this.tagInfos = tagInfos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResourceAcceptedAssociationEventDTO resourceAcceptedAssociationEventDTO = (ResourceAcceptedAssociationEventDTO) o;
    return Objects.equals(this.id, resourceAcceptedAssociationEventDTO.id) &&
        Objects.equals(this.questionId, resourceAcceptedAssociationEventDTO.questionId) &&
        Objects.equals(this.tagInfos, resourceAcceptedAssociationEventDTO.tagInfos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, questionId, tagInfos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceAcceptedAssociationEventDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    questionId: ").append(toIndentedString(questionId)).append("\n");
    sb.append("    tagInfos: ").append(toIndentedString(tagInfos)).append("\n");
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

