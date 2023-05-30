package org.contextmapper.generated.evaluationcontext.client.answercontext.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.contextmapper.generated.evaluationcontext.client.answercontext.model.AnswerDTO;
import org.contextmapper.generated.evaluationcontext.client.answercontext.model.AvailableAnswerDTO;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * NewAnswerDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:13:50.158415+02:00[Europe/Paris]")
public class NewAnswerDto {

  private AnswerDTO answer;

  @Valid
  private List<@Valid AvailableAnswerDTO> availableAnswerDTOS;

  public NewAnswerDto answer(AnswerDTO answer) {
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
  public AnswerDTO getAnswer() {
    return answer;
  }

  public void setAnswer(AnswerDTO answer) {
    this.answer = answer;
  }

  public NewAnswerDto availableAnswerDTOS(List<@Valid AvailableAnswerDTO> availableAnswerDTOS) {
    this.availableAnswerDTOS = availableAnswerDTOS;
    return this;
  }

  public NewAnswerDto addAvailableAnswerDTOSItem(AvailableAnswerDTO availableAnswerDTOSItem) {
    if (this.availableAnswerDTOS == null) {
      this.availableAnswerDTOS = new ArrayList<>();
    }
    this.availableAnswerDTOS.add(availableAnswerDTOSItem);
    return this;
  }

  /**
   * Get availableAnswerDTOS
   * @return availableAnswerDTOS
  */
  @Valid 
  @Schema(name = "availableAnswerDTOS", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("availableAnswerDTOS")
  public List<@Valid AvailableAnswerDTO> getAvailableAnswerDTOS() {
    return availableAnswerDTOS;
  }

  public void setAvailableAnswerDTOS(List<@Valid AvailableAnswerDTO> availableAnswerDTOS) {
    this.availableAnswerDTOS = availableAnswerDTOS;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewAnswerDto newAnswerDto = (NewAnswerDto) o;
    return Objects.equals(this.answer, newAnswerDto.answer) &&
        Objects.equals(this.availableAnswerDTOS, newAnswerDto.availableAnswerDTOS);
  }

  @Override
  public int hashCode() {
    return Objects.hash(answer, availableAnswerDTOS);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewAnswerDto {\n");
    sb.append("    answer: ").append(toIndentedString(answer)).append("\n");
    sb.append("    availableAnswerDTOS: ").append(toIndentedString(availableAnswerDTOS)).append("\n");
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

