/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.contextmapper.generated.questioncontext.client.skillcontext.api;

import org.contextmapper.generated.questioncontext.client.skillcontext.model.CategoryDTO;
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
@Tag(name = "category-resource", description = "the category-resource API")
public interface CategoryResourceApi {

    /**
     * POST /api/categories
     *
     * @param categoryDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "createCategory",
        tags = { "category-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = CategoryDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/categories",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<CategoryDTO> createCategory(
        @Parameter(name = "CategoryDTO", description = "", required = true) @Valid @RequestBody CategoryDTO categoryDTO
    );


    /**
     * DELETE /api/categories/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "deleteCategory",
        tags = { "category-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/api/categories/{id}"
    )
    ResponseEntity<Void> deleteCategory(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * GET /api/categories
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getAllCategories",
        tags = { "category-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/categories",
        produces = "*/*"
    )
    ResponseEntity<List<CategoryDTO>> getAllCategories(
        
    );


    /**
     * GET /api/categories/{id}
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getCategory",
        tags = { "category-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = CategoryDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/categories/{id}",
        produces = "*/*"
    )
    ResponseEntity<CategoryDTO> getCategory(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * PATCH /api/categories/{id}
     *
     * @param id  (required)
     * @param categoryDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "partialUpdateCategory",
        tags = { "category-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = CategoryDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/api/categories/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<CategoryDTO> partialUpdateCategory(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "CategoryDTO", description = "", required = true) @Valid @RequestBody CategoryDTO categoryDTO
    );


    /**
     * PUT /api/categories/{id}
     *
     * @param id  (required)
     * @param categoryDTO  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "updateCategory",
        tags = { "category-resource" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "*/*", schema = @Schema(implementation = CategoryDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/categories/{id}",
        produces = "*/*",
        consumes = "application/json"
    )
    ResponseEntity<CategoryDTO> updateCategory(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "CategoryDTO", description = "", required = true) @Valid @RequestBody CategoryDTO categoryDTO
    );

}
