package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.AwardedPointRepository;
import org.contextmapper.generated.evaluationcontext.service.AwardedPointService;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardedPointDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.AwardedPoint}.
 */
@RestController
@RequestMapping("/api")
public class AwardedPointResource {

    private final Logger log = LoggerFactory.getLogger(AwardedPointResource.class);

    private static final String ENTITY_NAME = "evaluationContextAwardedPoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AwardedPointService awardedPointService;

    private final AwardedPointRepository awardedPointRepository;

    public AwardedPointResource(AwardedPointService awardedPointService, AwardedPointRepository awardedPointRepository) {
        this.awardedPointService = awardedPointService;
        this.awardedPointRepository = awardedPointRepository;
    }

    /**
     * {@code POST  /awarded-points} : Create a new awardedPoint.
     *
     * @param awardedPointDTO the awardedPointDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new awardedPointDTO, or with status {@code 400 (Bad Request)} if the awardedPoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/awarded-points")
    public ResponseEntity<AwardedPointDTO> createAwardedPoint(@RequestBody AwardedPointDTO awardedPointDTO) throws URISyntaxException {
        log.debug("REST request to save AwardedPoint : {}", awardedPointDTO);
        if (awardedPointDTO.getId() != null) {
            throw new BadRequestAlertException("A new awardedPoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AwardedPointDTO result = awardedPointService.save(awardedPointDTO);
        return ResponseEntity
            .created(new URI("/api/awarded-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /awarded-points/:id} : Updates an existing awardedPoint.
     *
     * @param id the id of the awardedPointDTO to save.
     * @param awardedPointDTO the awardedPointDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated awardedPointDTO,
     * or with status {@code 400 (Bad Request)} if the awardedPointDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the awardedPointDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/awarded-points/{id}")
    public ResponseEntity<AwardedPointDTO> updateAwardedPoint(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AwardedPointDTO awardedPointDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AwardedPoint : {}, {}", id, awardedPointDTO);
        if (awardedPointDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, awardedPointDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!awardedPointRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AwardedPointDTO result = awardedPointService.update(awardedPointDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, awardedPointDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /awarded-points/:id} : Partial updates given fields of an existing awardedPoint, field will ignore if it is null
     *
     * @param id the id of the awardedPointDTO to save.
     * @param awardedPointDTO the awardedPointDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated awardedPointDTO,
     * or with status {@code 400 (Bad Request)} if the awardedPointDTO is not valid,
     * or with status {@code 404 (Not Found)} if the awardedPointDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the awardedPointDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/awarded-points/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AwardedPointDTO> partialUpdateAwardedPoint(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AwardedPointDTO awardedPointDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AwardedPoint partially : {}, {}", id, awardedPointDTO);
        if (awardedPointDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, awardedPointDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!awardedPointRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AwardedPointDTO> result = awardedPointService.partialUpdate(awardedPointDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, awardedPointDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /awarded-points} : get all the awardedPoints.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of awardedPoints in body.
     */
    @GetMapping("/awarded-points")
    public List<AwardedPointDTO> getAllAwardedPoints() {
        log.debug("REST request to get all AwardedPoints");
        return awardedPointService.findAll();
    }

    /**
     * {@code GET  /awarded-points/:id} : get the "id" awardedPoint.
     *
     * @param id the id of the awardedPointDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the awardedPointDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/awarded-points/{id}")
    public ResponseEntity<AwardedPointDTO> getAwardedPoint(@PathVariable Long id) {
        log.debug("REST request to get AwardedPoint : {}", id);
        Optional<AwardedPointDTO> awardedPointDTO = awardedPointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(awardedPointDTO);
    }

    /**
     * {@code DELETE  /awarded-points/:id} : delete the "id" awardedPoint.
     *
     * @param id the id of the awardedPointDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/awarded-points/{id}")
    public ResponseEntity<Void> deleteAwardedPoint(@PathVariable Long id) {
        log.debug("REST request to delete AwardedPoint : {}", id);
        awardedPointService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
