package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.domain.CreateEvaluationCommand;
import org.contextmapper.generated.gateway.repository.CreateEvaluationCommandRepository;
import org.contextmapper.generated.gateway.service.CreateEvaluationCommandService;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.CreateEvaluationCommand}.
 */
@RestController
@RequestMapping("/api")
public class CreateEvaluationCommandResource {

    private final Logger log = LoggerFactory.getLogger(CreateEvaluationCommandResource.class);

    private static final String ENTITY_NAME = "createEvaluationCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreateEvaluationCommandService createEvaluationCommandService;

    private final CreateEvaluationCommandRepository createEvaluationCommandRepository;

    public CreateEvaluationCommandResource(
        CreateEvaluationCommandService createEvaluationCommandService,
        CreateEvaluationCommandRepository createEvaluationCommandRepository
    ) {
        this.createEvaluationCommandService = createEvaluationCommandService;
        this.createEvaluationCommandRepository = createEvaluationCommandRepository;
    }

    /**
     * {@code POST  /create-evaluation-commands} : Create a new createEvaluationCommand.
     *
     * @param createEvaluationCommand the createEvaluationCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createEvaluationCommand, or with status {@code 400 (Bad Request)} if the createEvaluationCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-evaluation-commands")
    public Mono<ResponseEntity<CreateEvaluationCommand>> createCreateEvaluationCommand(
        @RequestBody CreateEvaluationCommand createEvaluationCommand
    ) throws URISyntaxException {
        log.debug("REST request to save CreateEvaluationCommand : {}", createEvaluationCommand);
        if (createEvaluationCommand.getId() != null) {
            throw new BadRequestAlertException("A new createEvaluationCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return createEvaluationCommandService
            .save(createEvaluationCommand)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/create-evaluation-commands/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /create-evaluation-commands/:id} : Updates an existing createEvaluationCommand.
     *
     * @param id the id of the createEvaluationCommand to save.
     * @param createEvaluationCommand the createEvaluationCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createEvaluationCommand,
     * or with status {@code 400 (Bad Request)} if the createEvaluationCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the createEvaluationCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/create-evaluation-commands/{id}")
    public Mono<ResponseEntity<CreateEvaluationCommand>> updateCreateEvaluationCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateEvaluationCommand createEvaluationCommand
    ) throws URISyntaxException {
        log.debug("REST request to update CreateEvaluationCommand : {}, {}", id, createEvaluationCommand);
        if (createEvaluationCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createEvaluationCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return createEvaluationCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return createEvaluationCommandService
                    .update(createEvaluationCommand)
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
     * {@code PATCH  /create-evaluation-commands/:id} : Partial updates given fields of an existing createEvaluationCommand, field will ignore if it is null
     *
     * @param id the id of the createEvaluationCommand to save.
     * @param createEvaluationCommand the createEvaluationCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createEvaluationCommand,
     * or with status {@code 400 (Bad Request)} if the createEvaluationCommand is not valid,
     * or with status {@code 404 (Not Found)} if the createEvaluationCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the createEvaluationCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/create-evaluation-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CreateEvaluationCommand>> partialUpdateCreateEvaluationCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateEvaluationCommand createEvaluationCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreateEvaluationCommand partially : {}, {}", id, createEvaluationCommand);
        if (createEvaluationCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createEvaluationCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return createEvaluationCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CreateEvaluationCommand> result = createEvaluationCommandService.partialUpdate(createEvaluationCommand);

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
     * {@code GET  /create-evaluation-commands} : get all the createEvaluationCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createEvaluationCommands in body.
     */
    @GetMapping("/create-evaluation-commands")
    public Mono<List<CreateEvaluationCommand>> getAllCreateEvaluationCommands() {
        log.debug("REST request to get all CreateEvaluationCommands");
        return createEvaluationCommandService.findAll().collectList();
    }

    /**
     * {@code GET  /create-evaluation-commands} : get all the createEvaluationCommands as a stream.
     * @return the {@link Flux} of createEvaluationCommands.
     */
    @GetMapping(value = "/create-evaluation-commands", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<CreateEvaluationCommand> getAllCreateEvaluationCommandsAsStream() {
        log.debug("REST request to get all CreateEvaluationCommands as a stream");
        return createEvaluationCommandService.findAll();
    }

    /**
     * {@code GET  /create-evaluation-commands/:id} : get the "id" createEvaluationCommand.
     *
     * @param id the id of the createEvaluationCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createEvaluationCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/create-evaluation-commands/{id}")
    public Mono<ResponseEntity<CreateEvaluationCommand>> getCreateEvaluationCommand(@PathVariable Long id) {
        log.debug("REST request to get CreateEvaluationCommand : {}", id);
        Mono<CreateEvaluationCommand> createEvaluationCommand = createEvaluationCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createEvaluationCommand);
    }

    /**
     * {@code DELETE  /create-evaluation-commands/:id} : delete the "id" createEvaluationCommand.
     *
     * @param id the id of the createEvaluationCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/create-evaluation-commands/{id}")
    public Mono<ResponseEntity<Void>> deleteCreateEvaluationCommand(@PathVariable Long id) {
        log.debug("REST request to delete CreateEvaluationCommand : {}", id);
        return createEvaluationCommandService
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
