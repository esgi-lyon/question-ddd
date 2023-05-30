package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.ViewTagEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.ViewTagEvaluationCommandService;
import org.contextmapper.generated.evaluationcontext.service.dto.ViewTagEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.ViewTagEvaluationCommand}.
 */
@RestController
@RequestMapping("/api")
public class ViewTagEvaluationCommandResource {

    private final Logger log = LoggerFactory.getLogger(ViewTagEvaluationCommandResource.class);

    private static final String ENTITY_NAME = "evaluationContextViewTagEvaluationCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ViewTagEvaluationCommandService viewTagEvaluationCommandService;

    private final ViewTagEvaluationCommandRepository viewTagEvaluationCommandRepository;

    public ViewTagEvaluationCommandResource(
        ViewTagEvaluationCommandService viewTagEvaluationCommandService,
        ViewTagEvaluationCommandRepository viewTagEvaluationCommandRepository
    ) {
        this.viewTagEvaluationCommandService = viewTagEvaluationCommandService;
        this.viewTagEvaluationCommandRepository = viewTagEvaluationCommandRepository;
    }

    /**
     * {@code POST  /view-tag-evaluation-commands} : Create a new viewTagEvaluationCommand.
     *
     * @param viewTagEvaluationCommandDTO the viewTagEvaluationCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new viewTagEvaluationCommandDTO, or with status {@code 400 (Bad Request)} if the viewTagEvaluationCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/view-tag-evaluation-commands")
    public ResponseEntity<ViewTagEvaluationCommandDTO> createViewTagEvaluationCommand(
        @RequestBody ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ViewTagEvaluationCommand : {}", viewTagEvaluationCommandDTO);
        if (viewTagEvaluationCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new viewTagEvaluationCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ViewTagEvaluationCommandDTO result = viewTagEvaluationCommandService.save(viewTagEvaluationCommandDTO);
        return ResponseEntity
            .created(new URI("/api/view-tag-evaluation-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /view-tag-evaluation-commands/:id} : Updates an existing viewTagEvaluationCommand.
     *
     * @param id the id of the viewTagEvaluationCommandDTO to save.
     * @param viewTagEvaluationCommandDTO the viewTagEvaluationCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewTagEvaluationCommandDTO,
     * or with status {@code 400 (Bad Request)} if the viewTagEvaluationCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the viewTagEvaluationCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/view-tag-evaluation-commands/{id}")
    public ResponseEntity<ViewTagEvaluationCommandDTO> updateViewTagEvaluationCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ViewTagEvaluationCommand : {}, {}", id, viewTagEvaluationCommandDTO);
        if (viewTagEvaluationCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewTagEvaluationCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewTagEvaluationCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ViewTagEvaluationCommandDTO result = viewTagEvaluationCommandService.update(viewTagEvaluationCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewTagEvaluationCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /view-tag-evaluation-commands/:id} : Partial updates given fields of an existing viewTagEvaluationCommand, field will ignore if it is null
     *
     * @param id the id of the viewTagEvaluationCommandDTO to save.
     * @param viewTagEvaluationCommandDTO the viewTagEvaluationCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewTagEvaluationCommandDTO,
     * or with status {@code 400 (Bad Request)} if the viewTagEvaluationCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the viewTagEvaluationCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the viewTagEvaluationCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/view-tag-evaluation-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ViewTagEvaluationCommandDTO> partialUpdateViewTagEvaluationCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewTagEvaluationCommandDTO viewTagEvaluationCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ViewTagEvaluationCommand partially : {}, {}", id, viewTagEvaluationCommandDTO);
        if (viewTagEvaluationCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewTagEvaluationCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewTagEvaluationCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ViewTagEvaluationCommandDTO> result = viewTagEvaluationCommandService.partialUpdate(viewTagEvaluationCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewTagEvaluationCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /view-tag-evaluation-commands} : get all the viewTagEvaluationCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viewTagEvaluationCommands in body.
     */
    @GetMapping("/view-tag-evaluation-commands")
    public List<ViewTagEvaluationCommandDTO> getAllViewTagEvaluationCommands() {
        log.debug("REST request to get all ViewTagEvaluationCommands");
        return viewTagEvaluationCommandService.findAll();
    }

    /**
     * {@code GET  /view-tag-evaluation-commands/:id} : get the "id" viewTagEvaluationCommand.
     *
     * @param id the id of the viewTagEvaluationCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viewTagEvaluationCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/view-tag-evaluation-commands/{id}")
    public ResponseEntity<ViewTagEvaluationCommandDTO> getViewTagEvaluationCommand(@PathVariable Long id) {
        log.debug("REST request to get ViewTagEvaluationCommand : {}", id);
        Optional<ViewTagEvaluationCommandDTO> viewTagEvaluationCommandDTO = viewTagEvaluationCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(viewTagEvaluationCommandDTO);
    }

    /**
     * {@code DELETE  /view-tag-evaluation-commands/:id} : delete the "id" viewTagEvaluationCommand.
     *
     * @param id the id of the viewTagEvaluationCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/view-tag-evaluation-commands/{id}")
    public ResponseEntity<Void> deleteViewTagEvaluationCommand(@PathVariable Long id) {
        log.debug("REST request to delete ViewTagEvaluationCommand : {}", id);
        viewTagEvaluationCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
