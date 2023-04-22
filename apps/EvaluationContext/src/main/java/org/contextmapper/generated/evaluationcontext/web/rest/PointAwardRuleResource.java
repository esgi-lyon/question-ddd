package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.PointAwardRuleRepository;
import org.contextmapper.generated.evaluationcontext.service.PointAwardRuleService;
import org.contextmapper.generated.evaluationcontext.service.dto.PointAwardRuleDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.PointAwardRule}.
 */
@RestController
@RequestMapping("/api")
public class PointAwardRuleResource {

    private final Logger log = LoggerFactory.getLogger(PointAwardRuleResource.class);

    private static final String ENTITY_NAME = "evaluationContextPointAwardRule";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PointAwardRuleService pointAwardRuleService;

    private final PointAwardRuleRepository pointAwardRuleRepository;

    public PointAwardRuleResource(PointAwardRuleService pointAwardRuleService, PointAwardRuleRepository pointAwardRuleRepository) {
        this.pointAwardRuleService = pointAwardRuleService;
        this.pointAwardRuleRepository = pointAwardRuleRepository;
    }

    /**
     * {@code POST  /point-award-rules} : Create a new pointAwardRule.
     *
     * @param pointAwardRuleDTO the pointAwardRuleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pointAwardRuleDTO, or with status {@code 400 (Bad Request)} if the pointAwardRule has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/point-award-rules")
    public ResponseEntity<PointAwardRuleDTO> createPointAwardRule(@RequestBody PointAwardRuleDTO pointAwardRuleDTO)
        throws URISyntaxException {
        log.debug("REST request to save PointAwardRule : {}", pointAwardRuleDTO);
        if (pointAwardRuleDTO.getId() != null) {
            throw new BadRequestAlertException("A new pointAwardRule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PointAwardRuleDTO result = pointAwardRuleService.save(pointAwardRuleDTO);
        return ResponseEntity
            .created(new URI("/api/point-award-rules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /point-award-rules/:id} : Updates an existing pointAwardRule.
     *
     * @param id the id of the pointAwardRuleDTO to save.
     * @param pointAwardRuleDTO the pointAwardRuleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pointAwardRuleDTO,
     * or with status {@code 400 (Bad Request)} if the pointAwardRuleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pointAwardRuleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/point-award-rules/{id}")
    public ResponseEntity<PointAwardRuleDTO> updatePointAwardRule(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PointAwardRuleDTO pointAwardRuleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PointAwardRule : {}, {}", id, pointAwardRuleDTO);
        if (pointAwardRuleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pointAwardRuleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pointAwardRuleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PointAwardRuleDTO result = pointAwardRuleService.update(pointAwardRuleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pointAwardRuleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /point-award-rules/:id} : Partial updates given fields of an existing pointAwardRule, field will ignore if it is null
     *
     * @param id the id of the pointAwardRuleDTO to save.
     * @param pointAwardRuleDTO the pointAwardRuleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pointAwardRuleDTO,
     * or with status {@code 400 (Bad Request)} if the pointAwardRuleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the pointAwardRuleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the pointAwardRuleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/point-award-rules/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PointAwardRuleDTO> partialUpdatePointAwardRule(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PointAwardRuleDTO pointAwardRuleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PointAwardRule partially : {}, {}", id, pointAwardRuleDTO);
        if (pointAwardRuleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, pointAwardRuleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!pointAwardRuleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PointAwardRuleDTO> result = pointAwardRuleService.partialUpdate(pointAwardRuleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pointAwardRuleDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /point-award-rules} : get all the pointAwardRules.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pointAwardRules in body.
     */
    @GetMapping("/point-award-rules")
    public List<PointAwardRuleDTO> getAllPointAwardRules() {
        log.debug("REST request to get all PointAwardRules");
        return pointAwardRuleService.findAll();
    }

    /**
     * {@code GET  /point-award-rules/:id} : get the "id" pointAwardRule.
     *
     * @param id the id of the pointAwardRuleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pointAwardRuleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/point-award-rules/{id}")
    public ResponseEntity<PointAwardRuleDTO> getPointAwardRule(@PathVariable Long id) {
        log.debug("REST request to get PointAwardRule : {}", id);
        Optional<PointAwardRuleDTO> pointAwardRuleDTO = pointAwardRuleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pointAwardRuleDTO);
    }

    /**
     * {@code DELETE  /point-award-rules/:id} : delete the "id" pointAwardRule.
     *
     * @param id the id of the pointAwardRuleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/point-award-rules/{id}")
    public ResponseEntity<Void> deletePointAwardRule(@PathVariable Long id) {
        log.debug("REST request to delete PointAwardRule : {}", id);
        pointAwardRuleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
