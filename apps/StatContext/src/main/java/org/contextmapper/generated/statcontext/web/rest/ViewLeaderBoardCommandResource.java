package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.ViewLeaderBoardCommandRepository;
import org.contextmapper.generated.statcontext.service.ViewLeaderBoardCommandService;
import org.contextmapper.generated.statcontext.service.dto.ViewLeaderBoardCommandDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.ViewLeaderBoardCommand}.
 */
@RestController
@RequestMapping("/api")
public class ViewLeaderBoardCommandResource {

    private final Logger log = LoggerFactory.getLogger(ViewLeaderBoardCommandResource.class);

    private static final String ENTITY_NAME = "statContextViewLeaderBoardCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ViewLeaderBoardCommandService viewLeaderBoardCommandService;

    private final ViewLeaderBoardCommandRepository viewLeaderBoardCommandRepository;

    public ViewLeaderBoardCommandResource(
        ViewLeaderBoardCommandService viewLeaderBoardCommandService,
        ViewLeaderBoardCommandRepository viewLeaderBoardCommandRepository
    ) {
        this.viewLeaderBoardCommandService = viewLeaderBoardCommandService;
        this.viewLeaderBoardCommandRepository = viewLeaderBoardCommandRepository;
    }

    /**
     * {@code POST  /view-leader-board-commands} : Create a new viewLeaderBoardCommand.
     *
     * @param viewLeaderBoardCommandDTO the viewLeaderBoardCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new viewLeaderBoardCommandDTO, or with status {@code 400 (Bad Request)} if the viewLeaderBoardCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/view-leader-board-commands")
    public ResponseEntity<ViewLeaderBoardCommandDTO> createViewLeaderBoardCommand(
        @RequestBody ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ViewLeaderBoardCommand : {}", viewLeaderBoardCommandDTO);
        if (viewLeaderBoardCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new viewLeaderBoardCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ViewLeaderBoardCommandDTO result = viewLeaderBoardCommandService.save(viewLeaderBoardCommandDTO);
        return ResponseEntity
            .created(new URI("/api/view-leader-board-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /view-leader-board-commands/:id} : Updates an existing viewLeaderBoardCommand.
     *
     * @param id the id of the viewLeaderBoardCommandDTO to save.
     * @param viewLeaderBoardCommandDTO the viewLeaderBoardCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewLeaderBoardCommandDTO,
     * or with status {@code 400 (Bad Request)} if the viewLeaderBoardCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the viewLeaderBoardCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/view-leader-board-commands/{id}")
    public ResponseEntity<ViewLeaderBoardCommandDTO> updateViewLeaderBoardCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ViewLeaderBoardCommand : {}, {}", id, viewLeaderBoardCommandDTO);
        if (viewLeaderBoardCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewLeaderBoardCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewLeaderBoardCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ViewLeaderBoardCommandDTO result = viewLeaderBoardCommandService.update(viewLeaderBoardCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewLeaderBoardCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /view-leader-board-commands/:id} : Partial updates given fields of an existing viewLeaderBoardCommand, field will ignore if it is null
     *
     * @param id the id of the viewLeaderBoardCommandDTO to save.
     * @param viewLeaderBoardCommandDTO the viewLeaderBoardCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewLeaderBoardCommandDTO,
     * or with status {@code 400 (Bad Request)} if the viewLeaderBoardCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the viewLeaderBoardCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the viewLeaderBoardCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/view-leader-board-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ViewLeaderBoardCommandDTO> partialUpdateViewLeaderBoardCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewLeaderBoardCommandDTO viewLeaderBoardCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ViewLeaderBoardCommand partially : {}, {}", id, viewLeaderBoardCommandDTO);
        if (viewLeaderBoardCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewLeaderBoardCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewLeaderBoardCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ViewLeaderBoardCommandDTO> result = viewLeaderBoardCommandService.partialUpdate(viewLeaderBoardCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewLeaderBoardCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /view-leader-board-commands} : get all the viewLeaderBoardCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viewLeaderBoardCommands in body.
     */
    @GetMapping("/view-leader-board-commands")
    public List<ViewLeaderBoardCommandDTO> getAllViewLeaderBoardCommands() {
        log.debug("REST request to get all ViewLeaderBoardCommands");
        return viewLeaderBoardCommandService.findAll();
    }

    /**
     * {@code GET  /view-leader-board-commands/:id} : get the "id" viewLeaderBoardCommand.
     *
     * @param id the id of the viewLeaderBoardCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viewLeaderBoardCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/view-leader-board-commands/{id}")
    public ResponseEntity<ViewLeaderBoardCommandDTO> getViewLeaderBoardCommand(@PathVariable Long id) {
        log.debug("REST request to get ViewLeaderBoardCommand : {}", id);
        Optional<ViewLeaderBoardCommandDTO> viewLeaderBoardCommandDTO = viewLeaderBoardCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(viewLeaderBoardCommandDTO);
    }

    /**
     * {@code DELETE  /view-leader-board-commands/:id} : delete the "id" viewLeaderBoardCommand.
     *
     * @param id the id of the viewLeaderBoardCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/view-leader-board-commands/{id}")
    public ResponseEntity<Void> deleteViewLeaderBoardCommand(@PathVariable Long id) {
        log.debug("REST request to delete ViewLeaderBoardCommand : {}", id);
        viewLeaderBoardCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
