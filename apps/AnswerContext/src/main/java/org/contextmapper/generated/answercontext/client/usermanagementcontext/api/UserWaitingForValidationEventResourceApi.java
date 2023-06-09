/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.answercontext.client.usermanagementcontext.api;

import org.contextmapper.generated.answercontext.client.usermanagementcontext.model.UserWaitingForValidationEventDTO;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:11:57.159370+02:00[Europe/Paris]")
@Validated
@Tag(name = "user-waiting-for-validation-event-resource", description = "the user-waiting-for-validation-event-resource API")
public interface UserWaitingForValidationEventResourceApi {

    /**
     * GET /api/user-waiting-for-validation-events
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAllUserWaitingForValidationEvents",
        tags = { "user-waiting-for-validation-event-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", array = @ArraySchema(schema = @Schema(implementation = UserWaitingForValidationEventDTO.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/user-waiting-for-validation-events",
        produces = "*/*"
    )
    ResponseEntity<List<UserWaitingForValidationEventDTO>> getAllUserWaitingForValidationEvents(
        
    );


    /**
     * GET /api/user-waiting-for-validation-events/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getUserWaitingForValidationEvent",
        tags = { "user-waiting-for-validation-event-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = UserWaitingForValidationEventDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/user-waiting-for-validation-events/{id}",
        produces = "*/*"
    )
    ResponseEntity<UserWaitingForValidationEventDTO> getUserWaitingForValidationEvent(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );

}
