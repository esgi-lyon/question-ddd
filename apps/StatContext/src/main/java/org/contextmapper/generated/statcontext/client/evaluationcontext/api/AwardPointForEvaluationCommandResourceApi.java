/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.statcontext.client.evaluationcontext.api;

import org.contextmapper.generated.statcontext.client.evaluationcontext.model.AwardPointForEvaluationCommandDTO;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T11:22:06.881122+02:00[Europe/Paris]")
@Validated
@Tag(name = "award-point-for-evaluation-command-resource", description = "the award-point-for-evaluation-command-resource API")
public interface AwardPointForEvaluationCommandResourceApi {

    /**
     * POST /api/award-point-for-evaluation-commands
     *
     * @param awardPointForEvaluationCommandDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "createAwardPointForEvaluationCommand",
        tags = { "award-point-for-evaluation-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AwardPointForEvaluationCommandDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/award-point-for-evaluation-commands",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<AwardPointForEvaluationCommandDTO> createAwardPointForEvaluationCommand(
        @Parameter(name = "AwardPointForEvaluationCommandDTO", description = "", required = true) @Valid @RequestBody AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO
    );


    /**
     * DELETE /api/award-point-for-evaluation-commands/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "deleteAwardPointForEvaluationCommand",
        tags = { "award-point-for-evaluation-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/award-point-for-evaluation-commands/{id}"
    )
    ResponseEntity<Void> deleteAwardPointForEvaluationCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * GET /api/award-point-for-evaluation-commands
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAllAwardPointForEvaluationCommands",
        tags = { "award-point-for-evaluation-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", array = @ArraySchema(schema = @Schema(implementation = AwardPointForEvaluationCommandDTO.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/award-point-for-evaluation-commands",
        produces = "*/*"
    )
    ResponseEntity<List<AwardPointForEvaluationCommandDTO>> getAllAwardPointForEvaluationCommands(
        
    );


    /**
     * GET /api/award-point-for-evaluation-commands/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAwardPointForEvaluationCommand",
        tags = { "award-point-for-evaluation-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AwardPointForEvaluationCommandDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/award-point-for-evaluation-commands/{id}",
        produces = "*/*"
    )
    ResponseEntity<AwardPointForEvaluationCommandDTO> getAwardPointForEvaluationCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * PATCH /api/award-point-for-evaluation-commands/{id}
     *
     * @param id  (required)
     * @param awardPointForEvaluationCommandDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "partialUpdateAwardPointForEvaluationCommand",
        tags = { "award-point-for-evaluation-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AwardPointForEvaluationCommandDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/api/award-point-for-evaluation-commands/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<AwardPointForEvaluationCommandDTO> partialUpdateAwardPointForEvaluationCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "AwardPointForEvaluationCommandDTO", description = "", required = true) @Valid @RequestBody AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO
    );


    /**
     * PUT /api/award-point-for-evaluation-commands/{id}
     *
     * @param id  (required)
     * @param awardPointForEvaluationCommandDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "updateAwardPointForEvaluationCommand",
        tags = { "award-point-for-evaluation-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AwardPointForEvaluationCommandDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/award-point-for-evaluation-commands/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<AwardPointForEvaluationCommandDTO> updateAwardPointForEvaluationCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "AwardPointForEvaluationCommandDTO", description = "", required = true) @Valid @RequestBody AwardPointForEvaluationCommandDTO awardPointForEvaluationCommandDTO
    );

}