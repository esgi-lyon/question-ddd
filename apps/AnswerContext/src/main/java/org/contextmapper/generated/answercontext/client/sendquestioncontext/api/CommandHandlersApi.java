/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.AddPreferencesCommandDTO;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.CreatedQuestionEventDTO;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.NotifiedUsersDTO;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.PreferencesAddedEventDTO;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.PrepareQuestionCommandDTO;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.SendByPreferencesCommandDTO;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:13:14.383620+02:00[Europe/Paris]")
@Validated
@Tag(name = "command-handlers", description = "the command-handlers API")
public interface CommandHandlersApi {

    /**
     * POST /api/handlers/add-preferences-command
     *
     * @param addPreferencesCommandDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "handleAddPreference",
        tags = { "command-handlers" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = PreferencesAddedEventDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/handlers/add-preferences-command",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<PreferencesAddedEventDTO> handleAddPreference(
        @Parameter(name = "AddPreferencesCommandDTO", description = "", required = true) @Valid @RequestBody AddPreferencesCommandDTO addPreferencesCommandDTO
    );


    /**
     * POST /api/handlers/prepare-question-command
     *
     * @param prepareQuestionCommandDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "handlePrepareQuestionsCommand",
        tags = { "command-handlers" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = CreatedQuestionEventDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/handlers/prepare-question-command",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<CreatedQuestionEventDTO> handlePrepareQuestionsCommand(
        @Parameter(name = "PrepareQuestionCommandDTO", description = "", required = true) @Valid @RequestBody PrepareQuestionCommandDTO prepareQuestionCommandDTO
    );


    /**
     * POST /api/handlers/send-question-by-preferences-command
     *
     * @param sendByPreferencesCommandDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "handleSendQuestionByTagsPreferencesCommand",
        tags = { "command-handlers" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = NotifiedUsersDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/handlers/send-question-by-preferences-command",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<NotifiedUsersDTO> handleSendQuestionByTagsPreferencesCommand(
        @Parameter(name = "SendByPreferencesCommandDTO", description = "", required = true) @Valid @RequestBody SendByPreferencesCommandDTO sendByPreferencesCommandDTO
    );

}
