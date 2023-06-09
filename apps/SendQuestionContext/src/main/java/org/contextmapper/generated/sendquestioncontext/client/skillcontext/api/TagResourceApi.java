/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.sendquestioncontext.client.skillcontext.api;

import org.contextmapper.generated.sendquestioncontext.client.skillcontext.model.TagDTO;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-30T12:12:46.072606+02:00[Europe/Paris]")
@Validated
@Tag(name = "tag-resource", description = "the tag-resource API")
public interface TagResourceApi {

    /**
     * POST /api/tags
     *
     * @param tagDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "createTag",
        tags = { "tag-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = TagDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/tags",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<TagDTO> createTag(
        @Parameter(name = "TagDTO", description = "", required = true) @Valid @RequestBody TagDTO tagDTO
    );


    /**
     * DELETE /api/tags/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "deleteTag",
        tags = { "tag-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/tags/{id}"
    )
    ResponseEntity<Void> deleteTag(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * GET /api/tags
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAllTags",
        tags = { "tag-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", array = @ArraySchema(schema = @Schema(implementation = TagDTO.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/tags",
        produces = "*/*"
    )
    ResponseEntity<List<TagDTO>> getAllTags(
        
    );


    /**
     * GET /api/tags/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getTag",
        tags = { "tag-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = TagDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/tags/{id}",
        produces = "*/*"
    )
    ResponseEntity<TagDTO> getTag(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * PATCH /api/tags/{id}
     *
     * @param id  (required)
     * @param tagDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "partialUpdateTag",
        tags = { "tag-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = TagDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/api/tags/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<TagDTO> partialUpdateTag(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "TagDTO", description = "", required = true) @Valid @RequestBody TagDTO tagDTO
    );


    /**
     * PUT /api/tags/{id}
     *
     * @param id  (required)
     * @param tagDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "updateTag",
        tags = { "tag-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = TagDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/tags/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<TagDTO> updateTag(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "TagDTO", description = "", required = true) @Valid @RequestBody TagDTO tagDTO
    );

}
