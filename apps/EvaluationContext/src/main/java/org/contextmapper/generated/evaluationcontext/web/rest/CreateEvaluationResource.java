package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.CreateEvaluationRepository;
import org.contextmapper.generated.evaluationcontext.service.CreateEvaluationService;
import org.contextmapper.generated.evaluationcontext.service.dto.CreateEvaluationDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.CreateEvaluation}.
 */
@RestController
@RequestMapping("/api")
public class CreateEvaluationResource {

    private final Logger log = LoggerFactory.getLogger(CreateEvaluationResource.class);

    private static final String ENTITY_NAME = "evaluationContextCreateEvaluation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreateEvaluationService createEvaluationService;

    private final CreateEvaluationRepository createEvaluationRepository;

    public CreateEvaluationResource(
        CreateEvaluationService createEvaluationService,
        CreateEvaluationRepository createEvaluationRepository
    ) {
        this.createEvaluationService = createEvaluationService;
        this.createEvaluationRepository = createEvaluationRepository;
    }

    /**
     * {@code POST  /create-evaluations} : Create a new createEvaluation.
     *
     * @param createEvaluationDTO the createEvaluationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createEvaluationDTO, or with status {@code 400 (Bad Request)} if the createEvaluation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-evaluations")
    public ResponseEntity<CreateEvaluationDTO> createCreateEvaluation(@RequestBody CreateEvaluationDTO createEvaluationDTO)
        throws URISyntaxException {
        log.debug("REST request to save CreateEvaluation : {}", createEvaluationDTO);
        if (createEvaluationDTO.getId() != null) {
            throw new BadRequestAlertException("A new createEvaluation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreateEvaluationDTO result = createEvaluationService.save(createEvaluationDTO);
        return ResponseEntity
            .created(new URI("/api/create-evaluations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /create-evaluations/:id} : Updates an existing createEvaluation.
     *
     * @param id the id of the createEvaluationDTO to save.
     * @param createEvaluationDTO the createEvaluationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createEvaluationDTO,
     * or with status {@code 400 (Bad Request)} if the createEvaluationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the createEvaluationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/create-evaluations/{id}")
    public ResponseEntity<CreateEvaluationDTO> updateCreateEvaluation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateEvaluationDTO createEvaluationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CreateEvaluation : {}, {}", id, createEvaluationDTO);
        if (createEvaluationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createEvaluationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createEvaluationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CreateEvaluationDTO result = createEvaluationService.update(createEvaluationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createEvaluationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /create-evaluations/:id} : Partial updates given fields of an existing createEvaluation, field will ignore if it is null
     *
     * @param id the id of the createEvaluationDTO to save.
     * @param createEvaluationDTO the createEvaluationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createEvaluationDTO,
     * or with status {@code 400 (Bad Request)} if the createEvaluationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the createEvaluationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the createEvaluationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/create-evaluations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CreateEvaluationDTO> partialUpdateCreateEvaluation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateEvaluationDTO createEvaluationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreateEvaluation partially : {}, {}", id, createEvaluationDTO);
        if (createEvaluationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createEvaluationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createEvaluationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CreateEvaluationDTO> result = createEvaluationService.partialUpdate(createEvaluationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createEvaluationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /create-evaluations} : get all the createEvaluations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createEvaluations in body.
     */
    @GetMapping("/create-evaluations")
    public List<CreateEvaluationDTO> getAllCreateEvaluations() {
        log.debug("REST request to get all CreateEvaluations");
        return createEvaluationService.findAll();
    }

    /**
     * {@code GET  /create-evaluations/:id} : get the "id" createEvaluation.
     *
     * @param id the id of the createEvaluationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createEvaluationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/create-evaluations/{id}")
    public ResponseEntity<CreateEvaluationDTO> getCreateEvaluation(@PathVariable Long id) {
        log.debug("REST request to get CreateEvaluation : {}", id);
        Optional<CreateEvaluationDTO> createEvaluationDTO = createEvaluationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createEvaluationDTO);
    }

    /**
     * {@code DELETE  /create-evaluations/:id} : delete the "id" createEvaluation.
     *
     * @param id the id of the createEvaluationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/create-evaluations/{id}")
    public ResponseEntity<Void> deleteCreateEvaluation(@PathVariable Long id) {
        log.debug("REST request to delete CreateEvaluation : {}", id);
        createEvaluationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
