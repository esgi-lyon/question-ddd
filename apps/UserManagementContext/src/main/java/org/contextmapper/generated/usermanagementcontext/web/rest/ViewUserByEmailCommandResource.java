package org.contextmapper.generated.usermanagementcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.usermanagementcontext.repository.ViewUserByEmailCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.ViewUserByEmailCommandService;
import org.contextmapper.generated.usermanagementcontext.service.dto.ViewUserByEmailCommandDTO;
import org.contextmapper.generated.usermanagementcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.usermanagementcontext.domain.ViewUserByEmailCommand}.
 */
@RestController
@RequestMapping("/api")
public class ViewUserByEmailCommandResource {

    private final Logger log = LoggerFactory.getLogger(ViewUserByEmailCommandResource.class);

    private static final String ENTITY_NAME = "userManagementContextViewUserByEmailCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ViewUserByEmailCommandService viewUserByEmailCommandService;

    private final ViewUserByEmailCommandRepository viewUserByEmailCommandRepository;

    public ViewUserByEmailCommandResource(
        ViewUserByEmailCommandService viewUserByEmailCommandService,
        ViewUserByEmailCommandRepository viewUserByEmailCommandRepository
    ) {
        this.viewUserByEmailCommandService = viewUserByEmailCommandService;
        this.viewUserByEmailCommandRepository = viewUserByEmailCommandRepository;
    }

    /**
     * {@code POST  /view-user-by-email-commands} : Create a new viewUserByEmailCommand.
     *
     * @param viewUserByEmailCommandDTO the viewUserByEmailCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new viewUserByEmailCommandDTO, or with status {@code 400 (Bad Request)} if the viewUserByEmailCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/view-user-by-email-commands")
    public ResponseEntity<ViewUserByEmailCommandDTO> createViewUserByEmailCommand(
        @RequestBody ViewUserByEmailCommandDTO viewUserByEmailCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ViewUserByEmailCommand : {}", viewUserByEmailCommandDTO);
        if (viewUserByEmailCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new viewUserByEmailCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ViewUserByEmailCommandDTO result = viewUserByEmailCommandService.save(viewUserByEmailCommandDTO);
        return ResponseEntity
            .created(new URI("/api/view-user-by-email-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /view-user-by-email-commands/:id} : Updates an existing viewUserByEmailCommand.
     *
     * @param id the id of the viewUserByEmailCommandDTO to save.
     * @param viewUserByEmailCommandDTO the viewUserByEmailCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewUserByEmailCommandDTO,
     * or with status {@code 400 (Bad Request)} if the viewUserByEmailCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the viewUserByEmailCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/view-user-by-email-commands/{id}")
    public ResponseEntity<ViewUserByEmailCommandDTO> updateViewUserByEmailCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewUserByEmailCommandDTO viewUserByEmailCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ViewUserByEmailCommand : {}, {}", id, viewUserByEmailCommandDTO);
        if (viewUserByEmailCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewUserByEmailCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewUserByEmailCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ViewUserByEmailCommandDTO result = viewUserByEmailCommandService.update(viewUserByEmailCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewUserByEmailCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /view-user-by-email-commands/:id} : Partial updates given fields of an existing viewUserByEmailCommand, field will ignore if it is null
     *
     * @param id the id of the viewUserByEmailCommandDTO to save.
     * @param viewUserByEmailCommandDTO the viewUserByEmailCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewUserByEmailCommandDTO,
     * or with status {@code 400 (Bad Request)} if the viewUserByEmailCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the viewUserByEmailCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the viewUserByEmailCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/view-user-by-email-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ViewUserByEmailCommandDTO> partialUpdateViewUserByEmailCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewUserByEmailCommandDTO viewUserByEmailCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ViewUserByEmailCommand partially : {}, {}", id, viewUserByEmailCommandDTO);
        if (viewUserByEmailCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewUserByEmailCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewUserByEmailCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ViewUserByEmailCommandDTO> result = viewUserByEmailCommandService.partialUpdate(viewUserByEmailCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewUserByEmailCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /view-user-by-email-commands} : get all the viewUserByEmailCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viewUserByEmailCommands in body.
     */
    @GetMapping("/view-user-by-email-commands")
    public List<ViewUserByEmailCommandDTO> getAllViewUserByEmailCommands() {
        log.debug("REST request to get all ViewUserByEmailCommands");
        return viewUserByEmailCommandService.findAll();
    }

    /**
     * {@code GET  /view-user-by-email-commands/:id} : get the "id" viewUserByEmailCommand.
     *
     * @param id the id of the viewUserByEmailCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viewUserByEmailCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/view-user-by-email-commands/{id}")
    public ResponseEntity<ViewUserByEmailCommandDTO> getViewUserByEmailCommand(@PathVariable Long id) {
        log.debug("REST request to get ViewUserByEmailCommand : {}", id);
        Optional<ViewUserByEmailCommandDTO> viewUserByEmailCommandDTO = viewUserByEmailCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(viewUserByEmailCommandDTO);
    }

    /**
     * {@code DELETE  /view-user-by-email-commands/:id} : delete the "id" viewUserByEmailCommand.
     *
     * @param id the id of the viewUserByEmailCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/view-user-by-email-commands/{id}")
    public ResponseEntity<Void> deleteViewUserByEmailCommand(@PathVariable Long id) {
        log.debug("REST request to delete ViewUserByEmailCommand : {}", id);
        viewUserByEmailCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
