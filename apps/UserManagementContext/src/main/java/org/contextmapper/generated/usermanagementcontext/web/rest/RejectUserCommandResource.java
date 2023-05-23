package org.contextmapper.generated.usermanagementcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.usermanagementcontext.domain.RejectUserCommand;
import org.contextmapper.generated.usermanagementcontext.repository.RejectUserCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.RejectUserCommandService;
import org.contextmapper.generated.usermanagementcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.usermanagementcontext.domain.RejectUserCommand}.
 */
@RestController
@RequestMapping("/api")
public class RejectUserCommandResource {

    private final Logger log = LoggerFactory.getLogger(RejectUserCommandResource.class);

    private static final String ENTITY_NAME = "userManagementContextRejectUserCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RejectUserCommandService rejectUserCommandService;

    private final RejectUserCommandRepository rejectUserCommandRepository;

    public RejectUserCommandResource(
        RejectUserCommandService rejectUserCommandService,
        RejectUserCommandRepository rejectUserCommandRepository
    ) {
        this.rejectUserCommandService = rejectUserCommandService;
        this.rejectUserCommandRepository = rejectUserCommandRepository;
    }

    /**
     * {@code POST  /reject-user-commands} : Create a new rejectUserCommand.
     *
     * @param rejectUserCommand the rejectUserCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rejectUserCommand, or with status {@code 400 (Bad Request)} if the rejectUserCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reject-user-commands")
    public ResponseEntity<RejectUserCommand> createRejectUserCommand(@RequestBody RejectUserCommand rejectUserCommand)
        throws URISyntaxException {
        log.debug("REST request to save RejectUserCommand : {}", rejectUserCommand);
        if (rejectUserCommand.getId() != null) {
            throw new BadRequestAlertException("A new rejectUserCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RejectUserCommand result = rejectUserCommandService.save(rejectUserCommand);
        return ResponseEntity
            .created(new URI("/api/reject-user-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reject-user-commands/:id} : Updates an existing rejectUserCommand.
     *
     * @param id the id of the rejectUserCommand to save.
     * @param rejectUserCommand the rejectUserCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rejectUserCommand,
     * or with status {@code 400 (Bad Request)} if the rejectUserCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rejectUserCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reject-user-commands/{id}")
    public ResponseEntity<RejectUserCommand> updateRejectUserCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RejectUserCommand rejectUserCommand
    ) throws URISyntaxException {
        log.debug("REST request to update RejectUserCommand : {}, {}", id, rejectUserCommand);
        if (rejectUserCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rejectUserCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rejectUserCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RejectUserCommand result = rejectUserCommandService.update(rejectUserCommand);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rejectUserCommand.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /reject-user-commands/:id} : Partial updates given fields of an existing rejectUserCommand, field will ignore if it is null
     *
     * @param id the id of the rejectUserCommand to save.
     * @param rejectUserCommand the rejectUserCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rejectUserCommand,
     * or with status {@code 400 (Bad Request)} if the rejectUserCommand is not valid,
     * or with status {@code 404 (Not Found)} if the rejectUserCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the rejectUserCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/reject-user-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RejectUserCommand> partialUpdateRejectUserCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RejectUserCommand rejectUserCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update RejectUserCommand partially : {}, {}", id, rejectUserCommand);
        if (rejectUserCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rejectUserCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rejectUserCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RejectUserCommand> result = rejectUserCommandService.partialUpdate(rejectUserCommand);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rejectUserCommand.getId().toString())
        );
    }

    /**
     * {@code GET  /reject-user-commands} : get all the rejectUserCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rejectUserCommands in body.
     */
    @GetMapping("/reject-user-commands")
    public List<RejectUserCommand> getAllRejectUserCommands() {
        log.debug("REST request to get all RejectUserCommands");
        return rejectUserCommandService.findAll();
    }

    /**
     * {@code GET  /reject-user-commands/:id} : get the "id" rejectUserCommand.
     *
     * @param id the id of the rejectUserCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rejectUserCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reject-user-commands/{id}")
    public ResponseEntity<RejectUserCommand> getRejectUserCommand(@PathVariable Long id) {
        log.debug("REST request to get RejectUserCommand : {}", id);
        Optional<RejectUserCommand> rejectUserCommand = rejectUserCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rejectUserCommand);
    }

    /**
     * {@code DELETE  /reject-user-commands/:id} : delete the "id" rejectUserCommand.
     *
     * @param id the id of the rejectUserCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reject-user-commands/{id}")
    public ResponseEntity<Void> deleteRejectUserCommand(@PathVariable Long id) {
        log.debug("REST request to delete RejectUserCommand : {}", id);
        rejectUserCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
