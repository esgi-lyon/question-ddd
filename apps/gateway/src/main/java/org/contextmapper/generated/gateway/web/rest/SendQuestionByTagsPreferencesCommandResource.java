package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.domain.SendQuestionByTagsPreferencesCommand;
import org.contextmapper.generated.gateway.repository.SendQuestionByTagsPreferencesCommandRepository;
import org.contextmapper.generated.gateway.service.SendQuestionByTagsPreferencesCommandService;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.SendQuestionByTagsPreferencesCommand}.
 */
@RestController
@RequestMapping("/api")
public class SendQuestionByTagsPreferencesCommandResource {

    private final Logger log = LoggerFactory.getLogger(SendQuestionByTagsPreferencesCommandResource.class);

    private static final String ENTITY_NAME = "sendQuestionByTagsPreferencesCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SendQuestionByTagsPreferencesCommandService sendQuestionByTagsPreferencesCommandService;

    private final SendQuestionByTagsPreferencesCommandRepository sendQuestionByTagsPreferencesCommandRepository;

    public SendQuestionByTagsPreferencesCommandResource(
        SendQuestionByTagsPreferencesCommandService sendQuestionByTagsPreferencesCommandService,
        SendQuestionByTagsPreferencesCommandRepository sendQuestionByTagsPreferencesCommandRepository
    ) {
        this.sendQuestionByTagsPreferencesCommandService = sendQuestionByTagsPreferencesCommandService;
        this.sendQuestionByTagsPreferencesCommandRepository = sendQuestionByTagsPreferencesCommandRepository;
    }

    /**
     * {@code POST  /send-question-by-tags-preferences-commands} : Create a new sendQuestionByTagsPreferencesCommand.
     *
     * @param sendQuestionByTagsPreferencesCommand the sendQuestionByTagsPreferencesCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sendQuestionByTagsPreferencesCommand, or with status {@code 400 (Bad Request)} if the sendQuestionByTagsPreferencesCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/send-question-by-tags-preferences-commands")
    public Mono<ResponseEntity<SendQuestionByTagsPreferencesCommand>> createSendQuestionByTagsPreferencesCommand(
        @RequestBody SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand
    ) throws URISyntaxException {
        log.debug("REST request to save SendQuestionByTagsPreferencesCommand : {}", sendQuestionByTagsPreferencesCommand);
        if (sendQuestionByTagsPreferencesCommand.getId() != null) {
            throw new BadRequestAlertException(
                "A new sendQuestionByTagsPreferencesCommand cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        return sendQuestionByTagsPreferencesCommandService
            .save(sendQuestionByTagsPreferencesCommand)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/send-question-by-tags-preferences-commands/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /send-question-by-tags-preferences-commands/:id} : Updates an existing sendQuestionByTagsPreferencesCommand.
     *
     * @param id the id of the sendQuestionByTagsPreferencesCommand to save.
     * @param sendQuestionByTagsPreferencesCommand the sendQuestionByTagsPreferencesCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sendQuestionByTagsPreferencesCommand,
     * or with status {@code 400 (Bad Request)} if the sendQuestionByTagsPreferencesCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sendQuestionByTagsPreferencesCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/send-question-by-tags-preferences-commands/{id}")
    public Mono<ResponseEntity<SendQuestionByTagsPreferencesCommand>> updateSendQuestionByTagsPreferencesCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand
    ) throws URISyntaxException {
        log.debug("REST request to update SendQuestionByTagsPreferencesCommand : {}, {}", id, sendQuestionByTagsPreferencesCommand);
        if (sendQuestionByTagsPreferencesCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sendQuestionByTagsPreferencesCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return sendQuestionByTagsPreferencesCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return sendQuestionByTagsPreferencesCommandService
                    .update(sendQuestionByTagsPreferencesCommand)
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
     * {@code PATCH  /send-question-by-tags-preferences-commands/:id} : Partial updates given fields of an existing sendQuestionByTagsPreferencesCommand, field will ignore if it is null
     *
     * @param id the id of the sendQuestionByTagsPreferencesCommand to save.
     * @param sendQuestionByTagsPreferencesCommand the sendQuestionByTagsPreferencesCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sendQuestionByTagsPreferencesCommand,
     * or with status {@code 400 (Bad Request)} if the sendQuestionByTagsPreferencesCommand is not valid,
     * or with status {@code 404 (Not Found)} if the sendQuestionByTagsPreferencesCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the sendQuestionByTagsPreferencesCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(
        value = "/send-question-by-tags-preferences-commands/{id}",
        consumes = { "application/json", "application/merge-patch+json" }
    )
    public Mono<ResponseEntity<SendQuestionByTagsPreferencesCommand>> partialUpdateSendQuestionByTagsPreferencesCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SendQuestionByTagsPreferencesCommand sendQuestionByTagsPreferencesCommand
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update SendQuestionByTagsPreferencesCommand partially : {}, {}",
            id,
            sendQuestionByTagsPreferencesCommand
        );
        if (sendQuestionByTagsPreferencesCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sendQuestionByTagsPreferencesCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return sendQuestionByTagsPreferencesCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SendQuestionByTagsPreferencesCommand> result = sendQuestionByTagsPreferencesCommandService.partialUpdate(
                    sendQuestionByTagsPreferencesCommand
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
     * {@code GET  /send-question-by-tags-preferences-commands} : get all the sendQuestionByTagsPreferencesCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sendQuestionByTagsPreferencesCommands in body.
     */
    @GetMapping("/send-question-by-tags-preferences-commands")
    public Mono<List<SendQuestionByTagsPreferencesCommand>> getAllSendQuestionByTagsPreferencesCommands() {
        log.debug("REST request to get all SendQuestionByTagsPreferencesCommands");
        return sendQuestionByTagsPreferencesCommandService.findAll().collectList();
    }

