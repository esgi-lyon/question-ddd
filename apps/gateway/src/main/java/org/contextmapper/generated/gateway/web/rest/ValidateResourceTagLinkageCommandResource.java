package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.domain.ValidateResourceTagLinkageCommand;
import org.contextmapper.generated.gateway.repository.ValidateResourceTagLinkageCommandRepository;
import org.contextmapper.generated.gateway.service.ValidateResourceTagLinkageCommandService;
import org.contextmapper.generated.gateway.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.ValidateResourceTagLinkageCommand}.
 */
@RestController
@RequestMapping("/api")
public class ValidateResourceTagLinkageCommandResource {

    private final Logger log = LoggerFactory.getLogger(ValidateResourceTagLinkageCommandResource.class);

    private static final String ENTITY_NAME = "validateResourceTagLinkageCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ValidateResourceTagLinkageCommandService validateResourceTagLinkageCommandService;

    private final ValidateResourceTagLinkageCommandRepository validateResourceTagLinkageCommandRepository;

    public ValidateResourceTagLinkageCommandResource(
        ValidateResourceTagLinkageCommandService validateResourceTagLinkageCommandService,
        ValidateResourceTagLinkageCommandRepository validateResourceTagLinkageCommandRepository
    ) {
        this.validateResourceTagLinkageCommandService = validateResourceTagLinkageCommandService;
        this.validateResourceTagLinkageCommandRepository = validateResourceTagLinkageCommandRepository;
    }

    /**
     * {@code POST  /validate-resource-tag-linkage-commands} : Create a new validateResourceTagLinkageCommand.
     *
     * @param validateResourceTagLinkageCommand the validateResourceTagLinkageCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new validateResourceTagLinkageCommand, or with status {@code 400 (Bad Request)} if the validateResourceTagLinkageCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/validate-resource-tag-linkage-commands")
    public Mono<ResponseEntity<ValidateResourceTagLinkageCommand>> createValidateResourceTagLinkageCommand(
        @RequestBody ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand
    ) throws URISyntaxException {
        log.debug("REST request to save ValidateResourceTagLinkageCommand : {}", validateResourceTagLinkageCommand);
        if (validateResourceTagLinkageCommand.getId() != null) {
            throw new BadRequestAlertException(
                "A new validateResourceTagLinkageCommand cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        return validateResourceTagLinkageCommandService
            .save(validateResourceTagLinkageCommand)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/validate-resource-tag-linkage-commands/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /validate-resource-tag-linkage-commands/:id} : Updates an existing validateResourceTagLinkageCommand.
     *
     * @param id the id of the validateResourceTagLinkageCommand to save.
     * @param validateResourceTagLinkageCommand the validateResourceTagLinkageCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated validateResourceTagLinkageCommand,
     * or with status {@code 400 (Bad Request)} if the validateResourceTagLinkageCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the validateResourceTagLinkageCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/validate-resource-tag-linkage-commands/{id}")
    public Mono<ResponseEntity<ValidateResourceTagLinkageCommand>> updateValidateResourceTagLinkageCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand
    ) throws URISyntaxException {
        log.debug("REST request to update ValidateResourceTagLinkageCommand : {}, {}", id, validateResourceTagLinkageCommand);
        if (validateResourceTagLinkageCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, validateResourceTagLinkageCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return validateResourceTagLinkageCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return validateResourceTagLinkageCommandService
                    .update(validateResourceTagLinkageCommand)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /validate-resource-tag-linkage-commands/:id} : Partial updates given fields of an existing validateResourceTagLinkageCommand, field will ignore if it is null
     *
     * @param id the id of the validateResourceTagLinkageCommand to save.
     * @param validateResourceTagLinkageCommand the validateResourceTagLinkageCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated validateResourceTagLinkageCommand,
     * or with status {@code 400 (Bad Request)} if the validateResourceTagLinkageCommand is not valid,
     * or with status {@code 404 (Not Found)} if the validateResourceTagLinkageCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the validateResourceTagLinkageCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/validate-resource-tag-linkage-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ValidateResourceTagLinkageCommand>> partialUpdateValidateResourceTagLinkageCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ValidateResourceTagLinkageCommand validateResourceTagLinkageCommand
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ValidateResourceTagLinkageCommand partially : {}, {}",
            id,
            validateResourceTagLinkageCommand
        );
        if (validateResourceTagLinkageCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, validateResourceTagLinkageCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return validateResourceTagLinkageCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ValidateResourceTagLinkageCommand> result = validateResourceTagLinkageCommandService.partialUpdate(
                    validateResourceTagLinkageCommand
                );

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /validate-resource-tag-linkage-commands} : get all the validateResourceTagLinkageCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of validateResourceTagLinkageCommands in body.
     */
    @GetMapping("/validate-resource-tag-linkage-commands")
    public Mono<List<ValidateResourceTagLinkageCommand>> getAllValidateResourceTagLinkageCommands() {
        log.debug("REST request to get all ValidateResourceTagLinkageCommands");
        return validateResourceTagLinkageCommandService.findAll().collectList();
    }

    /**
     * {@code GET  /validate-resource-tag-linkage-commands} : get all the validateResourceTagLinkageCommands as a stream.
     * @return the {@link Flux} of validateResourceTagLinkageCommands.
     */
    @GetMapping(value = "/validate-resource-tag-linkage-commands", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<ValidateResourceTagLinkageCommand> getAllValidateResourceTagLinkageCommandsAsStream() {
        log.debug("REST request to get all ValidateResourceTagLinkageCommands as a stream");
        return validateResourceTagLinkageCommandService.findAll();
    }

    /**
     * {@code GET  /validate-resource-tag-linkage-commands/:id} : get the "id" validateResourceTagLinkageCommand.
     *
     * @param id the id of the validateResourceTagLinkageCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the validateResourceTagLinkageCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/validate-resource-tag-linkage-commands/{id}")
    public Mono<ResponseEntity<ValidateResourceTagLinkageCommand>> getValidateResourceTagLinkageCommand(@PathVariable Long id) {
        log.debug("REST request to get ValidateResourceTagLinkageCommand : {}", id);
        Mono<ValidateResourceTagLinkageCommand> validateResourceTagLinkageCommand = validateResourceTagLinkageCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(validateResourceTagLinkageCommand);
    }

    /**
     * {@code DELETE  /validate-resource-tag-linkage-commands/:id} : delete the "id" validateResourceTagLinkageCommand.
     *
     * @param id the id of the validateResourceTagLinkageCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/validate-resource-tag-linkage-commands/{id}")
    public Mono<ResponseEntity<Void>> deleteValidateResourceTagLinkageCommand(@PathVariable Long id) {
        log.debug("REST request to delete ValidateResourceTagLinkageCommand : {}", id);
        return validateResourceTagLinkageCommandService
            .delete(id)
            .then(
                Mono.just(
                    ResponseEntity
                        .noContent()
                        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                        .build()
                )
            );
    }
}
