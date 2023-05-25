package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionsCommand;
import org.contextmapper.generated.sendquestioncontext.repository.PrepareQuestionsCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.PrepareQuestionsCommandService;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionsCommand}.
 */
@RestController
@RequestMapping("/api")
public class PrepareQuestionsCommandResource {

    private final Logger log = LoggerFactory.getLogger(CommandHandlers.class);

    private static final String ENTITY_NAME = "sendQuestionContextPrepareQuestionsCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrepareQuestionsCommandService prepareQuestionsCommandService;

    private final PrepareQuestionsCommandRepository prepareQuestionsCommandRepository;

    public PrepareQuestionsCommandResource(
        PrepareQuestionsCommandService prepareQuestionsCommandService,
        PrepareQuestionsCommandRepository prepareQuestionsCommandRepository
    ) {
        this.prepareQuestionsCommandService = prepareQuestionsCommandService;
        this.prepareQuestionsCommandRepository = prepareQuestionsCommandRepository;
    }

    /**
     * {@code POST  /prepare-questions-commands} : Create a new prepareQuestionsCommand.
     *
     * @param prepareQuestionsCommand the prepareQuestionsCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new prepareQuestionsCommand, or with status {@code 400 (Bad Request)} if the prepareQuestionsCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prepare-questions-commands")
    public ResponseEntity<PrepareQuestionsCommand> createPrepareQuestionsCommand(
        @RequestBody PrepareQuestionsCommand prepareQuestionsCommand
    ) throws URISyntaxException {
        log.debug("REST request to save PrepareQuestionsCommand : {}", prepareQuestionsCommand);
        if (prepareQuestionsCommand.getId() != null) {
            throw new BadRequestAlertException("A new prepareQuestionsCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrepareQuestionsCommand result = prepareQuestionsCommandService.save(prepareQuestionsCommand);
        return ResponseEntity
            .created(new URI("/api/prepare-questions-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prepare-questions-commands/:id} : Updates an existing prepareQuestionsCommand.
     *
     * @param id the id of the prepareQuestionsCommand to save.
     * @param prepareQuestionsCommand the prepareQuestionsCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prepareQuestionsCommand,
     * or with status {@code 400 (Bad Request)} if the prepareQuestionsCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the prepareQuestionsCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prepare-questions-commands/{id}")
    public ResponseEntity<PrepareQuestionsCommand> updatePrepareQuestionsCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PrepareQuestionsCommand prepareQuestionsCommand
    ) throws URISyntaxException {
        log.debug("REST request to update PrepareQuestionsCommand : {}, {}", id, prepareQuestionsCommand);
        if (prepareQuestionsCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, prepareQuestionsCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!prepareQuestionsCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PrepareQuestionsCommand result = prepareQuestionsCommandService.update(prepareQuestionsCommand);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, prepareQuestionsCommand.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /prepare-questions-commands/:id} : Partial updates given fields of an existing prepareQuestionsCommand, field will ignore if it is null
     *
     * @param id the id of the prepareQuestionsCommand to save.
     * @param prepareQuestionsCommand the prepareQuestionsCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prepareQuestionsCommand,
     * or with status {@code 400 (Bad Request)} if the prepareQuestionsCommand is not valid,
     * or with status {@code 404 (Not Found)} if the prepareQuestionsCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the prepareQuestionsCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/prepare-questions-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PrepareQuestionsCommand> partialUpdatePrepareQuestionsCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PrepareQuestionsCommand prepareQuestionsCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update PrepareQuestionsCommand partially : {}, {}", id, prepareQuestionsCommand);
        if (prepareQuestionsCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, prepareQuestionsCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!prepareQuestionsCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PrepareQuestionsCommand> result = prepareQuestionsCommandService.partialUpdate(prepareQuestionsCommand);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, prepareQuestionsCommand.getId().toString())
        );
    }

    /**
     * {@code GET  /prepare-questions-commands} : get all the prepareQuestionsCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of prepareQuestionsCommands in body.
     */
    @GetMapping("/prepare-questions-commands")
    public List<PrepareQuestionsCommand> getAllPrepareQuestionsCommands() {
        log.debug("REST request to get all PrepareQuestionsCommands");
        return prepareQuestionsCommandService.findAll();
    }

    /**
     * {@code GET  /prepare-questions-commands/:id} : get the "id" prepareQuestionsCommand.
     *
     * @param id the id of the prepareQuestionsCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the prepareQuestionsCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prepare-questions-commands/{id}")
    public ResponseEntity<PrepareQuestionsCommand> getPrepareQuestionsCommand(@PathVariable Long id) {
        log.debug("REST request to get PrepareQuestionsCommand : {}", id);
        Optional<PrepareQuestionsCommand> prepareQuestionsCommand = prepareQuestionsCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(prepareQuestionsCommand);
    }

    /**
     * {@code DELETE  /prepare-questions-commands/:id} : delete the "id" prepareQuestionsCommand.
     *
     * @param id the id of the prepareQuestionsCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prepare-questions-commands/{id}")
    public ResponseEntity<Void> deletePrepareQuestionsCommand(@PathVariable Long id) {
        log.debug("REST request to delete PrepareQuestionsCommand : {}", id);
        prepareQuestionsCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
