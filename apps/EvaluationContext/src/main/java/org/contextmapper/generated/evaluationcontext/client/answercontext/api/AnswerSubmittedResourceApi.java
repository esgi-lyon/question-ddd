/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.evaluationcontext.client.answercontext.api;

import org.contextmapper.generated.evaluationcontext.client.answercontext.model.AnswerSubmittedDTO;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:13:50.158415+02:00[Europe/Paris]")
@Validated
@Tag(name = "answer-submitted-resource", description = "the answer-submitted-resource API")
public interface AnswerSubmittedResourceApi {

    /**
     * POST /api/answer-submitteds
     *
     * @param answerSubmittedDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "createAnswerSubmitted",
        tags = { "answer-submitted-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AnswerSubmittedDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/answer-submitteds",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<AnswerSubmittedDTO> createAnswerSubmitted(
        @Parameter(name = "AnswerSubmittedDTO", description = "", required = true) @Valid @RequestBody AnswerSubmittedDTO answerSubmittedDTO
    );


    /**
     * DELETE /api/answer-submitteds/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "deleteAnswerSubmitted",
        tags = { "answer-submitted-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/answer-submitteds/{id}"
    )
    ResponseEntity<Void> deleteAnswerSubmitted(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * GET /api/answer-submitteds
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAllAnswerSubmitteds",
        tags = { "answer-submitted-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", array = @ArraySchema(schema = @Schema(implementation = AnswerSubmittedDTO.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/answer-submitteds",
        produces = "*/*"
    )
    ResponseEntity<List<AnswerSubmittedDTO>> getAllAnswerSubmitteds(
        
    );


    /**
     * GET /api/answer-submitteds/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAnswerSubmitted",
        tags = { "answer-submitted-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AnswerSubmittedDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/answer-submitteds/{id}",
        produces = "*/*"
    )
    ResponseEntity<AnswerSubmittedDTO> getAnswerSubmitted(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * PATCH /api/answer-submitteds/{id}
     *
     * @param id  (required)
     * @param answerSubmittedDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "partialUpdateAnswerSubmitted",
        tags = { "answer-submitted-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AnswerSubmittedDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/api/answer-submitteds/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<AnswerSubmittedDTO> partialUpdateAnswerSubmitted(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "AnswerSubmittedDTO", description = "", required = true) @Valid @RequestBody AnswerSubmittedDTO answerSubmittedDTO
    );


    /**
     * PUT /api/answer-submitteds/{id}
     *
     * @param id  (required)
     * @param answerSubmittedDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "updateAnswerSubmitted",
        tags = { "answer-submitted-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AnswerSubmittedDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/answer-submitteds/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<AnswerSubmittedDTO> updateAnswerSubmitted(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "AnswerSubmittedDTO", description = "", required = true) @Valid @RequestBody AnswerSubmittedDTO answerSubmittedDTO
    );

}
