package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.ViewQuestionEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.ViewQuestionEvaluationCommandService;
import org.contextmapper.generated.evaluationcontext.service.dto.ViewQuestionEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.ViewQuestionEvaluationCommand}.
 */
@RestController
@RequestMapping("/api")
public class ViewQuestionEvaluationCommandResource {

    private final Logger log = LoggerFactory.getLogger(ViewQuestionEvaluationCommandResource.class);

    private static final String ENTITY_NAME = "evaluationContextViewQuestionEvaluationCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ViewQuestionEvaluationCommandService viewQuestionEvaluationCommandService;

    private final ViewQuestionEvaluationCommandRepository viewQuestionEvaluationCommandRepository;

    public ViewQuestionEvaluationCommandResource(
        ViewQuestionEvaluationCommandService viewQuestionEvaluationCommandService,
        ViewQuestionEvaluationCommandRepository viewQuestionEvaluationCommandRepository
    ) {
        this.viewQuestionEvaluationCommandService = viewQuestionEvaluationCommandService;
        this.viewQuestionEvaluationCommandRepository = viewQuestionEvaluationCommandRepository;
    }

    /**
     * {@code POST  /view-question-evaluation-commands} : Create a new viewQuestionEvaluationCommand.
     *
     * @param viewQuestionEvaluationCommandDTO the viewQuestionEvaluationCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new viewQuestionEvaluationCommandDTO, or with status {@code 400 (Bad Request)} if the viewQuestionEvaluationCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/view-question-evaluation-commands")
    public ResponseEntity<ViewQuestionEvaluationCommandDTO> createViewQuestionEvaluationCommand(
        @RequestBody ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ViewQuestionEvaluationCommand : {}", viewQuestionEvaluationCommandDTO);
        if (viewQuestionEvaluationCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new viewQuestionEvaluationCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ViewQuestionEvaluationCommandDTO result = viewQuestionEvaluationCommandService.save(viewQuestionEvaluationCommandDTO);
        return ResponseEntity
            .created(new URI("/api/view-question-evaluation-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /view-question-evaluation-commands/:id} : Updates an existing viewQuestionEvaluationCommand.
     *
     * @param id the id of the viewQuestionEvaluationCommandDTO to save.
     * @param viewQuestionEvaluationCommandDTO the viewQuestionEvaluationCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewQuestionEvaluationCommandDTO,
     * or with status {@code 400 (Bad Request)} if the viewQuestionEvaluationCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the viewQuestionEvaluationCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/view-question-evaluation-commands/{id}")
    public ResponseEntity<ViewQuestionEvaluationCommandDTO> updateViewQuestionEvaluationCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ViewQuestionEvaluationCommand : {}, {}", id, viewQuestionEvaluationCommandDTO);
        if (viewQuestionEvaluationCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewQuestionEvaluationCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewQuestionEvaluationCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ViewQuestionEvaluationCommandDTO result = viewQuestionEvaluationCommandService.update(viewQuestionEvaluationCommandDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewQuestionEvaluationCommandDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /view-question-evaluation-commands/:id} : Partial updates given fields of an existing viewQuestionEvaluationCommand, field will ignore if it is null
     *
     * @param id the id of the viewQuestionEvaluationCommandDTO to save.
     * @param viewQuestionEvaluationCommandDTO the viewQuestionEvaluationCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewQuestionEvaluationCommandDTO,
     * or with status {@code 400 (Bad Request)} if the viewQuestionEvaluationCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the viewQuestionEvaluationCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the viewQuestionEvaluationCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/view-question-evaluation-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ViewQuestionEvaluationCommandDTO> partialUpdateViewQuestionEvaluationCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewQuestionEvaluationCommandDTO viewQuestionEvaluationCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ViewQuestionEvaluationCommand partially : {}, {}", id, viewQuestionEvaluationCommandDTO);
        if (viewQuestionEvaluationCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewQuestionEvaluationCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewQuestionEvaluationCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ViewQuestionEvaluationCommandDTO> result = viewQuestionEvaluationCommandService.partialUpdate(
            viewQuestionEvaluationCommandDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewQuestionEvaluationCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /view-question-evaluation-commands} : get all the viewQuestionEvaluationCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viewQuestionEvaluationCommands in body.
     */
    @GetMapping("/view-question-evaluation-commands")
    public List<ViewQuestionEvaluationCommandDTO> getAllViewQuestionEvaluationCommands() {
        log.debug("REST request to get all ViewQuestionEvaluationCommands");
        return viewQuestionEvaluationCommandService.findAll();
    }

    /**
     * {@code GET  /view-question-evaluation-commands/:id} : get the "id" viewQuestionEvaluationCommand.
     *
     * @param id the id of the viewQuestionEvaluationCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viewQuestionEvaluationCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/view-question-evaluation-commands/{id}")
    public ResponseEntity<ViewQuestionEvaluationCommandDTO> getViewQuestionEvaluationCommand(@PathVariable Long id) {
        log.debug("REST request to get ViewQuestionEvaluationCommand : {}", id);
        Optional<ViewQuestionEvaluationCommandDTO> viewQuestionEvaluationCommandDTO = viewQuestionEvaluationCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(viewQuestionEvaluationCommandDTO);
    }

    /**
     * {@code DELETE  /view-question-evaluation-commands/:id} : delete the "id" viewQuestionEvaluationCommand.
     *
     * @param id the id of the viewQuestionEvaluationCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/view-question-evaluation-commands/{id}")
    public ResponseEntity<Void> deleteViewQuestionEvaluationCommand(@PathVariable Long id) {
        log.debug("REST request to delete ViewQuestionEvaluationCommand : {}", id);
        viewQuestionEvaluationCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
