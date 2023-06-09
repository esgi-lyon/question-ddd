/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.statcontext.client.evaluationcontext.api;

import org.contextmapper.generated.statcontext.client.evaluationcontext.model.NotifyNewAnswerCommandDTO;
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
@Tag(name = "notify-new-answer-command-resource", description = "the notify-new-answer-command-resource API")
public interface NotifyNewAnswerCommandResourceApi {

    /**
     * POST /api/notify-new-answer-commands
     *
     * @param notifyNewAnswerCommandDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "createNotifyNewAnswerCommand",
        tags = { "notify-new-answer-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = NotifyNewAnswerCommandDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/notify-new-answer-commands",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<NotifyNewAnswerCommandDTO> createNotifyNewAnswerCommand(
        @Parameter(name = "NotifyNewAnswerCommandDTO", description = "", required = true) @Valid @RequestBody NotifyNewAnswerCommandDTO notifyNewAnswerCommandDTO
    );


    /**
     * DELETE /api/notify-new-answer-commands/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "deleteNotifyNewAnswerCommand",
        tags = { "notify-new-answer-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/notify-new-answer-commands/{id}"
    )
    ResponseEntity<Void> deleteNotifyNewAnswerCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * GET /api/notify-new-answer-commands
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAllNotifyNewAnswerCommands",
        tags = { "notify-new-answer-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", array = @ArraySchema(schema = @Schema(implementation = NotifyNewAnswerCommandDTO.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/notify-new-answer-commands",
        produces = "*/*"
    )
    ResponseEntity<List<NotifyNewAnswerCommandDTO>> getAllNotifyNewAnswerCommands(
        
    );


    /**
     * GET /api/notify-new-answer-commands/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getNotifyNewAnswerCommand",
        tags = { "notify-new-answer-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = NotifyNewAnswerCommandDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/notify-new-answer-commands/{id}",
        produces = "*/*"
    )
    ResponseEntity<NotifyNewAnswerCommandDTO> getNotifyNewAnswerCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * PATCH /api/notify-new-answer-commands/{id}
     *
     * @param id  (required)
     * @param notifyNewAnswerCommandDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "partialUpdateNotifyNewAnswerCommand",
        tags = { "notify-new-answer-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = NotifyNewAnswerCommandDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/api/notify-new-answer-commands/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<NotifyNewAnswerCommandDTO> partialUpdateNotifyNewAnswerCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "NotifyNewAnswerCommandDTO", description = "", required = true) @Valid @RequestBody NotifyNewAnswerCommandDTO notifyNewAnswerCommandDTO
    );


    /**
     * PUT /api/notify-new-answer-commands/{id}
     *
     * @param id  (required)
     * @param notifyNewAnswerCommandDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "updateNotifyNewAnswerCommand",
        tags = { "notify-new-answer-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = NotifyNewAnswerCommandDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/notify-new-answer-commands/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<NotifyNewAnswerCommandDTO> updateNotifyNewAnswerCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "NotifyNewAnswerCommandDTO", description = "", required = true) @Valid @RequestBody NotifyNewAnswerCommandDTO notifyNewAnswerCommandDTO
    );

}
