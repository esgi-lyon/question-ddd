package org.contextmapper.generated.statcontext.client.evaluationcontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.contextmapper.generated.statcontext.client.evaluationcontext.model.EvaluationDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * AwardPointForEvaluationDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:14:28.995550+02:00[Europe/Paris]")
public class AwardPointForEvaluationDTO {

  private Long id;

  private EvaluationDTO evaluation;

  public AwardPointForEvaluationDTO id(Long id) {
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

  public AwardPointForEvaluationDTO evaluation(EvaluationDTO evaluation) {
    this.evaluation = evaluation;
    return this;
  }

  /**
   * Get evaluation
   * @return evaluation
  */
  @Valid 
  @Schema(name = "evaluation", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("evaluation")
  public EvaluationDTO getEvaluation() {
    return evaluation;
  }

  public void setEvaluation(EvaluationDTO evaluation) {
    this.evaluation = evaluation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AwardPointForEvaluationDTO awardPointForEvaluationDTO = (AwardPointForEvaluationDTO) o;
    return Objects.equals(this.id, awardPointForEvaluationDTO.id) &&
        Objects.equals(this.evaluation, awardPointForEvaluationDTO.evaluation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, evaluation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AwardPointForEvaluationDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    evaluation: ").append(toIndentedString(evaluation)).append("\n");
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

