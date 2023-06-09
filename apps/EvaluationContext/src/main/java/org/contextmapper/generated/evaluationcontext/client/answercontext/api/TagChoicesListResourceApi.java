/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.evaluationcontext.client.answercontext.api;

import org.contextmapper.generated.evaluationcontext.client.answercontext.model.TagChoicesListDTO;
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
@Tag(name = "tag-choices-list-resource", description = "the tag-choices-list-resource API")
public interface TagChoicesListResourceApi {

    /**
     * POST /api/tag-choices-lists
     *
     * @param tagChoicesListDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "createTagChoicesList",
        tags = { "tag-choices-list-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = TagChoicesListDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/tag-choices-lists",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<TagChoicesListDTO> createTagChoicesList(
        @Parameter(name = "TagChoicesListDTO", description = "", required = true) @Valid @RequestBody TagChoicesListDTO tagChoicesListDTO
    );


    /**
     * DELETE /api/tag-choices-lists/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "deleteTagChoicesList",
        tags = { "tag-choices-list-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/tag-choices-lists/{id}"
    )
    ResponseEntity<Void> deleteTagChoicesList(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * GET /api/tag-choices-lists
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAllTagChoicesLists",
        tags = { "tag-choices-list-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", array = @ArraySchema(schema = @Schema(implementation = TagChoicesListDTO.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/tag-choices-lists",
        produces = "*/*"
    )
    ResponseEntity<List<TagChoicesListDTO>> getAllTagChoicesLists(
        
    );


    /**
     * GET /api/tag-choices-lists/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getTagChoicesList",
        tags = { "tag-choices-list-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = TagChoicesListDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/tag-choices-lists/{id}",
        produces = "*/*"
    )
    ResponseEntity<TagChoicesListDTO> getTagChoicesList(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * PATCH /api/tag-choices-lists/{id}
     *
     * @param id  (required)
     * @param tagChoicesListDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "partialUpdateTagChoicesList",
        tags = { "tag-choices-list-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = TagChoicesListDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/api/tag-choices-lists/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<TagChoicesListDTO> partialUpdateTagChoicesList(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "TagChoicesListDTO", description = "", required = true) @Valid @RequestBody TagChoicesListDTO tagChoicesListDTO
    );


    /**
     * PUT /api/tag-choices-lists/{id}
     *
     * @param id  (required)
     * @param tagChoicesListDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "updateTagChoicesList",
        tags = { "tag-choices-list-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = TagChoicesListDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/tag-choices-lists/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<TagChoicesListDTO> updateTagChoicesList(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "TagChoicesListDTO", description = "", required = true) @Valid @RequestBody TagChoicesListDTO tagChoicesListDTO
    );

}
