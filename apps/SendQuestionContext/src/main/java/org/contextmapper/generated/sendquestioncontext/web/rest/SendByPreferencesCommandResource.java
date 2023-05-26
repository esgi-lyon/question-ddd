package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.domain.SendByPreferencesCommand;
import org.contextmapper.generated.sendquestioncontext.repository.SendByPreferencesCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.SendByPreferencesCommandService;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.SendByPreferencesCommand}.
 */
@RestController
@RequestMapping("/api")
public class SendByPreferencesCommandResource {

    private final Logger log = LoggerFactory.getLogger(SendByPreferencesCommandResource.class);

    private static final String ENTITY_NAME = "sendQuestionContextSendByPreferencesCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SendByPreferencesCommandService sendByPreferencesCommandService;

    private final SendByPreferencesCommandRepository sendByPreferencesCommandRepository;

    public SendByPreferencesCommandResource(
        SendByPreferencesCommandService sendByPreferencesCommandService,
        SendByPreferencesCommandRepository sendByPreferencesCommandRepository
    ) {
        this.sendByPreferencesCommandService = sendByPreferencesCommandService;
        this.sendByPreferencesCommandRepository = sendByPreferencesCommandRepository;
    }

    /**
     * {@code POST  /send-by-preferences-commands} : Create a new sendByPreferencesCommand.
     *
     * @param sendByPreferencesCommand the sendByPreferencesCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sendByPreferencesCommand, or with status {@code 400 (Bad Request)} if the sendByPreferencesCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/send-by-preferences-commands")
    public ResponseEntity<SendByPreferencesCommand> createSendByPreferencesCommand(
        @RequestBody SendByPreferencesCommand sendByPreferencesCommand
    ) throws URISyntaxException {
        log.debug("REST request to save SendByPreferencesCommand : {}", sendByPreferencesCommand);
        if (sendByPreferencesCommand.getId() != null) {
            throw new BadRequestAlertException("A new sendByPreferencesCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SendByPreferencesCommand result = sendByPreferencesCommandService.save(sendByPreferencesCommand);
        return ResponseEntity
            .created(new URI("/api/send-by-preferences-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /send-by-preferences-commands/:id} : Updates an existing sendByPreferencesCommand.
     *
     * @param id the id of the sendByPreferencesCommand to save.
     * @param sendByPreferencesCommand the sendByPreferencesCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sendByPreferencesCommand,
     * or with status {@code 400 (Bad Request)} if the sendByPreferencesCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sendByPreferencesCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/send-by-preferences-commands/{id}")
    public ResponseEntity<SendByPreferencesCommand> updateSendByPreferencesCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SendByPreferencesCommand sendByPreferencesCommand
    ) throws URISyntaxException {
        log.debug("REST request to update SendByPreferencesCommand : {}, {}", id, sendByPreferencesCommand);
        if (sendByPreferencesCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sendByPreferencesCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sendByPreferencesCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SendByPreferencesCommand result = sendByPreferencesCommandService.update(sendByPreferencesCommand);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sendByPreferencesCommand.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /send-by-preferences-commands/:id} : Partial updates given fields of an existing sendByPreferencesCommand, field will ignore if it is null
     *
     * @param id the id of the sendByPreferencesCommand to save.
     * @param sendByPreferencesCommand the sendByPreferencesCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sendByPreferencesCommand,
     * or with status {@code 400 (Bad Request)} if the sendByPreferencesCommand is not valid,
     * or with status {@code 404 (Not Found)} if the sendByPreferencesCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the sendByPreferencesCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/send-by-preferences-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SendByPreferencesCommand> partialUpdateSendByPreferencesCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SendByPreferencesCommand sendByPreferencesCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update SendByPreferencesCommand partially : {}, {}", id, sendByPreferencesCommand);
        if (sendByPreferencesCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sendByPreferencesCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sendByPreferencesCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SendByPreferencesCommand> result = sendByPreferencesCommandService.partialUpdate(sendByPreferencesCommand);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sendByPreferencesCommand.getId().toString())
        );
    }

    /**
     * {@code GET  /send-by-preferences-commands} : get all the sendByPreferencesCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sendByPreferencesCommands in body.
     */
    @GetMapping("/send-by-preferences-commands")
    public List<SendByPreferencesCommand> getAllSendByPreferencesCommands() {
        log.debug("REST request to get all SendByPreferencesCommands");
        return sendByPreferencesCommandService.findAll();
    }

    /**
     * {@code GET  /send-by-preferences-commands/:id} : get the "id" sendByPreferencesCommand.
     *
     * @param id the id of the sendByPreferencesCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sendByPreferencesCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/send-by-preferences-commands/{id}")
    public ResponseEntity<SendByPreferencesCommand> getSendByPreferencesCommand(@PathVariable Long id) {
        log.debug("REST request to get SendByPreferencesCommand : {}", id);
        Optional<SendByPreferencesCommand> sendByPreferencesCommand = sendByPreferencesCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sendByPreferencesCommand);
    }

    /**
     * {@code DELETE  /send-by-preferences-commands/:id} : delete the "id" sendByPreferencesCommand.
     *
     * @param id the id of the sendByPreferencesCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/send-by-preferences-commands/{id}")
    public ResponseEntity<Void> deleteSendByPreferencesCommand(@PathVariable Long id) {
        log.debug("REST request to delete SendByPreferencesCommand : {}", id);
        sendByPreferencesCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