    /**
     * {@code GET  /send-question-by-tags-preferences-commands} : get all the sendQuestionByTagsPreferencesCommands as a stream.
     * @return the {@link Flux} of sendQuestionByTagsPreferencesCommands.
     */
    @GetMapping(value = "/send-question-by-tags-preferences-commands", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<SendQuestionByTagsPreferencesCommand> getAllSendQuestionByTagsPreferencesCommandsAsStream() {
        log.debug("REST request to get all SendQuestionByTagsPreferencesCommands as a stream");
        return sendQuestionByTagsPreferencesCommandService.findAll();
    }

    /**
     * {@code GET  /send-question-by-tags-preferences-commands/:id} : get the "id" sendQuestionByTagsPreferencesCommand.
     *
     * @param id the id of the sendQuestionByTagsPreferencesCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sendQuestionByTagsPreferencesCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/send-question-by-tags-preferences-commands/{id}")
    public Mono<ResponseEntity<SendQuestionByTagsPreferencesCommand>> getSendQuestionByTagsPreferencesCommand(@PathVariable Long id) {
        log.debug("REST request to get SendQuestionByTagsPreferencesCommand : {}", id);
        Mono<SendQuestionByTagsPreferencesCommand> sendQuestionByTagsPreferencesCommand = sendQuestionByTagsPreferencesCommandService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(sendQuestionByTagsPreferencesCommand);
    }

    /**
     * {@code DELETE  /send-question-by-tags-preferences-commands/:id} : delete the "id" sendQuestionByTagsPreferencesCommand.
     *
     * @param id the id of the sendQuestionByTagsPreferencesCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/send-question-by-tags-preferences-commands/{id}")
    public Mono<ResponseEntity<Void>> deleteSendQuestionByTagsPreferencesCommand(@PathVariable Long id) {
        log.debug("REST request to delete SendQuestionByTagsPreferencesCommand : {}", id);
        return sendQuestionByTagsPreferencesCommandService
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
