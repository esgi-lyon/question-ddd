package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.EvaluationCreatedRepository;
import org.contextmapper.generated.evaluationcontext.service.EvaluationCreatedService;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationCreatedDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.EvaluationCreated}.
 */
@RestController
@RequestMapping("/api")
public class EvaluationCreatedResource {

    private final Logger log = LoggerFactory.getLogger(EvaluationCreatedResource.class);

    private static final String ENTITY_NAME = "evaluationContextEvaluationCreated";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EvaluationCreatedService evaluationCreatedService;

    private final EvaluationCreatedRepository evaluationCreatedRepository;

    public EvaluationCreatedResource(
        EvaluationCreatedService evaluationCreatedService,
        EvaluationCreatedRepository evaluationCreatedRepository
    ) {
        this.evaluationCreatedService = evaluationCreatedService;
        this.evaluationCreatedRepository = evaluationCreatedRepository;
    }

    /**
     * {@code POST  /evaluation-createds} : Create a new evaluationCreated.
     *
     * @param evaluationCreatedDTO the evaluationCreatedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new evaluationCreatedDTO, or with status {@code 400 (Bad Request)} if the evaluationCreated has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/evaluation-createds")
    public ResponseEntity<EvaluationCreatedDTO> createEvaluationCreated(@RequestBody EvaluationCreatedDTO evaluationCreatedDTO)
        throws URISyntaxException {
        log.debug("REST request to save EvaluationCreated : {}", evaluationCreatedDTO);
        if (evaluationCreatedDTO.getId() != null) {
            throw new BadRequestAlertException("A new evaluationCreated cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EvaluationCreatedDTO result = evaluationCreatedService.save(evaluationCreatedDTO);
        return ResponseEntity
            .created(new URI("/api/evaluation-createds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /evaluation-createds/:id} : Updates an existing evaluationCreated.
     *
     * @param id the id of the evaluationCreatedDTO to save.
     * @param evaluationCreatedDTO the evaluationCreatedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated evaluationCreatedDTO,
     * or with status {@code 400 (Bad Request)} if the evaluationCreatedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the evaluationCreatedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/evaluation-createds/{id}")
    public ResponseEntity<EvaluationCreatedDTO> updateEvaluationCreated(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EvaluationCreatedDTO evaluationCreatedDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EvaluationCreated : {}, {}", id, evaluationCreatedDTO);
        if (evaluationCreatedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, evaluationCreatedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!evaluationCreatedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EvaluationCreatedDTO result = evaluationCreatedService.update(evaluationCreatedDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, evaluationCreatedDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /evaluation-createds/:id} : Partial updates given fields of an existing evaluationCreated, field will ignore if it is null
     *
     * @param id the id of the evaluationCreatedDTO to save.
     * @param evaluationCreatedDTO the evaluationCreatedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated evaluationCreatedDTO,
     * or with status {@code 400 (Bad Request)} if the evaluationCreatedDTO is not valid,
     * or with status {@code 404 (Not Found)} if the evaluationCreatedDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the evaluationCreatedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/evaluation-createds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EvaluationCreatedDTO> partialUpdateEvaluationCreated(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EvaluationCreatedDTO evaluationCreatedDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EvaluationCreated partially : {}, {}", id, evaluationCreatedDTO);
        if (evaluationCreatedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, evaluationCreatedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!evaluationCreatedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EvaluationCreatedDTO> result = evaluationCreatedService.partialUpdate(evaluationCreatedDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, evaluationCreatedDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /evaluation-createds} : get all the evaluationCreateds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of evaluationCreateds in body.
     */
    @GetMapping("/evaluation-createds")
    public List<EvaluationCreatedDTO> getAllEvaluationCreateds() {
        log.debug("REST request to get all EvaluationCreateds");
        return evaluationCreatedService.findAll();
    }

    /**
     * {@code GET  /evaluation-createds/:id} : get the "id" evaluationCreated.
     *
     * @param id the id of the evaluationCreatedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluationCreatedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evaluation-createds/{id}")
    public ResponseEntity<EvaluationCreatedDTO> getEvaluationCreated(@PathVariable Long id) {
        log.debug("REST request to get EvaluationCreated : {}", id);
        Optional<EvaluationCreatedDTO> evaluationCreatedDTO = evaluationCreatedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluationCreatedDTO);
    }

    /**
     * {@code DELETE  /evaluation-createds/:id} : delete the "id" evaluationCreated.
     *
     * @param id the id of the evaluationCreatedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/evaluation-createds/{id}")
    public ResponseEntity<Void> deleteEvaluationCreated(@PathVariable Long id) {
        log.debug("REST request to delete EvaluationCreated : {}", id);
        evaluationCreatedService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
