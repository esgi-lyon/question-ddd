package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.domain.CheckAnswerCommand;
import org.contextmapper.generated.gateway.repository.CheckAnswerCommandRepository;
import org.contextmapper.generated.gateway.service.CheckAnswerCommandService;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.CheckAnswerCommand}.
 */
@RestController
@RequestMapping("/api")
public class CheckAnswerCommandResource {

    private final Logger log = LoggerFactory.getLogger(CheckAnswerCommandResource.class);

    private static final String ENTITY_NAME = "checkAnswerCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CheckAnswerCommandService checkAnswerCommandService;

    private final CheckAnswerCommandRepository checkAnswerCommandRepository;

    public CheckAnswerCommandResource(
        CheckAnswerCommandService checkAnswerCommandService,
        CheckAnswerCommandRepository checkAnswerCommandRepository
    ) {
        this.checkAnswerCommandService = checkAnswerCommandService;
        this.checkAnswerCommandRepository = checkAnswerCommandRepository;
    }

    /**
     * {@code POST  /check-answer-commands} : Create a new checkAnswerCommand.
     *
     * @param checkAnswerCommand the checkAnswerCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new checkAnswerCommand, or with status {@code 400 (Bad Request)} if the checkAnswerCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/check-answer-commands")
    public Mono<ResponseEntity<CheckAnswerCommand>> createCheckAnswerCommand(@RequestBody CheckAnswerCommand checkAnswerCommand)
        throws URISyntaxException {
        log.debug("REST request to save CheckAnswerCommand : {}", checkAnswerCommand);
        if (checkAnswerCommand.getId() != null) {
            throw new BadRequestAlertException("A new checkAnswerCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return checkAnswerCommandService
            .save(checkAnswerCommand)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/check-answer-commands/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /check-answer-commands/:id} : Updates an existing checkAnswerCommand.
     *
     * @param id the id of the checkAnswerCommand to save.
     * @param checkAnswerCommand the checkAnswerCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checkAnswerCommand,
     * or with status {@code 400 (Bad Request)} if the checkAnswerCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the checkAnswerCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/check-answer-commands/{id}")
    public Mono<ResponseEntity<CheckAnswerCommand>> updateCheckAnswerCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CheckAnswerCommand checkAnswerCommand
    ) throws URISyntaxException {
        log.debug("REST request to update CheckAnswerCommand : {}, {}", id, checkAnswerCommand);
        if (checkAnswerCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, checkAnswerCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return checkAnswerCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return checkAnswerCommandService
                    .update(checkAnswerCommand)
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
     * {@code PATCH  /check-answer-commands/:id} : Partial updates given fields of an existing checkAnswerCommand, field will ignore if it is null
     *
     * @param id the id of the checkAnswerCommand to save.
     * @param checkAnswerCommand the checkAnswerCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checkAnswerCommand,
     * or with status {@code 400 (Bad Request)} if the checkAnswerCommand is not valid,
     * or with status {@code 404 (Not Found)} if the checkAnswerCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the checkAnswerCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/check-answer-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CheckAnswerCommand>> partialUpdateCheckAnswerCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CheckAnswerCommand checkAnswerCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update CheckAnswerCommand partially : {}, {}", id, checkAnswerCommand);
        if (checkAnswerCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, checkAnswerCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return checkAnswerCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CheckAnswerCommand> result = checkAnswerCommandService.partialUpdate(checkAnswerCommand);

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
     * {@code GET  /check-answer-commands} : get all the checkAnswerCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of checkAnswerCommands in body.
     */
    @GetMapping("/check-answer-commands")
    public Mono<List<CheckAnswerCommand>> getAllCheckAnswerCommands() {
        log.debug("REST request to get all CheckAnswerCommands");
        return checkAnswerCommandService.findAll().collectList();
    }

    /**
     * {@code GET  /check-answer-commands} : get all the checkAnswerCommands as a stream.
     * @return the {@link Flux} of checkAnswerCommands.
     */
    @GetMapping(value = "/check-answer-commands", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<CheckAnswerCommand> getAllCheckAnswerCommandsAsStream() {
        log.debug("REST request to get all CheckAnswerCommands as a stream");
        return checkAnswerCommandService.findAll();
    }

    /**
     * {@code GET  /check-answer-commands/:id} : get the "id" checkAnswerCommand.
     *
     * @param id the id of the checkAnswerCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the checkAnswerCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/check-answer-commands/{id}")
    public Mono<ResponseEntity<CheckAnswerCommand>> getCheckAnswerCommand(@PathVariable Long id) {
        log.debug("REST request to get CheckAnswerCommand : {}", id);
        Mono<CheckAnswerCommand> checkAnswerCommand = checkAnswerCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(checkAnswerCommand);
    }

    /**
     * {@code DELETE  /check-answer-commands/:id} : delete the "id" checkAnswerCommand.
     *
     * @param id the id of the checkAnswerCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/check-answer-commands/{id}")
    public Mono<ResponseEntity<Void>> deleteCheckAnswerCommand(@PathVariable Long id) {
        log.debug("REST request to delete CheckAnswerCommand : {}", id);
        return checkAnswerCommandService
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
