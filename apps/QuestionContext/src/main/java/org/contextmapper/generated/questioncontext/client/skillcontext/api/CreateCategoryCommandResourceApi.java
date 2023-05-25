/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.questioncontext.client.skillcontext.api;

import org.contextmapper.generated.questioncontext.client.skillcontext.model.CreateCategoryCommand;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-25T16:01:44.699196+02:00[Europe/Paris]")
@Validated
@Tag(name = "create-category-command-resource", description = "the create-category-command-resource API")
public interface CreateCategoryCommandResourceApi {

    /**
     * POST /api/create-category-commands
     *
     * @param createCategoryCommand  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "createCreateCategoryCommand1",
        tags = { "create-category-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = CreateCategoryCommand.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/create-category-commands",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<CreateCategoryCommand> createCreateCategoryCommand1(
        @Parameter(name = "CreateCategoryCommand", description = "", required = true) @Valid @RequestBody CreateCategoryCommand createCategoryCommand
    );


    /**
     * DELETE /api/create-category-commands/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "deleteCreateCategoryCommand",
        tags = { "create-category-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/create-category-commands/{id}"
    )
    ResponseEntity<Void> deleteCreateCategoryCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * GET /api/create-category-commands
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAllCreateCategoryCommands",
        tags = { "create-category-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", array = @ArraySchema(schema = @Schema(implementation = CreateCategoryCommand.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/create-category-commands",
        produces = "*/*"
    )
    ResponseEntity<List<CreateCategoryCommand>> getAllCreateCategoryCommands(
        
    );


    /**
     * GET /api/create-category-commands/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getCreateCategoryCommand",
        tags = { "create-category-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = CreateCategoryCommand.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/create-category-commands/{id}",
        produces = "*/*"
    )
    ResponseEntity<CreateCategoryCommand> getCreateCategoryCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * PATCH /api/create-category-commands/{id}
     *
     * @param id  (required)
     * @param createCategoryCommand  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "partialUpdateCreateCategoryCommand",
        tags = { "create-category-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = CreateCategoryCommand.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/api/create-category-commands/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<CreateCategoryCommand> partialUpdateCreateCategoryCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "CreateCategoryCommand", description = "", required = true) @Valid @RequestBody CreateCategoryCommand createCategoryCommand
    );


    /**
     * PUT /api/create-category-commands/{id}
     *
     * @param id  (required)
     * @param createCategoryCommand  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "updateCreateCategoryCommand",
        tags = { "create-category-command-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = CreateCategoryCommand.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/create-category-commands/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<CreateCategoryCommand> updateCreateCategoryCommand(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "CreateCategoryCommand", description = "", required = true) @Valid @RequestBody CreateCategoryCommand createCategoryCommand
    );

}
