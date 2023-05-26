/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.evaluationcontext.client.answercontext.api;

import org.contextmapper.generated.evaluationcontext.client.answercontext.model.AnswerSubmitCommand;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-26T09:42:18.133357+02:00[Europe/Paris]")
@Validated
@Tag(name = "answer-submit-command-resource", description = "the answer-submit-command-resource API")
public interface AnswerSubmitCommandResourceApi {

    /**
     * POST /api/answer-submit-commands
     *
     * @param answerSubmitCommand  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "createAnswerSubmitCommand",
        tags = { "answer-submit-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AnswerSubmitCommand.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/answer-submit-commands",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<AnswerSubmitCommand> createAnswerSubmitCommand(
        @Parameter(name = "AnswerSubmitCommand", description = "", required = true) @Valid @RequestBody AnswerSubmitCommand answerSubmitCommand
    );


    /**
     * DELETE /api/answer-submit-commands/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "deleteAnswerSubmitCommand",
        tags = { "answer-submit-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/answer-submit-commands/{id}"
    )
    ResponseEntity<Void> deleteAnswerSubmitCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * GET /api/answer-submit-commands
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAllAnswerSubmitCommands",
        tags = { "answer-submit-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", array = @ArraySchema(schema = @Schema(implementation = AnswerSubmitCommand.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/answer-submit-commands",
        produces = "*/*"
    )
    ResponseEntity<List<AnswerSubmitCommand>> getAllAnswerSubmitCommands(
        
    );


    /**
     * GET /api/answer-submit-commands/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAnswerSubmitCommand",
        tags = { "answer-submit-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AnswerSubmitCommand.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/answer-submit-commands/{id}",
        produces = "*/*"
    )
    ResponseEntity<AnswerSubmitCommand> getAnswerSubmitCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * PATCH /api/answer-submit-commands/{id}
     *
     * @param id  (required)
     * @param answerSubmitCommand  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "partialUpdateAnswerSubmitCommand",
        tags = { "answer-submit-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AnswerSubmitCommand.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/api/answer-submit-commands/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<AnswerSubmitCommand> partialUpdateAnswerSubmitCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "AnswerSubmitCommand", description = "", required = true) @Valid @RequestBody AnswerSubmitCommand answerSubmitCommand
    );


    /**
     * PUT /api/answer-submit-commands/{id}
     *
     * @param id  (required)
     * @param answerSubmitCommand  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "updateAnswerSubmitCommand",
        tags = { "answer-submit-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = AnswerSubmitCommand.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/answer-submit-commands/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<AnswerSubmitCommand> updateAnswerSubmitCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "AnswerSubmitCommand", description = "", required = true) @Valid @RequestBody AnswerSubmitCommand answerSubmitCommand
    );

}