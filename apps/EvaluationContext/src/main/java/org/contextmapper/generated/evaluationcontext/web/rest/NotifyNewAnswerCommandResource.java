package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.domain.NotifyNewAnswerCommand;
import org.contextmapper.generated.evaluationcontext.repository.NotifyNewAnswerCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.NotifyNewAnswerCommandService;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.NotifyNewAnswerCommand}.
 */
@RestController
@RequestMapping("/api")
public class NotifyNewAnswerCommandResource {

    private final Logger log = LoggerFactory.getLogger(NotifyNewAnswerCommandResource.class);

    private static final String ENTITY_NAME = "evaluationContextNotifyNewAnswerCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotifyNewAnswerCommandService notifyNewAnswerCommandService;

    private final NotifyNewAnswerCommandRepository notifyNewAnswerCommandRepository;

    public NotifyNewAnswerCommandResource(
        NotifyNewAnswerCommandService notifyNewAnswerCommandService,
        NotifyNewAnswerCommandRepository notifyNewAnswerCommandRepository
    ) {
        this.notifyNewAnswerCommandService = notifyNewAnswerCommandService;
        this.notifyNewAnswerCommandRepository = notifyNewAnswerCommandRepository;
    }

    /**
     * {@code POST  /notify-new-answer-commands} : Create a new notifyNewAnswerCommand.
     *
     * @param notifyNewAnswerCommand the notifyNewAnswerCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notifyNewAnswerCommand, or with status {@code 400 (Bad Request)} if the notifyNewAnswerCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notify-new-answer-commands")
    public ResponseEntity<NotifyNewAnswerCommand> createNotifyNewAnswerCommand(@RequestBody NotifyNewAnswerCommand notifyNewAnswerCommand)
        throws URISyntaxException {
        log.debug("REST request to save NotifyNewAnswerCommand : {}", notifyNewAnswerCommand);
        if (notifyNewAnswerCommand.getId() != null) {
            throw new BadRequestAlertException("A new notifyNewAnswerCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotifyNewAnswerCommand result = notifyNewAnswerCommandService.save(notifyNewAnswerCommand);
        return ResponseEntity
            .created(new URI("/api/notify-new-answer-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notify-new-answer-commands/:id} : Updates an existing notifyNewAnswerCommand.
     *
     * @param id the id of the notifyNewAnswerCommand to save.
     * @param notifyNewAnswerCommand the notifyNewAnswerCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notifyNewAnswerCommand,
     * or with status {@code 400 (Bad Request)} if the notifyNewAnswerCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notifyNewAnswerCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notify-new-answer-commands/{id}")
    public ResponseEntity<NotifyNewAnswerCommand> updateNotifyNewAnswerCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NotifyNewAnswerCommand notifyNewAnswerCommand
    ) throws URISyntaxException {
        log.debug("REST request to update NotifyNewAnswerCommand : {}, {}", id, notifyNewAnswerCommand);
        if (notifyNewAnswerCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, notifyNewAnswerCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!notifyNewAnswerCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NotifyNewAnswerCommand result = notifyNewAnswerCommandService.update(notifyNewAnswerCommand);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notifyNewAnswerCommand.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /notify-new-answer-commands/:id} : Partial updates given fields of an existing notifyNewAnswerCommand, field will ignore if it is null
     *
     * @param id the id of the notifyNewAnswerCommand to save.
     * @param notifyNewAnswerCommand the notifyNewAnswerCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notifyNewAnswerCommand,
     * or with status {@code 400 (Bad Request)} if the notifyNewAnswerCommand is not valid,
     * or with status {@code 404 (Not Found)} if the notifyNewAnswerCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the notifyNewAnswerCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/notify-new-answer-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NotifyNewAnswerCommand> partialUpdateNotifyNewAnswerCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NotifyNewAnswerCommand notifyNewAnswerCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update NotifyNewAnswerCommand partially : {}, {}", id, notifyNewAnswerCommand);
        if (notifyNewAnswerCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, notifyNewAnswerCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!notifyNewAnswerCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NotifyNewAnswerCommand> result = notifyNewAnswerCommandService.partialUpdate(notifyNewAnswerCommand);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notifyNewAnswerCommand.getId().toString())
        );
    }

    /**
     * {@code GET  /notify-new-answer-commands} : get all the notifyNewAnswerCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notifyNewAnswerCommands in body.
     */
    @GetMapping("/notify-new-answer-commands")
    public List<NotifyNewAnswerCommand> getAllNotifyNewAnswerCommands() {
        log.debug("REST request to get all NotifyNewAnswerCommands");
        return notifyNewAnswerCommandService.findAll();
    }

    /**
     * {@code GET  /notify-new-answer-commands/:id} : get the "id" notifyNewAnswerCommand.
     *
     * @param id the id of the notifyNewAnswerCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notifyNewAnswerCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notify-new-answer-commands/{id}")
    public ResponseEntity<NotifyNewAnswerCommand> getNotifyNewAnswerCommand(@PathVariable Long id) {
        log.debug("REST request to get NotifyNewAnswerCommand : {}", id);
        Optional<NotifyNewAnswerCommand> notifyNewAnswerCommand = notifyNewAnswerCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notifyNewAnswerCommand);
    }

    /**
     * {@code DELETE  /notify-new-answer-commands/:id} : delete the "id" notifyNewAnswerCommand.
     *
     * @param id the id of the notifyNewAnswerCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notify-new-answer-commands/{id}")
    public ResponseEntity<Void> deleteNotifyNewAnswerCommand(@PathVariable Long id) {
        log.debug("REST request to delete NotifyNewAnswerCommand : {}", id);
        notifyNewAnswerCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
