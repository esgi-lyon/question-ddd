package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.domain.RejectResourceTagCommand;
import org.contextmapper.generated.gateway.repository.RejectResourceTagCommandRepository;
import org.contextmapper.generated.gateway.service.RejectResourceTagCommandService;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.RejectResourceTagCommand}.
 */
@RestController
@RequestMapping("/api")
public class RejectResourceTagCommandResource {

    private final Logger log = LoggerFactory.getLogger(RejectResourceTagCommandResource.class);

    private static final String ENTITY_NAME = "rejectResourceTagCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RejectResourceTagCommandService rejectResourceTagCommandService;

    private final RejectResourceTagCommandRepository rejectResourceTagCommandRepository;

    public RejectResourceTagCommandResource(
        RejectResourceTagCommandService rejectResourceTagCommandService,
        RejectResourceTagCommandRepository rejectResourceTagCommandRepository
    ) {
        this.rejectResourceTagCommandService = rejectResourceTagCommandService;
        this.rejectResourceTagCommandRepository = rejectResourceTagCommandRepository;
    }

    /**
     * {@code POST  /reject-resource-tag-commands} : Create a new rejectResourceTagCommand.
     *
     * @param rejectResourceTagCommand the rejectResourceTagCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rejectResourceTagCommand, or with status {@code 400 (Bad Request)} if the rejectResourceTagCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reject-resource-tag-commands")
    public Mono<ResponseEntity<RejectResourceTagCommand>> createRejectResourceTagCommand(
        @RequestBody RejectResourceTagCommand rejectResourceTagCommand
    ) throws URISyntaxException {
        log.debug("REST request to save RejectResourceTagCommand : {}", rejectResourceTagCommand);
        if (rejectResourceTagCommand.getId() != null) {
            throw new BadRequestAlertException("A new rejectResourceTagCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return rejectResourceTagCommandService
            .save(rejectResourceTagCommand)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/reject-resource-tag-commands/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /reject-resource-tag-commands/:id} : Updates an existing rejectResourceTagCommand.
     *
     * @param id the id of the rejectResourceTagCommand to save.
     * @param rejectResourceTagCommand the rejectResourceTagCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rejectResourceTagCommand,
     * or with status {@code 400 (Bad Request)} if the rejectResourceTagCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rejectResourceTagCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reject-resource-tag-commands/{id}")
    public Mono<ResponseEntity<RejectResourceTagCommand>> updateRejectResourceTagCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RejectResourceTagCommand rejectResourceTagCommand
    ) throws URISyntaxException {
        log.debug("REST request to update RejectResourceTagCommand : {}, {}", id, rejectResourceTagCommand);
        if (rejectResourceTagCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rejectResourceTagCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return rejectResourceTagCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return rejectResourceTagCommandService
                    .update(rejectResourceTagCommand)
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
     * {@code PATCH  /reject-resource-tag-commands/:id} : Partial updates given fields of an existing rejectResourceTagCommand, field will ignore if it is null
     *
     * @param id the id of the rejectResourceTagCommand to save.
     * @param rejectResourceTagCommand the rejectResourceTagCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rejectResourceTagCommand,
     * or with status {@code 400 (Bad Request)} if the rejectResourceTagCommand is not valid,
     * or with status {@code 404 (Not Found)} if the rejectResourceTagCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the rejectResourceTagCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/reject-resource-tag-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<RejectResourceTagCommand>> partialUpdateRejectResourceTagCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RejectResourceTagCommand rejectResourceTagCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update RejectResourceTagCommand partially : {}, {}", id, rejectResourceTagCommand);
        if (rejectResourceTagCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rejectResourceTagCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return rejectResourceTagCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<RejectResourceTagCommand> result = rejectResourceTagCommandService.partialUpdate(rejectResourceTagCommand);

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
     * {@code GET  /reject-resource-tag-commands} : get all the rejectResourceTagCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rejectResourceTagCommands in body.
     */
    @GetMapping("/reject-resource-tag-commands")
    public Mono<List<RejectResourceTagCommand>> getAllRejectResourceTagCommands() {
        log.debug("REST request to get all RejectResourceTagCommands");
        return rejectResourceTagCommandService.findAll().collectList();
    }

    /**
     * {@code GET  /reject-resource-tag-commands} : get all the rejectResourceTagCommands as a stream.
     * @return the {@link Flux} of rejectResourceTagCommands.
     */
    @GetMapping(value = "/reject-resource-tag-commands", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<RejectResourceTagCommand> getAllRejectResourceTagCommandsAsStream() {
        log.debug("REST request to get all RejectResourceTagCommands as a stream");
        return rejectResourceTagCommandService.findAll();
    }

    /**
     * {@code GET  /reject-resource-tag-commands/:id} : get the "id" rejectResourceTagCommand.
     *
     * @param id the id of the rejectResourceTagCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rejectResourceTagCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reject-resource-tag-commands/{id}")
    public Mono<ResponseEntity<RejectResourceTagCommand>> getRejectResourceTagCommand(@PathVariable Long id) {
        log.debug("REST request to get RejectResourceTagCommand : {}", id);
        Mono<RejectResourceTagCommand> rejectResourceTagCommand = rejectResourceTagCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rejectResourceTagCommand);
    }

    /**
     * {@code DELETE  /reject-resource-tag-commands/:id} : delete the "id" rejectResourceTagCommand.
     *
     * @param id the id of the rejectResourceTagCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reject-resource-tag-commands/{id}")
    public Mono<ResponseEntity<Void>> deleteRejectResourceTagCommand(@PathVariable Long id) {
        log.debug("REST request to delete RejectResourceTagCommand : {}", id);
        return rejectResourceTagCommandService
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
