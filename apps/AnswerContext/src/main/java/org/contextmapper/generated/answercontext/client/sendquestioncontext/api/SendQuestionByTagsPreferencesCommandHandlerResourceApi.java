/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.QuestionSentDTO;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.model.SendQuestionByTagsPreferencesCommand;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-25T18:07:12.588883+02:00[Europe/Paris]")
@Validated
@Tag(name = "send-question-by-tags-preferences-command-handler-resource", description = "the send-question-by-tags-preferences-command-handler-resource API")
public interface SendQuestionByTagsPreferencesCommandHandlerResourceApi {

    /**
     * POST /api/send-question-by-preferences-command
     *
     * @param questionSentDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "handleSendQuestionByTagsPreferencesCommand",
        tags = { "send-question-by-tags-preferences-command-handler-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = SendQuestionByTagsPreferencesCommand.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/send-question-by-preferences-command",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<SendQuestionByTagsPreferencesCommand> handleSendQuestionByTagsPreferencesCommand(
        @Parameter(name = "QuestionSentDTO", description = "", required = true) @Valid @RequestBody QuestionSentDTO questionSentDTO
    );

}
