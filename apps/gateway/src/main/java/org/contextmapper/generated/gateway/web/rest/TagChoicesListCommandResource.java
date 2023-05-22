package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.domain.TagChoicesListCommand;
import org.contextmapper.generated.gateway.repository.TagChoicesListCommandRepository;
import org.contextmapper.generated.gateway.service.TagChoicesListCommandService;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.TagChoicesListCommand}.
 */
@RestController
@RequestMapping("/api")
public class TagChoicesListCommandResource {

    private final Logger log = LoggerFactory.getLogger(TagChoicesListCommandResource.class);

    private static final String ENTITY_NAME = "tagChoicesListCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TagChoicesListCommandService tagChoicesListCommandService;

    private final TagChoicesListCommandRepository tagChoicesListCommandRepository;

    public TagChoicesListCommandResource(
        TagChoicesListCommandService tagChoicesListCommandService,
        TagChoicesListCommandRepository tagChoicesListCommandRepository
    ) {
        this.tagChoicesListCommandService = tagChoicesListCommandService;
        this.tagChoicesListCommandRepository = tagChoicesListCommandRepository;
    }

    /**
     * {@code POST  /tag-choices-list-commands} : Create a new tagChoicesListCommand.
     *
     * @param tagChoicesListCommand the tagChoicesListCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tagChoicesListCommand, or with status {@code 400 (Bad Request)} if the tagChoicesListCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tag-choices-list-commands")
    public Mono<ResponseEntity<TagChoicesListCommand>> createTagChoicesListCommand(
        @RequestBody TagChoicesListCommand tagChoicesListCommand
    ) throws URISyntaxException {
        log.debug("REST request to save TagChoicesListCommand : {}", tagChoicesListCommand);
        if (tagChoicesListCommand.getId() != null) {
            throw new BadRequestAlertException("A new tagChoicesListCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return tagChoicesListCommandService
            .save(tagChoicesListCommand)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/tag-choices-list-commands/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /tag-choices-list-commands/:id} : Updates an existing tagChoicesListCommand.
     *
     * @param id the id of the tagChoicesListCommand to save.
     * @param tagChoicesListCommand the tagChoicesListCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagChoicesListCommand,
     * or with status {@code 400 (Bad Request)} if the tagChoicesListCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tagChoicesListCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tag-choices-list-commands/{id}")
    public Mono<ResponseEntity<TagChoicesListCommand>> updateTagChoicesListCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TagChoicesListCommand tagChoicesListCommand
    ) throws URISyntaxException {
        log.debug("REST request to update TagChoicesListCommand : {}, {}", id, tagChoicesListCommand);
        if (tagChoicesListCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tagChoicesListCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return tagChoicesListCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return tagChoicesListCommandService
                    .update(tagChoicesListCommand)
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
     * {@code PATCH  /tag-choices-list-commands/:id} : Partial updates given fields of an existing tagChoicesListCommand, field will ignore if it is null
     *
     * @param id the id of the tagChoicesListCommand to save.
     * @param tagChoicesListCommand the tagChoicesListCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagChoicesListCommand,
     * or with status {@code 400 (Bad Request)} if the tagChoicesListCommand is not valid,
     * or with status {@code 404 (Not Found)} if the tagChoicesListCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the tagChoicesListCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tag-choices-list-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<TagChoicesListCommand>> partialUpdateTagChoicesListCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TagChoicesListCommand tagChoicesListCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update TagChoicesListCommand partially : {}, {}", id, tagChoicesListCommand);
        if (tagChoicesListCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tagChoicesListCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return tagChoicesListCommandRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<TagChoicesListCommand> result = tagChoicesListCommandService.partialUpdate(tagChoicesListCommand);

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
     * {@code GET  /tag-choices-list-commands} : get all the tagChoicesListCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagChoicesListCommands in body.
     */
    @GetMapping("/tag-choices-list-commands")
    public Mono<List<TagChoicesListCommand>> getAllTagChoicesListCommands() {
        log.debug("REST request to get all TagChoicesListCommands");
        return tagChoicesListCommandService.findAll().collectList();
    }

    /**
     * {@code GET  /tag-choices-list-commands} : get all the tagChoicesListCommands as a stream.
     * @return the {@link Flux} of tagChoicesListCommands.
     */
    @GetMapping(value = "/tag-choices-list-commands", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<TagChoicesListCommand> getAllTagChoicesListCommandsAsStream() {
        log.debug("REST request to get all TagChoicesListCommands as a stream");
        return tagChoicesListCommandService.findAll();
    }

    /**
     * {@code GET  /tag-choices-list-commands/:id} : get the "id" tagChoicesListCommand.
     *
     * @param id the id of the tagChoicesListCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagChoicesListCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-choices-list-commands/{id}")
    public Mono<ResponseEntity<TagChoicesListCommand>> getTagChoicesListCommand(@PathVariable Long id) {
        log.debug("REST request to get TagChoicesListCommand : {}", id);
        Mono<TagChoicesListCommand> tagChoicesListCommand = tagChoicesListCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagChoicesListCommand);
    }

    /**
     * {@code DELETE  /tag-choices-list-commands/:id} : delete the "id" tagChoicesListCommand.
     *
     * @param id the id of the tagChoicesListCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tag-choices-list-commands/{id}")
    public Mono<ResponseEntity<Void>> deleteTagChoicesListCommand(@PathVariable Long id) {
        log.debug("REST request to delete TagChoicesListCommand : {}", id);
        return tagChoicesListCommandService
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
