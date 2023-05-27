package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.repository.ValidateResourceTagLinkageCommandRepository;
import org.contextmapper.generated.questioncontext.service.ValidateResourceTagLinkageCommandService;
import org.contextmapper.generated.questioncontext.service.dto.ValidateResourceTagLinkageCommandDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.ValidateResourceTagLinkageCommand}.
 */
@RestController
@RequestMapping("/api")
public class ValidateResourceTagLinkageCommandResource {

    private final Logger log = LoggerFactory.getLogger(ValidateResourceTagLinkageCommandResource.class);

    private static final String ENTITY_NAME = "questionContextValidateResourceTagLinkageCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ValidateResourceTagLinkageCommandService validateResourceTagLinkageCommandService;

    private final ValidateResourceTagLinkageCommandRepository validateResourceTagLinkageCommandRepository;

    public ValidateResourceTagLinkageCommandResource(
        ValidateResourceTagLinkageCommandService validateResourceTagLinkageCommandService,
        ValidateResourceTagLinkageCommandRepository validateResourceTagLinkageCommandRepository
    ) {
        this.validateResourceTagLinkageCommandService = validateResourceTagLinkageCommandService;
        this.validateResourceTagLinkageCommandRepository = validateResourceTagLinkageCommandRepository;
    }

    /**
     * {@code POST  /validate-resource-tag-linkage-commands} : Create a new validateResourceTagLinkageCommand.
     *
     * @param validateResourceTagLinkageCommandDTO the validateResourceTagLinkageCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new validateResourceTagLinkageCommandDTO, or with status {@code 400 (Bad Request)} if the validateResourceTagLinkageCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/validate-resource-tag-linkage-commands")
    public ResponseEntity<ValidateResourceTagLinkageCommandDTO> createValidateResourceTagLinkageCommand(
        @RequestBody ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ValidateResourceTagLinkageCommand : {}", validateResourceTagLinkageCommandDTO);
        if (validateResourceTagLinkageCommandDTO.getId() != null) {
            throw new BadRequestAlertException(
                "A new validateResourceTagLinkageCommand cannot already have an ID",
                ENTITY_NAME,
                "idexists"
            );
        }
        ValidateResourceTagLinkageCommandDTO result = validateResourceTagLinkageCommandService.save(validateResourceTagLinkageCommandDTO);
        return ResponseEntity
            .created(new URI("/api/validate-resource-tag-linkage-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /validate-resource-tag-linkage-commands/:id} : Updates an existing validateResourceTagLinkageCommand.
     *
     * @param id the id of the validateResourceTagLinkageCommandDTO to save.
     * @param validateResourceTagLinkageCommandDTO the validateResourceTagLinkageCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated validateResourceTagLinkageCommandDTO,
     * or with status {@code 400 (Bad Request)} if the validateResourceTagLinkageCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the validateResourceTagLinkageCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/validate-resource-tag-linkage-commands/{id}")
    public ResponseEntity<ValidateResourceTagLinkageCommandDTO> updateValidateResourceTagLinkageCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ValidateResourceTagLinkageCommand : {}, {}", id, validateResourceTagLinkageCommandDTO);
        if (validateResourceTagLinkageCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, validateResourceTagLinkageCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!validateResourceTagLinkageCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ValidateResourceTagLinkageCommandDTO result = validateResourceTagLinkageCommandService.update(validateResourceTagLinkageCommandDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    true,
                    ENTITY_NAME,
                    validateResourceTagLinkageCommandDTO.getId().toString()
                )
            )
            .body(result);
    }

    /**
     * {@code PATCH  /validate-resource-tag-linkage-commands/:id} : Partial updates given fields of an existing validateResourceTagLinkageCommand, field will ignore if it is null
     *
     * @param id the id of the validateResourceTagLinkageCommandDTO to save.
     * @param validateResourceTagLinkageCommandDTO the validateResourceTagLinkageCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated validateResourceTagLinkageCommandDTO,
     * or with status {@code 400 (Bad Request)} if the validateResourceTagLinkageCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the validateResourceTagLinkageCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the validateResourceTagLinkageCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/validate-resource-tag-linkage-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ValidateResourceTagLinkageCommandDTO> partialUpdateValidateResourceTagLinkageCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ValidateResourceTagLinkageCommandDTO validateResourceTagLinkageCommandDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update ValidateResourceTagLinkageCommand partially : {}, {}",
            id,
            validateResourceTagLinkageCommandDTO
        );
        if (validateResourceTagLinkageCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, validateResourceTagLinkageCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!validateResourceTagLinkageCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ValidateResourceTagLinkageCommandDTO> result = validateResourceTagLinkageCommandService.partialUpdate(
            validateResourceTagLinkageCommandDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, validateResourceTagLinkageCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /validate-resource-tag-linkage-commands} : get all the validateResourceTagLinkageCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of validateResourceTagLinkageCommands in body.
     */
    @GetMapping("/validate-resource-tag-linkage-commands")
    public List<ValidateResourceTagLinkageCommandDTO> getAllValidateResourceTagLinkageCommands() {
        log.debug("REST request to get all ValidateResourceTagLinkageCommands");
        return validateResourceTagLinkageCommandService.findAll();
    }

    /**
     * {@code GET  /validate-resource-tag-linkage-commands/:id} : get the "id" validateResourceTagLinkageCommand.
     *
     * @param id the id of the validateResourceTagLinkageCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the validateResourceTagLinkageCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/validate-resource-tag-linkage-commands/{id}")
    public ResponseEntity<ValidateResourceTagLinkageCommandDTO> getValidateResourceTagLinkageCommand(@PathVariable Long id) {
        log.debug("REST request to get ValidateResourceTagLinkageCommand : {}", id);
        Optional<ValidateResourceTagLinkageCommandDTO> validateResourceTagLinkageCommandDTO = validateResourceTagLinkageCommandService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(validateResourceTagLinkageCommandDTO);
    }

    /**
     * {@code DELETE  /validate-resource-tag-linkage-commands/:id} : delete the "id" validateResourceTagLinkageCommand.
     *
     * @param id the id of the validateResourceTagLinkageCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/validate-resource-tag-linkage-commands/{id}")
    public ResponseEntity<Void> deleteValidateResourceTagLinkageCommand(@PathVariable Long id) {
        log.debug("REST request to delete ValidateResourceTagLinkageCommand : {}", id);
        validateResourceTagLinkageCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
