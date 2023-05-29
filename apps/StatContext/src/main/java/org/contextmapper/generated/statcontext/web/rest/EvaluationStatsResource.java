package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.EvaluationStatsRepository;
import org.contextmapper.generated.statcontext.service.EvaluationStatsService;
import org.contextmapper.generated.statcontext.service.dto.EvaluationStatsDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.EvaluationStats}.
 */
@RestController
@RequestMapping("/api")
public class EvaluationStatsResource {

    private final Logger log = LoggerFactory.getLogger(EvaluationStatsResource.class);

    private static final String ENTITY_NAME = "statContextEvaluationStats";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EvaluationStatsService evaluationStatsService;

    private final EvaluationStatsRepository evaluationStatsRepository;

    public EvaluationStatsResource(EvaluationStatsService evaluationStatsService, EvaluationStatsRepository evaluationStatsRepository) {
        this.evaluationStatsService = evaluationStatsService;
        this.evaluationStatsRepository = evaluationStatsRepository;
    }

    /**
     * {@code POST  /evaluation-stats} : Create a new evaluationStats.
     *
     * @param evaluationStatsDTO the evaluationStatsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new evaluationStatsDTO, or with status {@code 400 (Bad Request)} if the evaluationStats has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/evaluation-stats")
    public ResponseEntity<EvaluationStatsDTO> createEvaluationStats(@RequestBody EvaluationStatsDTO evaluationStatsDTO)
        throws URISyntaxException {
        log.debug("REST request to save EvaluationStats : {}", evaluationStatsDTO);
        if (evaluationStatsDTO.getId() != null) {
            throw new BadRequestAlertException("A new evaluationStats cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EvaluationStatsDTO result = evaluationStatsService.save(evaluationStatsDTO);
        return ResponseEntity
            .created(new URI("/api/evaluation-stats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /evaluation-stats/:id} : Updates an existing evaluationStats.
     *
     * @param id the id of the evaluationStatsDTO to save.
     * @param evaluationStatsDTO the evaluationStatsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated evaluationStatsDTO,
     * or with status {@code 400 (Bad Request)} if the evaluationStatsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the evaluationStatsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/evaluation-stats/{id}")
    public ResponseEntity<EvaluationStatsDTO> updateEvaluationStats(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EvaluationStatsDTO evaluationStatsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EvaluationStats : {}, {}", id, evaluationStatsDTO);
        if (evaluationStatsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, evaluationStatsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!evaluationStatsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EvaluationStatsDTO result = evaluationStatsService.update(evaluationStatsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, evaluationStatsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /evaluation-stats/:id} : Partial updates given fields of an existing evaluationStats, field will ignore if it is null
     *
     * @param id the id of the evaluationStatsDTO to save.
     * @param evaluationStatsDTO the evaluationStatsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated evaluationStatsDTO,
     * or with status {@code 400 (Bad Request)} if the evaluationStatsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the evaluationStatsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the evaluationStatsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/evaluation-stats/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EvaluationStatsDTO> partialUpdateEvaluationStats(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EvaluationStatsDTO evaluationStatsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EvaluationStats partially : {}, {}", id, evaluationStatsDTO);
        if (evaluationStatsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, evaluationStatsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!evaluationStatsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EvaluationStatsDTO> result = evaluationStatsService.partialUpdate(evaluationStatsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, evaluationStatsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /evaluation-stats} : get all the evaluationStats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of evaluationStats in body.
     */
    @GetMapping("/evaluation-stats")
    public List<EvaluationStatsDTO> getAllEvaluationStats() {
        log.debug("REST request to get all EvaluationStats");
        return evaluationStatsService.findAll();
    }

    /**
     * {@code GET  /evaluation-stats/:id} : get the "id" evaluationStats.
     *
     * @param id the id of the evaluationStatsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluationStatsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evaluation-stats/{id}")
    public ResponseEntity<EvaluationStatsDTO> getEvaluationStats(@PathVariable Long id) {
        log.debug("REST request to get EvaluationStats : {}", id);
        Optional<EvaluationStatsDTO> evaluationStatsDTO = evaluationStatsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluationStatsDTO);
    }

    /**
     * {@code DELETE  /evaluation-stats/:id} : delete the "id" evaluationStats.
     *
     * @param id the id of the evaluationStatsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/evaluation-stats/{id}")
    public ResponseEntity<Void> deleteEvaluationStats(@PathVariable Long id) {
        log.debug("REST request to delete EvaluationStats : {}", id);
        evaluationStatsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
