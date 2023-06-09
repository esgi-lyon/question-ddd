/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.statcontext.client.evaluationcontext.api;

import org.contextmapper.generated.statcontext.client.evaluationcontext.model.AwardPointForEvaluationDTO;
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
@Tag(name = "award-point-for-evaluation-resource", description = "the award-point-for-evaluation-resource API")
public interface AwardPointForEvaluationResourceApi {

    /**
     * POST /api/award-point-for-evaluations
     *
     * @param awardPointForEvaluationDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "createAwardPointForEvaluation",
        tags = { "award-point-for-evaluation-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AwardPointForEvaluationDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/award-point-for-evaluations",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<AwardPointForEvaluationDTO> createAwardPointForEvaluation(
        @Parameter(name = "AwardPointForEvaluationDTO", description = "", required = true) @Valid @RequestBody AwardPointForEvaluationDTO awardPointForEvaluationDTO
    );


    /**
     * DELETE /api/award-point-for-evaluations/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "deleteAwardPointForEvaluation",
        tags = { "award-point-for-evaluation-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/award-point-for-evaluations/{id}"
    )
    ResponseEntity<Void> deleteAwardPointForEvaluation(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * GET /api/award-point-for-evaluations
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAllAwardPointForEvaluations",
        tags = { "award-point-for-evaluation-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", array = @ArraySchema(schema = @Schema(implementation = AwardPointForEvaluationDTO.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/award-point-for-evaluations",
        produces = "*/*"
    )
    ResponseEntity<List<AwardPointForEvaluationDTO>> getAllAwardPointForEvaluations(
        
    );


    /**
     * GET /api/award-point-for-evaluations/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAwardPointForEvaluation",
        tags = { "award-point-for-evaluation-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AwardPointForEvaluationDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/award-point-for-evaluations/{id}",
        produces = "*/*"
    )
    ResponseEntity<AwardPointForEvaluationDTO> getAwardPointForEvaluation(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * PATCH /api/award-point-for-evaluations/{id}
     *
     * @param id  (required)
     * @param awardPointForEvaluationDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "partialUpdateAwardPointForEvaluation",
        tags = { "award-point-for-evaluation-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AwardPointForEvaluationDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/api/award-point-for-evaluations/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<AwardPointForEvaluationDTO> partialUpdateAwardPointForEvaluation(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "AwardPointForEvaluationDTO", description = "", required = true) @Valid @RequestBody AwardPointForEvaluationDTO awardPointForEvaluationDTO
    );


    /**
     * PUT /api/award-point-for-evaluations/{id}
     *
     * @param id  (required)
     * @param awardPointForEvaluationDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "updateAwardPointForEvaluation",
        tags = { "award-point-for-evaluation-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AwardPointForEvaluationDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/award-point-for-evaluations/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<AwardPointForEvaluationDTO> updateAwardPointForEvaluation(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "AwardPointForEvaluationDTO", description = "", required = true) @Valid @RequestBody AwardPointForEvaluationDTO awardPointForEvaluationDTO
    );

}
