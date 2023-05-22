package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.domain.CreateResourceCommand;
import org.contextmapper.generated.questioncontext.repository.CreateResourceCommandRepository;
import org.contextmapper.generated.questioncontext.service.CreateResourceCommandService;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.CreateResourceCommand}.
 */
@RestController
@RequestMapping("/api")
public class CreateResourceCommandResource {

    private final Logger log = LoggerFactory.getLogger(CreateResourceCommandResource.class);

    private static final String ENTITY_NAME = "questionContextCreateResourceCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreateResourceCommandService createResourceCommandService;

    private final CreateResourceCommandRepository createResourceCommandRepository;

    public CreateResourceCommandResource(
        CreateResourceCommandService createResourceCommandService,
        CreateResourceCommandRepository createResourceCommandRepository
    ) {
        this.createResourceCommandService = createResourceCommandService;
        this.createResourceCommandRepository = createResourceCommandRepository;
    }

    /**
     * {@code POST  /create-resource-commands} : Create a new createResourceCommand.
     *
     * @param createResourceCommand the createResourceCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createResourceCommand, or with status {@code 400 (Bad Request)} if the createResourceCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-resource-commands")
    public ResponseEntity<CreateResourceCommand> createCreateResourceCommand(@RequestBody CreateResourceCommand createResourceCommand)
        throws URISyntaxException {
        log.debug("REST request to save CreateResourceCommand : {}", createResourceCommand);
        if (createResourceCommand.getId() != null) {
            throw new BadRequestAlertException("A new createResourceCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreateResourceCommand result = createResourceCommandService.save(createResourceCommand);
        return ResponseEntity
            .created(new URI("/api/create-resource-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /create-resource-commands/:id} : Updates an existing createResourceCommand.
     *
     * @param id the id of the createResourceCommand to save.
     * @param createResourceCommand the createResourceCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createResourceCommand,
     * or with status {@code 400 (Bad Request)} if the createResourceCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the createResourceCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/create-resource-commands/{id}")
    public ResponseEntity<CreateResourceCommand> updateCreateResourceCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateResourceCommand createResourceCommand
    ) throws URISyntaxException {
        log.debug("REST request to update CreateResourceCommand : {}, {}", id, createResourceCommand);
        if (createResourceCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createResourceCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createResourceCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CreateResourceCommand result = createResourceCommandService.update(createResourceCommand);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createResourceCommand.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /create-resource-commands/:id} : Partial updates given fields of an existing createResourceCommand, field will ignore if it is null
     *
     * @param id the id of the createResourceCommand to save.
     * @param createResourceCommand the createResourceCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createResourceCommand,
     * or with status {@code 400 (Bad Request)} if the createResourceCommand is not valid,
     * or with status {@code 404 (Not Found)} if the createResourceCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the createResourceCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/create-resource-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CreateResourceCommand> partialUpdateCreateResourceCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateResourceCommand createResourceCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreateResourceCommand partially : {}, {}", id, createResourceCommand);
        if (createResourceCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createResourceCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createResourceCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CreateResourceCommand> result = createResourceCommandService.partialUpdate(createResourceCommand);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createResourceCommand.getId().toString())
        );
    }

    /**
     * {@code GET  /create-resource-commands} : get all the createResourceCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createResourceCommands in body.
     */
    @GetMapping("/create-resource-commands")
    public List<CreateResourceCommand> getAllCreateResourceCommands() {
        log.debug("REST request to get all CreateResourceCommands");
        return createResourceCommandService.findAll();
    }

    /**
     * {@code GET  /create-resource-commands/:id} : get the "id" createResourceCommand.
     *
     * @param id the id of the createResourceCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createResourceCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/create-resource-commands/{id}")
    public ResponseEntity<CreateResourceCommand> getCreateResourceCommand(@PathVariable Long id) {
        log.debug("REST request to get CreateResourceCommand : {}", id);
        Optional<CreateResourceCommand> createResourceCommand = createResourceCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createResourceCommand);
    }

    /**
     * {@code DELETE  /create-resource-commands/:id} : delete the "id" createResourceCommand.
     *
     * @param id the id of the createResourceCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/create-resource-commands/{id}")
    public ResponseEntity<Void> deleteCreateResourceCommand(@PathVariable Long id) {
        log.debug("REST request to delete CreateResourceCommand : {}", id);
        createResourceCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
