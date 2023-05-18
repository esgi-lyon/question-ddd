package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.repository.ValidateResourceTagLinkageRepository;
import org.contextmapper.generated.questioncontext.service.ValidateResourceTagLinkageService;
import org.contextmapper.generated.questioncontext.service.dto.ValidateResourceTagLinkageDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.ValidateResourceTagLinkage}.
 */
@RestController
@RequestMapping("/api")
public class ValidateResourceTagLinkageResource {

    private final Logger log = LoggerFactory.getLogger(ValidateResourceTagLinkageResource.class);

    private static final String ENTITY_NAME = "questionContextValidateResourceTagLinkage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ValidateResourceTagLinkageService validateResourceTagLinkageService;

    private final ValidateResourceTagLinkageRepository validateResourceTagLinkageRepository;

    public ValidateResourceTagLinkageResource(
        ValidateResourceTagLinkageService validateResourceTagLinkageService,
        ValidateResourceTagLinkageRepository validateResourceTagLinkageRepository
    ) {
        this.validateResourceTagLinkageService = validateResourceTagLinkageService;
        this.validateResourceTagLinkageRepository = validateResourceTagLinkageRepository;
    }

    /**
     * {@code POST  /validate-resource-tag-linkages} : Create a new validateResourceTagLinkage.
     *
     * @param validateResourceTagLinkageDTO the validateResourceTagLinkageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new validateResourceTagLinkageDTO, or with status {@code 400 (Bad Request)} if the validateResourceTagLinkage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/validate-resource-tag-linkages")
    public ResponseEntity<ValidateResourceTagLinkageDTO> createValidateResourceTagLinkage(
        @RequestBody ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ValidateResourceTagLinkage : {}", validateResourceTagLinkageDTO);
        if (validateResourceTagLinkageDTO.getId() != null) {
            throw new BadRequestAlertException("A new validateResourceTagLinkage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ValidateResourceTagLinkageDTO result = validateResourceTagLinkageService.save(validateResourceTagLinkageDTO);
        return ResponseEntity
            .created(new URI("/api/validate-resource-tag-linkages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /validate-resource-tag-linkages/:id} : Updates an existing validateResourceTagLinkage.
     *
     * @param id the id of the validateResourceTagLinkageDTO to save.
     * @param validateResourceTagLinkageDTO the validateResourceTagLinkageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated validateResourceTagLinkageDTO,
     * or with status {@code 400 (Bad Request)} if the validateResourceTagLinkageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the validateResourceTagLinkageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/validate-resource-tag-linkages/{id}")
    public ResponseEntity<ValidateResourceTagLinkageDTO> updateValidateResourceTagLinkage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ValidateResourceTagLinkage : {}, {}", id, validateResourceTagLinkageDTO);
        if (validateResourceTagLinkageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, validateResourceTagLinkageDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!validateResourceTagLinkageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ValidateResourceTagLinkageDTO result = validateResourceTagLinkageService.update(validateResourceTagLinkageDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, validateResourceTagLinkageDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /validate-resource-tag-linkages/:id} : Partial updates given fields of an existing validateResourceTagLinkage, field will ignore if it is null
     *
     * @param id the id of the validateResourceTagLinkageDTO to save.
     * @param validateResourceTagLinkageDTO the validateResourceTagLinkageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated validateResourceTagLinkageDTO,
     * or with status {@code 400 (Bad Request)} if the validateResourceTagLinkageDTO is not valid,
     * or with status {@code 404 (Not Found)} if the validateResourceTagLinkageDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the validateResourceTagLinkageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/validate-resource-tag-linkages/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ValidateResourceTagLinkageDTO> partialUpdateValidateResourceTagLinkage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ValidateResourceTagLinkageDTO validateResourceTagLinkageDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ValidateResourceTagLinkage partially : {}, {}", id, validateResourceTagLinkageDTO);
        if (validateResourceTagLinkageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, validateResourceTagLinkageDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!validateResourceTagLinkageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ValidateResourceTagLinkageDTO> result = validateResourceTagLinkageService.partialUpdate(validateResourceTagLinkageDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, validateResourceTagLinkageDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /validate-resource-tag-linkages} : get all the validateResourceTagLinkages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of validateResourceTagLinkages in body.
     */
    @GetMapping("/validate-resource-tag-linkages")
    public List<ValidateResourceTagLinkageDTO> getAllValidateResourceTagLinkages() {
        log.debug("REST request to get all ValidateResourceTagLinkages");
        return validateResourceTagLinkageService.findAll();
    }

    /**
     * {@code GET  /validate-resource-tag-linkages/:id} : get the "id" validateResourceTagLinkage.
     *
     * @param id the id of the validateResourceTagLinkageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the validateResourceTagLinkageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/validate-resource-tag-linkages/{id}")
    public ResponseEntity<ValidateResourceTagLinkageDTO> getValidateResourceTagLinkage(@PathVariable Long id) {
        log.debug("REST request to get ValidateResourceTagLinkage : {}", id);
        Optional<ValidateResourceTagLinkageDTO> validateResourceTagLinkageDTO = validateResourceTagLinkageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(validateResourceTagLinkageDTO);
    }

    /**
     * {@code DELETE  /validate-resource-tag-linkages/:id} : delete the "id" validateResourceTagLinkage.
     *
     * @param id the id of the validateResourceTagLinkageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/validate-resource-tag-linkages/{id}")
    public ResponseEntity<Void> deleteValidateResourceTagLinkage(@PathVariable Long id) {
        log.debug("REST request to delete ValidateResourceTagLinkage : {}", id);
        validateResourceTagLinkageService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
