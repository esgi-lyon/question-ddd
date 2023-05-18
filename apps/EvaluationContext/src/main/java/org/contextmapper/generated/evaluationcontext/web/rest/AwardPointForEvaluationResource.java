package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.AwardPointForEvaluationRepository;
import org.contextmapper.generated.evaluationcontext.service.AwardPointForEvaluationService;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardPointForEvaluationDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.AwardPointForEvaluation}.
 */
@RestController
@RequestMapping("/api")
public class AwardPointForEvaluationResource {

    private final Logger log = LoggerFactory.getLogger(AwardPointForEvaluationResource.class);

    private static final String ENTITY_NAME = "evaluationContextAwardPointForEvaluation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AwardPointForEvaluationService awardPointForEvaluationService;

    private final AwardPointForEvaluationRepository awardPointForEvaluationRepository;

    public AwardPointForEvaluationResource(
        AwardPointForEvaluationService awardPointForEvaluationService,
        AwardPointForEvaluationRepository awardPointForEvaluationRepository
    ) {
        this.awardPointForEvaluationService = awardPointForEvaluationService;
        this.awardPointForEvaluationRepository = awardPointForEvaluationRepository;
    }

    /**
     * {@code POST  /award-point-for-evaluations} : Create a new awardPointForEvaluation.
     *
     * @param awardPointForEvaluationDTO the awardPointForEvaluationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new awardPointForEvaluationDTO, or with status {@code 400 (Bad Request)} if the awardPointForEvaluation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/award-point-for-evaluations")
    public ResponseEntity<AwardPointForEvaluationDTO> createAwardPointForEvaluation(
        @RequestBody AwardPointForEvaluationDTO awardPointForEvaluationDTO
    ) throws URISyntaxException {
        log.debug("REST request to save AwardPointForEvaluation : {}", awardPointForEvaluationDTO);
        if (awardPointForEvaluationDTO.getId() != null) {
            throw new BadRequestAlertException("A new awardPointForEvaluation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AwardPointForEvaluationDTO result = awardPointForEvaluationService.save(awardPointForEvaluationDTO);
        return ResponseEntity
            .created(new URI("/api/award-point-for-evaluations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /award-point-for-evaluations/:id} : Updates an existing awardPointForEvaluation.
     *
     * @param id the id of the awardPointForEvaluationDTO to save.
     * @param awardPointForEvaluationDTO the awardPointForEvaluationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated awardPointForEvaluationDTO,
     * or with status {@code 400 (Bad Request)} if the awardPointForEvaluationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the awardPointForEvaluationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/award-point-for-evaluations/{id}")
    public ResponseEntity<AwardPointForEvaluationDTO> updateAwardPointForEvaluation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AwardPointForEvaluationDTO awardPointForEvaluationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AwardPointForEvaluation : {}, {}", id, awardPointForEvaluationDTO);
        if (awardPointForEvaluationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, awardPointForEvaluationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!awardPointForEvaluationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AwardPointForEvaluationDTO result = awardPointForEvaluationService.update(awardPointForEvaluationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, awardPointForEvaluationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /award-point-for-evaluations/:id} : Partial updates given fields of an existing awardPointForEvaluation, field will ignore if it is null
     *
     * @param id the id of the awardPointForEvaluationDTO to save.
     * @param awardPointForEvaluationDTO the awardPointForEvaluationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated awardPointForEvaluationDTO,
     * or with status {@code 400 (Bad Request)} if the awardPointForEvaluationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the awardPointForEvaluationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the awardPointForEvaluationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/award-point-for-evaluations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AwardPointForEvaluationDTO> partialUpdateAwardPointForEvaluation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AwardPointForEvaluationDTO awardPointForEvaluationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AwardPointForEvaluation partially : {}, {}", id, awardPointForEvaluationDTO);
        if (awardPointForEvaluationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, awardPointForEvaluationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!awardPointForEvaluationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AwardPointForEvaluationDTO> result = awardPointForEvaluationService.partialUpdate(awardPointForEvaluationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, awardPointForEvaluationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /award-point-for-evaluations} : get all the awardPointForEvaluations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of awardPointForEvaluations in body.
     */
    @GetMapping("/award-point-for-evaluations")
    public List<AwardPointForEvaluationDTO> getAllAwardPointForEvaluations() {
        log.debug("REST request to get all AwardPointForEvaluations");
        return awardPointForEvaluationService.findAll();
    }

    /**
     * {@code GET  /award-point-for-evaluations/:id} : get the "id" awardPointForEvaluation.
     *
     * @param id the id of the awardPointForEvaluationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the awardPointForEvaluationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/award-point-for-evaluations/{id}")
    public ResponseEntity<AwardPointForEvaluationDTO> getAwardPointForEvaluation(@PathVariable Long id) {
        log.debug("REST request to get AwardPointForEvaluation : {}", id);
        Optional<AwardPointForEvaluationDTO> awardPointForEvaluationDTO = awardPointForEvaluationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(awardPointForEvaluationDTO);
    }

    /**
     * {@code DELETE  /award-point-for-evaluations/:id} : delete the "id" awardPointForEvaluation.
     *
     * @param id the id of the awardPointForEvaluationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/award-point-for-evaluations/{id}")
    public ResponseEntity<Void> deleteAwardPointForEvaluation(@PathVariable Long id) {
        log.debug("REST request to delete AwardPointForEvaluation : {}", id);
        awardPointForEvaluationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
