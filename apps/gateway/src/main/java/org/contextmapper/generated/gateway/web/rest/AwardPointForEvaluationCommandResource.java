package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.domain.AwardPointForEvaluationCommand;
import org.contextmapper.generated.gateway.repository.AwardPointForEvaluationCommandRepository;
import org.contextmapper.generated.gateway.service.AwardPointForEvaluationCommandService;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.AwardPointForEvaluationCommand}.
 */
@RestController
@RequestMapping("/api")
public class AwardPointForEvaluationCommandResource {

    private final Logger log = LoggerFactory.getLogger(AwardPointForEvaluationCommandResource.class);

    private static final String ENTITY_NAME = "awardPointForEvaluationCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AwardPointForEvaluationCommandService awardPointForEvaluationCommandService;

    private final AwardPointForEvaluationCommandRepository awardPointForEvaluationCommandRepository;

    public AwardPointForEvaluationCommandResource(
        AwardPointForEvaluationCommandService awardPointForEvaluationCommandService,
        AwardPointForEvaluationCommandRepository awardPointForEvaluationCommandRepository
    ) {
        this.awardPointForEvaluationCommandService = awardPointForEvaluationCommandService;
        this.awardPointForEvaluationCommandRepository = awardPointForEvaluationCommandRepository;
    }

    /**
     * {@code POST  /award-point-for-evaluation-commands} : Create a new awardPointForEvaluationCommand.
     *
     * @param awardPointForEvaluationCommand the awardPointForEvaluationCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new awardPointForEvaluationCommand, or with status {@code 400 (Bad Request)} if the awardPointForEvaluationCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/award-point-for-evaluation-commands")
    public Mono<ResponseEntity<AwardPointForEvaluationCommand>> createAwardPointForEvaluationCommand(
        @RequestBody AwardPointForEvaluationCommand awardPointForEvaluationCommand
    ) throws URISyntaxException {
        log.debug("REST request to save AwardPointForEvaluationCommand : {}", awardPointForEvaluationCommand);
        if (awardPointForEvaluationCommand.getId() != null) {
            throw new BadRequestAlertException("A new awardPointForEvaluationCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return awardPointForEvaluationCommandService
            .save(awardPointForEvaluationCommand)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/award-point-for-evaluation-commands/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /award-point-for-evaluation-commands/:id} : Updates an existing awardPointForEvaluationCommand.
     *
     * @param id the id of the awardPointForEvaluationCommand to save.
     * @param awardPointForEvaluationCommand the awardPointForEvaluationCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated awardPointForEvaluationCommand,
     * or with status {@code 400 (Bad Request)} if the awardPointForEvaluationCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the awardPointForEvaluationCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/award-point-for-evaluation-commands/{id}")
    public Mono<ResponseEntity<AwardPointForEvaluationCommand>> updateAwardPointForEvaluationCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AwardPointForEvaluationCommand awardPointForEvaluationCommand
    ) throws URISyntaxException {
        log.debug("REST request to update AwardPointForEvaluationCommand : {}, {}", id, awardPointForEvaluationCommand);
        if (awardPointForEvaluationCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, awardPointForEvaluationCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return awardPointForEvaluationCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return awardPointForEvaluationCommandService
                    .update(awardPointForEvaluationCommand)
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
     * {@code PATCH  /award-point-for-evaluation-commands/:id} : Partial updates given fields of an existing awardPointForEvaluationCommand, field will ignore if it is null
     *
     * @param id the id of the awardPointForEvaluationCommand to save.
     * @param awardPointForEvaluationCommand the awardPointForEvaluationCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated awardPointForEvaluationCommand,
     * or with status {@code 400 (Bad Request)} if the awardPointForEvaluationCommand is not valid,
     * or with status {@code 404 (Not Found)} if the awardPointForEvaluationCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the awardPointForEvaluationCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/award-point-for-evaluation-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<AwardPointForEvaluationCommand>> partialUpdateAwardPointForEvaluationCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AwardPointForEvaluationCommand awardPointForEvaluationCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update AwardPointForEvaluationCommand partially : {}, {}", id, awardPointForEvaluationCommand);
        if (awardPointForEvaluationCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, awardPointForEvaluationCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return awardPointForEvaluationCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<AwardPointForEvaluationCommand> result = awardPointForEvaluationCommandService.partialUpdate(
                    awardPointForEvaluationCommand
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
     * {@code GET  /award-point-for-evaluation-commands} : get all the awardPointForEvaluationCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of awardPointForEvaluationCommands in body.
     */
    @GetMapping("/award-point-for-evaluation-commands")
    public Mono<List<AwardPointForEvaluationCommand>> getAllAwardPointForEvaluationCommands() {
        log.debug("REST request to get all AwardPointForEvaluationCommands");
        return awardPointForEvaluationCommandService.findAll().collectList();
    }

    /**
     * {@code GET  /award-point-for-evaluation-commands} : get all the awardPointForEvaluationCommands as a stream.
     * @return the {@link Flux} of awardPointForEvaluationCommands.
     */
    @GetMapping(value = "/award-point-for-evaluation-commands", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<AwardPointForEvaluationCommand> getAllAwardPointForEvaluationCommandsAsStream() {
        log.debug("REST request to get all AwardPointForEvaluationCommands as a stream");
        return awardPointForEvaluationCommandService.findAll();
    }

    /**
     * {@code GET  /award-point-for-evaluation-commands/:id} : get the "id" awardPointForEvaluationCommand.
     *
     * @param id the id of the awardPointForEvaluationCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the awardPointForEvaluationCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/award-point-for-evaluation-commands/{id}")
    public Mono<ResponseEntity<AwardPointForEvaluationCommand>> getAwardPointForEvaluationCommand(@PathVariable Long id) {
        log.debug("REST request to get AwardPointForEvaluationCommand : {}", id);
        Mono<AwardPointForEvaluationCommand> awardPointForEvaluationCommand = awardPointForEvaluationCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(awardPointForEvaluationCommand);
    }

    /**
     * {@code DELETE  /award-point-for-evaluation-commands/:id} : delete the "id" awardPointForEvaluationCommand.
     *
     * @param id the id of the awardPointForEvaluationCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/award-point-for-evaluation-commands/{id}")
    public Mono<ResponseEntity<Void>> deleteAwardPointForEvaluationCommand(@PathVariable Long id) {
        log.debug("REST request to delete AwardPointForEvaluationCommand : {}", id);
        return awardPointForEvaluationCommandService
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
