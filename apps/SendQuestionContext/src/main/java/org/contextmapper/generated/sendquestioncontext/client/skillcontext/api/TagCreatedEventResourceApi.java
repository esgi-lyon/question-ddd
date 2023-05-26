/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.sendquestioncontext.client.skillcontext.api;

import org.contextmapper.generated.sendquestioncontext.client.skillcontext.model.TagCreatedEventDTO;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-26T09:28:33.252958+02:00[Europe/Paris]")
@Validated
@Tag(name = "tag-created-event-resource", description = "the tag-created-event-resource API")
public interface TagCreatedEventResourceApi {

    /**
     * GET /api/tag-created-events
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAllTagCreatedEvents",
        tags = { "tag-created-event-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", array = @ArraySchema(schema = @Schema(implementation = TagCreatedEventDTO.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/tag-created-events",
        produces = "*/*"
    )
    ResponseEntity<List<TagCreatedEventDTO>> getAllTagCreatedEvents(
        
    );


    /**
     * GET /api/tag-created-events/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getTagCreatedEvent",
        tags = { "tag-created-event-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = TagCreatedEventDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/tag-created-events/{id}",
        produces = "*/*"
    )
    ResponseEntity<TagCreatedEventDTO> getTagCreatedEvent(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );

}
