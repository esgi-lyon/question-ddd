package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionCommand;
import org.contextmapper.generated.sendquestioncontext.repository.PrepareQuestionCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.PrepareQuestionCommandService;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionCommand}.
 */
@RestController
@RequestMapping("/api")
public class PrepareQuestionCommandResource {

    private final Logger log = LoggerFactory.getLogger(PrepareQuestionCommandResource.class);

    private static final String ENTITY_NAME = "sendQuestionContextPrepareQuestionCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrepareQuestionCommandService prepareQuestionCommandService;

    private final PrepareQuestionCommandRepository prepareQuestionCommandRepository;

    public PrepareQuestionCommandResource(
        PrepareQuestionCommandService prepareQuestionCommandService,
        PrepareQuestionCommandRepository prepareQuestionCommandRepository
    ) {
        this.prepareQuestionCommandService = prepareQuestionCommandService;
        this.prepareQuestionCommandRepository = prepareQuestionCommandRepository;
    }

    /**
     * {@code POST  /prepare-question-commands} : Create a new prepareQuestionCommand.
     *
     * @param prepareQuestionCommand the prepareQuestionCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new prepareQuestionCommand, or with status {@code 400 (Bad Request)} if the prepareQuestionCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prepare-question-commands")
    public ResponseEntity<PrepareQuestionCommand> createPrepareQuestionCommand(@RequestBody PrepareQuestionCommand prepareQuestionCommand)
        throws URISyntaxException {
        log.debug("REST request to save PrepareQuestionCommand : {}", prepareQuestionCommand);
        if (prepareQuestionCommand.getId() != null) {
            throw new BadRequestAlertException("A new prepareQuestionCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrepareQuestionCommand result = prepareQuestionCommandService.save(prepareQuestionCommand);
        return ResponseEntity
            .created(new URI("/api/prepare-question-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prepare-question-commands/:id} : Updates an existing prepareQuestionCommand.
     *
     * @param id the id of the prepareQuestionCommand to save.
     * @param prepareQuestionCommand the prepareQuestionCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prepareQuestionCommand,
     * or with status {@code 400 (Bad Request)} if the prepareQuestionCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the prepareQuestionCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prepare-question-commands/{id}")
    public ResponseEntity<PrepareQuestionCommand> updatePrepareQuestionCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PrepareQuestionCommand prepareQuestionCommand
    ) throws URISyntaxException {
        log.debug("REST request to update PrepareQuestionCommand : {}, {}", id, prepareQuestionCommand);
        if (prepareQuestionCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, prepareQuestionCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!prepareQuestionCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PrepareQuestionCommand result = prepareQuestionCommandService.update(prepareQuestionCommand);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, prepareQuestionCommand.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /prepare-question-commands/:id} : Partial updates given fields of an existing prepareQuestionCommand, field will ignore if it is null
     *
     * @param id the id of the prepareQuestionCommand to save.
     * @param prepareQuestionCommand the prepareQuestionCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prepareQuestionCommand,
     * or with status {@code 400 (Bad Request)} if the prepareQuestionCommand is not valid,
     * or with status {@code 404 (Not Found)} if the prepareQuestionCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the prepareQuestionCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/prepare-question-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PrepareQuestionCommand> partialUpdatePrepareQuestionCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PrepareQuestionCommand prepareQuestionCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update PrepareQuestionCommand partially : {}, {}", id, prepareQuestionCommand);
        if (prepareQuestionCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, prepareQuestionCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!prepareQuestionCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PrepareQuestionCommand> result = prepareQuestionCommandService.partialUpdate(prepareQuestionCommand);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, prepareQuestionCommand.getId().toString())
        );
    }

    /**
     * {@code GET  /prepare-question-commands} : get all the prepareQuestionCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of prepareQuestionCommands in body.
     */
    @GetMapping("/prepare-question-commands")
    public List<PrepareQuestionCommand> getAllPrepareQuestionCommands() {
        log.debug("REST request to get all PrepareQuestionCommands");
        return prepareQuestionCommandService.findAll();
    }

    /**
     * {@code GET  /prepare-question-commands/:id} : get the "id" prepareQuestionCommand.
     *
     * @param id the id of the prepareQuestionCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the prepareQuestionCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prepare-question-commands/{id}")
    public ResponseEntity<PrepareQuestionCommand> getPrepareQuestionCommand(@PathVariable Long id) {
        log.debug("REST request to get PrepareQuestionCommand : {}", id);
        Optional<PrepareQuestionCommand> prepareQuestionCommand = prepareQuestionCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(prepareQuestionCommand);
    }

    /**
     * {@code DELETE  /prepare-question-commands/:id} : delete the "id" prepareQuestionCommand.
     *
     * @param id the id of the prepareQuestionCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prepare-question-commands/{id}")
    public ResponseEntity<Void> deletePrepareQuestionCommand(@PathVariable Long id) {
        log.debug("REST request to delete PrepareQuestionCommand : {}", id);
        prepareQuestionCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
