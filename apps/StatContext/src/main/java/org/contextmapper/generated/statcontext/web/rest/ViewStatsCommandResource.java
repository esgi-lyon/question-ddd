package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.domain.ViewStatsCommand;
import org.contextmapper.generated.statcontext.repository.ViewStatsCommandRepository;
import org.contextmapper.generated.statcontext.service.ViewStatsCommandService;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.ViewStatsCommand}.
 */
@RestController
@RequestMapping("/api")
public class ViewStatsCommandResource {

    private final Logger log = LoggerFactory.getLogger(ViewStatsCommandResource.class);

    private static final String ENTITY_NAME = "statContextViewStatsCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ViewStatsCommandService viewStatsCommandService;

    private final ViewStatsCommandRepository viewStatsCommandRepository;

    public ViewStatsCommandResource(
        ViewStatsCommandService viewStatsCommandService,
        ViewStatsCommandRepository viewStatsCommandRepository
    ) {
        this.viewStatsCommandService = viewStatsCommandService;
        this.viewStatsCommandRepository = viewStatsCommandRepository;
    }

    /**
     * {@code POST  /view-stats-commands} : Create a new viewStatsCommand.
     *
     * @param viewStatsCommand the viewStatsCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new viewStatsCommand, or with status {@code 400 (Bad Request)} if the viewStatsCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/view-stats-commands")
    public ResponseEntity<ViewStatsCommand> createViewStatsCommand(@RequestBody ViewStatsCommand viewStatsCommand)
        throws URISyntaxException {
        log.debug("REST request to save ViewStatsCommand : {}", viewStatsCommand);
        if (viewStatsCommand.getId() != null) {
            throw new BadRequestAlertException("A new viewStatsCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ViewStatsCommand result = viewStatsCommandService.save(viewStatsCommand);
        return ResponseEntity
            .created(new URI("/api/view-stats-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /view-stats-commands/:id} : Updates an existing viewStatsCommand.
     *
     * @param id the id of the viewStatsCommand to save.
     * @param viewStatsCommand the viewStatsCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewStatsCommand,
     * or with status {@code 400 (Bad Request)} if the viewStatsCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the viewStatsCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/view-stats-commands/{id}")
    public ResponseEntity<ViewStatsCommand> updateViewStatsCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewStatsCommand viewStatsCommand
    ) throws URISyntaxException {
        log.debug("REST request to update ViewStatsCommand : {}, {}", id, viewStatsCommand);
        if (viewStatsCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewStatsCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewStatsCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ViewStatsCommand result = viewStatsCommandService.update(viewStatsCommand);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewStatsCommand.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /view-stats-commands/:id} : Partial updates given fields of an existing viewStatsCommand, field will ignore if it is null
     *
     * @param id the id of the viewStatsCommand to save.
     * @param viewStatsCommand the viewStatsCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewStatsCommand,
     * or with status {@code 400 (Bad Request)} if the viewStatsCommand is not valid,
     * or with status {@code 404 (Not Found)} if the viewStatsCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the viewStatsCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/view-stats-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ViewStatsCommand> partialUpdateViewStatsCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewStatsCommand viewStatsCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update ViewStatsCommand partially : {}, {}", id, viewStatsCommand);
        if (viewStatsCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewStatsCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewStatsCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ViewStatsCommand> result = viewStatsCommandService.partialUpdate(viewStatsCommand);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewStatsCommand.getId().toString())
        );
    }

    /**
     * {@code GET  /view-stats-commands} : get all the viewStatsCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viewStatsCommands in body.
     */
    @GetMapping("/view-stats-commands")
    public List<ViewStatsCommand> getAllViewStatsCommands() {
        log.debug("REST request to get all ViewStatsCommands");
        return viewStatsCommandService.findAll();
    }

    /**
     * {@code GET  /view-stats-commands/:id} : get the "id" viewStatsCommand.
     *
     * @param id the id of the viewStatsCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viewStatsCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/view-stats-commands/{id}")
    public ResponseEntity<ViewStatsCommand> getViewStatsCommand(@PathVariable Long id) {
        log.debug("REST request to get ViewStatsCommand : {}", id);
        Optional<ViewStatsCommand> viewStatsCommand = viewStatsCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(viewStatsCommand);
    }

    /**
     * {@code DELETE  /view-stats-commands/:id} : delete the "id" viewStatsCommand.
     *
     * @param id the id of the viewStatsCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/view-stats-commands/{id}")
    public ResponseEntity<Void> deleteViewStatsCommand(@PathVariable Long id) {
        log.debug("REST request to delete ViewStatsCommand : {}", id);
        viewStatsCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
