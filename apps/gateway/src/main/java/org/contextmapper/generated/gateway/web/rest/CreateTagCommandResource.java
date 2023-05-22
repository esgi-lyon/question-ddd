package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.domain.CreateTagCommand;
import org.contextmapper.generated.gateway.repository.CreateTagCommandRepository;
import org.contextmapper.generated.gateway.service.CreateTagCommandService;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.CreateTagCommand}.
 */
@RestController
@RequestMapping("/api")
public class CreateTagCommandResource {

    private final Logger log = LoggerFactory.getLogger(CreateTagCommandResource.class);

    private static final String ENTITY_NAME = "createTagCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreateTagCommandService createTagCommandService;

    private final CreateTagCommandRepository createTagCommandRepository;

    public CreateTagCommandResource(
        CreateTagCommandService createTagCommandService,
        CreateTagCommandRepository createTagCommandRepository
    ) {
        this.createTagCommandService = createTagCommandService;
        this.createTagCommandRepository = createTagCommandRepository;
    }

    /**
     * {@code POST  /create-tag-commands} : Create a new createTagCommand.
     *
     * @param createTagCommand the createTagCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createTagCommand, or with status {@code 400 (Bad Request)} if the createTagCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-tag-commands")
    public Mono<ResponseEntity<CreateTagCommand>> createCreateTagCommand(@RequestBody CreateTagCommand createTagCommand)
        throws URISyntaxException {
        log.debug("REST request to save CreateTagCommand : {}", createTagCommand);
        if (createTagCommand.getId() != null) {
            throw new BadRequestAlertException("A new createTagCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return createTagCommandService
            .save(createTagCommand)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/create-tag-commands/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /create-tag-commands/:id} : Updates an existing createTagCommand.
     *
     * @param id the id of the createTagCommand to save.
     * @param createTagCommand the createTagCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createTagCommand,
     * or with status {@code 400 (Bad Request)} if the createTagCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the createTagCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/create-tag-commands/{id}")
    public Mono<ResponseEntity<CreateTagCommand>> updateCreateTagCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateTagCommand createTagCommand
    ) throws URISyntaxException {
        log.debug("REST request to update CreateTagCommand : {}, {}", id, createTagCommand);
        if (createTagCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createTagCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return createTagCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return createTagCommandService
                    .update(createTagCommand)
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
     * {@code PATCH  /create-tag-commands/:id} : Partial updates given fields of an existing createTagCommand, field will ignore if it is null
     *
     * @param id the id of the createTagCommand to save.
     * @param createTagCommand the createTagCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createTagCommand,
     * or with status {@code 400 (Bad Request)} if the createTagCommand is not valid,
     * or with status {@code 404 (Not Found)} if the createTagCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the createTagCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/create-tag-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CreateTagCommand>> partialUpdateCreateTagCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateTagCommand createTagCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreateTagCommand partially : {}, {}", id, createTagCommand);
        if (createTagCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createTagCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return createTagCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CreateTagCommand> result = createTagCommandService.partialUpdate(createTagCommand);

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
     * {@code GET  /create-tag-commands} : get all the createTagCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createTagCommands in body.
     */
    @GetMapping("/create-tag-commands")
    public Mono<List<CreateTagCommand>> getAllCreateTagCommands() {
        log.debug("REST request to get all CreateTagCommands");
        return createTagCommandService.findAll().collectList();
    }

    /**
     * {@code GET  /create-tag-commands} : get all the createTagCommands as a stream.
     * @return the {@link Flux} of createTagCommands.
     */
    @GetMapping(value = "/create-tag-commands", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<CreateTagCommand> getAllCreateTagCommandsAsStream() {
        log.debug("REST request to get all CreateTagCommands as a stream");
        return createTagCommandService.findAll();
    }

    /**
     * {@code GET  /create-tag-commands/:id} : get the "id" createTagCommand.
     *
     * @param id the id of the createTagCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createTagCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/create-tag-commands/{id}")
    public Mono<ResponseEntity<CreateTagCommand>> getCreateTagCommand(@PathVariable Long id) {
        log.debug("REST request to get CreateTagCommand : {}", id);
        Mono<CreateTagCommand> createTagCommand = createTagCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createTagCommand);
    }

    /**
     * {@code DELETE  /create-tag-commands/:id} : delete the "id" createTagCommand.
     *
     * @param id the id of the createTagCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/create-tag-commands/{id}")
    public Mono<ResponseEntity<Void>> deleteCreateTagCommand(@PathVariable Long id) {
        log.debug("REST request to delete CreateTagCommand : {}", id);
        return createTagCommandService
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
