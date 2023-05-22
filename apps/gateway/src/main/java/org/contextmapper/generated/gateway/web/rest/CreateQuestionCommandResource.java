package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.domain.CreateQuestionCommand;
import org.contextmapper.generated.gateway.repository.CreateQuestionCommandRepository;
import org.contextmapper.generated.gateway.service.CreateQuestionCommandService;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.CreateQuestionCommand}.
 */
@RestController
@RequestMapping("/api")
public class CreateQuestionCommandResource {

    private final Logger log = LoggerFactory.getLogger(CreateQuestionCommandResource.class);

    private static final String ENTITY_NAME = "createQuestionCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreateQuestionCommandService createQuestionCommandService;

    private final CreateQuestionCommandRepository createQuestionCommandRepository;

    public CreateQuestionCommandResource(
        CreateQuestionCommandService createQuestionCommandService,
        CreateQuestionCommandRepository createQuestionCommandRepository
    ) {
        this.createQuestionCommandService = createQuestionCommandService;
        this.createQuestionCommandRepository = createQuestionCommandRepository;
    }

    /**
     * {@code POST  /create-question-commands} : Create a new createQuestionCommand.
     *
     * @param createQuestionCommand the createQuestionCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createQuestionCommand, or with status {@code 400 (Bad Request)} if the createQuestionCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-question-commands")
    public Mono<ResponseEntity<CreateQuestionCommand>> createCreateQuestionCommand(
        @RequestBody CreateQuestionCommand createQuestionCommand
    ) throws URISyntaxException {
        log.debug("REST request to save CreateQuestionCommand : {}", createQuestionCommand);
        if (createQuestionCommand.getId() != null) {
            throw new BadRequestAlertException("A new createQuestionCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return createQuestionCommandService
            .save(createQuestionCommand)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/create-question-commands/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /create-question-commands/:id} : Updates an existing createQuestionCommand.
     *
     * @param id the id of the createQuestionCommand to save.
     * @param createQuestionCommand the createQuestionCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createQuestionCommand,
     * or with status {@code 400 (Bad Request)} if the createQuestionCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the createQuestionCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/create-question-commands/{id}")
    public Mono<ResponseEntity<CreateQuestionCommand>> updateCreateQuestionCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateQuestionCommand createQuestionCommand
    ) throws URISyntaxException {
        log.debug("REST request to update CreateQuestionCommand : {}, {}", id, createQuestionCommand);
        if (createQuestionCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createQuestionCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return createQuestionCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return createQuestionCommandService
                    .update(createQuestionCommand)
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
     * {@code PATCH  /create-question-commands/:id} : Partial updates given fields of an existing createQuestionCommand, field will ignore if it is null
     *
     * @param id the id of the createQuestionCommand to save.
     * @param createQuestionCommand the createQuestionCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createQuestionCommand,
     * or with status {@code 400 (Bad Request)} if the createQuestionCommand is not valid,
     * or with status {@code 404 (Not Found)} if the createQuestionCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the createQuestionCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/create-question-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CreateQuestionCommand>> partialUpdateCreateQuestionCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateQuestionCommand createQuestionCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreateQuestionCommand partially : {}, {}", id, createQuestionCommand);
        if (createQuestionCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createQuestionCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return createQuestionCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CreateQuestionCommand> result = createQuestionCommandService.partialUpdate(createQuestionCommand);

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
     * {@code GET  /create-question-commands} : get all the createQuestionCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createQuestionCommands in body.
     */
    @GetMapping("/create-question-commands")
    public Mono<List<CreateQuestionCommand>> getAllCreateQuestionCommands() {
        log.debug("REST request to get all CreateQuestionCommands");
        return createQuestionCommandService.findAll().collectList();
    }

    /**
     * {@code GET  /create-question-commands} : get all the createQuestionCommands as a stream.
     * @return the {@link Flux} of createQuestionCommands.
     */
    @GetMapping(value = "/create-question-commands", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<CreateQuestionCommand> getAllCreateQuestionCommandsAsStream() {
        log.debug("REST request to get all CreateQuestionCommands as a stream");
        return createQuestionCommandService.findAll();
    }

    /**
     * {@code GET  /create-question-commands/:id} : get the "id" createQuestionCommand.
     *
     * @param id the id of the createQuestionCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createQuestionCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/create-question-commands/{id}")
    public Mono<ResponseEntity<CreateQuestionCommand>> getCreateQuestionCommand(@PathVariable Long id) {
        log.debug("REST request to get CreateQuestionCommand : {}", id);
        Mono<CreateQuestionCommand> createQuestionCommand = createQuestionCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createQuestionCommand);
    }

    /**
     * {@code DELETE  /create-question-commands/:id} : delete the "id" createQuestionCommand.
     *
     * @param id the id of the createQuestionCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/create-question-commands/{id}")
    public Mono<ResponseEntity<Void>> deleteCreateQuestionCommand(@PathVariable Long id) {
        log.debug("REST request to delete CreateQuestionCommand : {}", id);
        return createQuestionCommandService
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
