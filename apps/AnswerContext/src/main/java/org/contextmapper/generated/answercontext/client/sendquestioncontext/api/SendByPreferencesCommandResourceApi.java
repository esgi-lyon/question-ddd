/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.SendByPreferencesCommand;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-26T09:39:32.961922+02:00[Europe/Paris]")
@Validated
@Tag(name = "send-by-preferences-command-resource", description = "the send-by-preferences-command-resource API")
public interface SendByPreferencesCommandResourceApi {

    /**
     * POST /api/send-by-preferences-commands
     *
     * @param sendByPreferencesCommand  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "createSendByPreferencesCommand",
        tags = { "send-by-preferences-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = SendByPreferencesCommand.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/send-by-preferences-commands",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<SendByPreferencesCommand> createSendByPreferencesCommand(
        @Parameter(name = "SendByPreferencesCommand", description = "", required = true) @Valid @RequestBody SendByPreferencesCommand sendByPreferencesCommand
    );


    /**
     * DELETE /api/send-by-preferences-commands/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "deleteSendByPreferencesCommand",
        tags = { "send-by-preferences-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/send-by-preferences-commands/{id}"
    )
    ResponseEntity<Void> deleteSendByPreferencesCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * GET /api/send-by-preferences-commands
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAllSendByPreferencesCommands",
        tags = { "send-by-preferences-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", array = @ArraySchema(schema = @Schema(implementation = SendByPreferencesCommand.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/send-by-preferences-commands",
        produces = "*/*"
    )
    ResponseEntity<List<SendByPreferencesCommand>> getAllSendByPreferencesCommands(
        
    );


    /**
     * GET /api/send-by-preferences-commands/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getSendByPreferencesCommand",
        tags = { "send-by-preferences-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = SendByPreferencesCommand.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/send-by-preferences-commands/{id}",
        produces = "*/*"
    )
    ResponseEntity<SendByPreferencesCommand> getSendByPreferencesCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * PATCH /api/send-by-preferences-commands/{id}
     *
     * @param id  (required)
     * @param sendByPreferencesCommand  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "partialUpdateSendByPreferencesCommand",
        tags = { "send-by-preferences-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = SendByPreferencesCommand.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/api/send-by-preferences-commands/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<SendByPreferencesCommand> partialUpdateSendByPreferencesCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "SendByPreferencesCommand", description = "", required = true) @Valid @RequestBody SendByPreferencesCommand sendByPreferencesCommand
    );


    /**
     * PUT /api/send-by-preferences-commands/{id}
     *
     * @param id  (required)
     * @param sendByPreferencesCommand  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "updateSendByPreferencesCommand",
        tags = { "send-by-preferences-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = SendByPreferencesCommand.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/send-by-preferences-commands/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<SendByPreferencesCommand> updateSendByPreferencesCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "SendByPreferencesCommand", description = "", required = true) @Valid @RequestBody SendByPreferencesCommand sendByPreferencesCommand
    );

}