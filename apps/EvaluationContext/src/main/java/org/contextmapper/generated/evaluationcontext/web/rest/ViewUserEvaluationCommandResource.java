package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.ViewUserEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.ViewUserEvaluationCommandService;
import org.contextmapper.generated.evaluationcontext.service.dto.ViewUserEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.ViewUserEvaluationCommand}.
 */
@RestController
@RequestMapping("/api")
public class ViewUserEvaluationCommandResource {

    private final Logger log = LoggerFactory.getLogger(ViewUserEvaluationCommandResource.class);

    private static final String ENTITY_NAME = "evaluationContextViewUserEvaluationCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ViewUserEvaluationCommandService viewUserEvaluationCommandService;

    private final ViewUserEvaluationCommandRepository viewUserEvaluationCommandRepository;

    public ViewUserEvaluationCommandResource(
        ViewUserEvaluationCommandService viewUserEvaluationCommandService,
        ViewUserEvaluationCommandRepository viewUserEvaluationCommandRepository
    ) {
        this.viewUserEvaluationCommandService = viewUserEvaluationCommandService;
        this.viewUserEvaluationCommandRepository = viewUserEvaluationCommandRepository;
    }

    /**
     * {@code POST  /view-user-evaluation-commands} : Create a new viewUserEvaluationCommand.
     *
     * @param viewUserEvaluationCommandDTO the viewUserEvaluationCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new viewUserEvaluationCommandDTO, or with status {@code 400 (Bad Request)} if the viewUserEvaluationCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/view-user-evaluation-commands")
    public ResponseEntity<ViewUserEvaluationCommandDTO> createViewUserEvaluationCommand(
        @RequestBody ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ViewUserEvaluationCommand : {}", viewUserEvaluationCommandDTO);
        if (viewUserEvaluationCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new viewUserEvaluationCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ViewUserEvaluationCommandDTO result = viewUserEvaluationCommandService.save(viewUserEvaluationCommandDTO);
        return ResponseEntity
            .created(new URI("/api/view-user-evaluation-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /view-user-evaluation-commands/:id} : Updates an existing viewUserEvaluationCommand.
     *
     * @param id the id of the viewUserEvaluationCommandDTO to save.
     * @param viewUserEvaluationCommandDTO the viewUserEvaluationCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewUserEvaluationCommandDTO,
     * or with status {@code 400 (Bad Request)} if the viewUserEvaluationCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the viewUserEvaluationCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/view-user-evaluation-commands/{id}")
    public ResponseEntity<ViewUserEvaluationCommandDTO> updateViewUserEvaluationCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ViewUserEvaluationCommand : {}, {}", id, viewUserEvaluationCommandDTO);
        if (viewUserEvaluationCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewUserEvaluationCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewUserEvaluationCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ViewUserEvaluationCommandDTO result = viewUserEvaluationCommandService.update(viewUserEvaluationCommandDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewUserEvaluationCommandDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /view-user-evaluation-commands/:id} : Partial updates given fields of an existing viewUserEvaluationCommand, field will ignore if it is null
     *
     * @param id the id of the viewUserEvaluationCommandDTO to save.
     * @param viewUserEvaluationCommandDTO the viewUserEvaluationCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewUserEvaluationCommandDTO,
     * or with status {@code 400 (Bad Request)} if the viewUserEvaluationCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the viewUserEvaluationCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the viewUserEvaluationCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/view-user-evaluation-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ViewUserEvaluationCommandDTO> partialUpdateViewUserEvaluationCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewUserEvaluationCommandDTO viewUserEvaluationCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ViewUserEvaluationCommand partially : {}, {}", id, viewUserEvaluationCommandDTO);
        if (viewUserEvaluationCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewUserEvaluationCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewUserEvaluationCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ViewUserEvaluationCommandDTO> result = viewUserEvaluationCommandService.partialUpdate(viewUserEvaluationCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewUserEvaluationCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /view-user-evaluation-commands} : get all the viewUserEvaluationCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viewUserEvaluationCommands in body.
     */
    @GetMapping("/view-user-evaluation-commands")
    public List<ViewUserEvaluationCommandDTO> getAllViewUserEvaluationCommands() {
        log.debug("REST request to get all ViewUserEvaluationCommands");
        return viewUserEvaluationCommandService.findAll();
    }

    /**
     * {@code GET  /view-user-evaluation-commands/:id} : get the "id" viewUserEvaluationCommand.
     *
     * @param id the id of the viewUserEvaluationCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viewUserEvaluationCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/view-user-evaluation-commands/{id}")
    public ResponseEntity<ViewUserEvaluationCommandDTO> getViewUserEvaluationCommand(@PathVariable Long id) {
        log.debug("REST request to get ViewUserEvaluationCommand : {}", id);
        Optional<ViewUserEvaluationCommandDTO> viewUserEvaluationCommandDTO = viewUserEvaluationCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(viewUserEvaluationCommandDTO);
    }

    /**
     * {@code DELETE  /view-user-evaluation-commands/:id} : delete the "id" viewUserEvaluationCommand.
     *
     * @param id the id of the viewUserEvaluationCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/view-user-evaluation-commands/{id}")
    public ResponseEntity<Void> deleteViewUserEvaluationCommand(@PathVariable Long id) {
        log.debug("REST request to delete ViewUserEvaluationCommand : {}", id);
        viewUserEvaluationCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
