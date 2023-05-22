package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.domain.CreateCategoryCommand;
import org.contextmapper.generated.gateway.repository.CreateCategoryCommandRepository;
import org.contextmapper.generated.gateway.service.CreateCategoryCommandService;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.CreateCategoryCommand}.
 */
@RestController
@RequestMapping("/api")
public class CreateCategoryCommandResource {

    private final Logger log = LoggerFactory.getLogger(CreateCategoryCommandResource.class);

    private static final String ENTITY_NAME = "createCategoryCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreateCategoryCommandService createCategoryCommandService;

    private final CreateCategoryCommandRepository createCategoryCommandRepository;

    public CreateCategoryCommandResource(
        CreateCategoryCommandService createCategoryCommandService,
        CreateCategoryCommandRepository createCategoryCommandRepository
    ) {
        this.createCategoryCommandService = createCategoryCommandService;
        this.createCategoryCommandRepository = createCategoryCommandRepository;
    }

    /**
     * {@code POST  /create-category-commands} : Create a new createCategoryCommand.
     *
     * @param createCategoryCommand the createCategoryCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createCategoryCommand, or with status {@code 400 (Bad Request)} if the createCategoryCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-category-commands")
    public Mono<ResponseEntity<CreateCategoryCommand>> createCreateCategoryCommand(
        @RequestBody CreateCategoryCommand createCategoryCommand
    ) throws URISyntaxException {
        log.debug("REST request to save CreateCategoryCommand : {}", createCategoryCommand);
        if (createCategoryCommand.getId() != null) {
            throw new BadRequestAlertException("A new createCategoryCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return createCategoryCommandService
            .save(createCategoryCommand)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/create-category-commands/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /create-category-commands/:id} : Updates an existing createCategoryCommand.
     *
     * @param id the id of the createCategoryCommand to save.
     * @param createCategoryCommand the createCategoryCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createCategoryCommand,
     * or with status {@code 400 (Bad Request)} if the createCategoryCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the createCategoryCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/create-category-commands/{id}")
    public Mono<ResponseEntity<CreateCategoryCommand>> updateCreateCategoryCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateCategoryCommand createCategoryCommand
    ) throws URISyntaxException {
        log.debug("REST request to update CreateCategoryCommand : {}, {}", id, createCategoryCommand);
        if (createCategoryCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createCategoryCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return createCategoryCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return createCategoryCommandService
                    .update(createCategoryCommand)
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
     * {@code PATCH  /create-category-commands/:id} : Partial updates given fields of an existing createCategoryCommand, field will ignore if it is null
     *
     * @param id the id of the createCategoryCommand to save.
     * @param createCategoryCommand the createCategoryCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createCategoryCommand,
     * or with status {@code 400 (Bad Request)} if the createCategoryCommand is not valid,
     * or with status {@code 404 (Not Found)} if the createCategoryCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the createCategoryCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/create-category-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CreateCategoryCommand>> partialUpdateCreateCategoryCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateCategoryCommand createCategoryCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreateCategoryCommand partially : {}, {}", id, createCategoryCommand);
        if (createCategoryCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createCategoryCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return createCategoryCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CreateCategoryCommand> result = createCategoryCommandService.partialUpdate(createCategoryCommand);

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
     * {@code GET  /create-category-commands} : get all the createCategoryCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createCategoryCommands in body.
     */
    @GetMapping("/create-category-commands")
    public Mono<List<CreateCategoryCommand>> getAllCreateCategoryCommands() {
        log.debug("REST request to get all CreateCategoryCommands");
        return createCategoryCommandService.findAll().collectList();
    }

    /**
     * {@code GET  /create-category-commands} : get all the createCategoryCommands as a stream.
     * @return the {@link Flux} of createCategoryCommands.
     */
    @GetMapping(value = "/create-category-commands", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<CreateCategoryCommand> getAllCreateCategoryCommandsAsStream() {
        log.debug("REST request to get all CreateCategoryCommands as a stream");
        return createCategoryCommandService.findAll();
    }

    /**
     * {@code GET  /create-category-commands/:id} : get the "id" createCategoryCommand.
     *
     * @param id the id of the createCategoryCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createCategoryCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/create-category-commands/{id}")
    public Mono<ResponseEntity<CreateCategoryCommand>> getCreateCategoryCommand(@PathVariable Long id) {
        log.debug("REST request to get CreateCategoryCommand : {}", id);
        Mono<CreateCategoryCommand> createCategoryCommand = createCategoryCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createCategoryCommand);
    }

    /**
     * {@code DELETE  /create-category-commands/:id} : delete the "id" createCategoryCommand.
     *
     * @param id the id of the createCategoryCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/create-category-commands/{id}")
    public Mono<ResponseEntity<Void>> deleteCreateCategoryCommand(@PathVariable Long id) {
        log.debug("REST request to delete CreateCategoryCommand : {}", id);
        return createCategoryCommandService
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
