/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.statcontext.client.evaluationcontext.api;

import org.contextmapper.generated.statcontext.client.evaluationcontext.model.EvaluationDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:14:28.995550+02:00[Europe/Paris]")
@Validated
@Tag(name = "evaluation-resource", description = "the evaluation-resource API")
public interface EvaluationResourceApi {

    /**
     * POST /api/evaluations
     *
     * @param evaluationDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "createEvaluation",
        tags = { "evaluation-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = EvaluationDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/evaluations",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<EvaluationDTO> createEvaluation(
        @Parameter(name = "EvaluationDTO", description = "", required = true) @Valid @RequestBody EvaluationDTO evaluationDTO
    );


    /**
     * DELETE /api/evaluations/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "deleteEvaluation",
        tags = { "evaluation-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/evaluations/{id}"
    )
    ResponseEntity<Void> deleteEvaluation(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * GET /api/evaluations
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAllEvaluations",
        tags = { "evaluation-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", array = @ArraySchema(schema = @Schema(implementation = EvaluationDTO.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/evaluations",
        produces = "*/*"
    )
    ResponseEntity<List<EvaluationDTO>> getAllEvaluations(
        
    );


    /**
     * GET /api/evaluations/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getEvaluation",
        tags = { "evaluation-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = EvaluationDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/evaluations/{id}",
        produces = "*/*"
    )
    ResponseEntity<EvaluationDTO> getEvaluation(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * PATCH /api/evaluations/{id}
     *
     * @param id  (required)
     * @param evaluationDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "partialUpdateEvaluation",
        tags = { "evaluation-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = EvaluationDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/api/evaluations/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<EvaluationDTO> partialUpdateEvaluation(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "EvaluationDTO", description = "", required = true) @Valid @RequestBody EvaluationDTO evaluationDTO
    );


    /**
     * PUT /api/evaluations/{id}
     *
     * @param id  (required)
     * @param evaluationDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "updateEvaluation",
        tags = { "evaluation-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = EvaluationDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/evaluations/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<EvaluationDTO> updateEvaluation(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "EvaluationDTO", description = "", required = true) @Valid @RequestBody EvaluationDTO evaluationDTO
    );

}
