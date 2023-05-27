package org.contextmapper.generated.usermanagementcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.usermanagementcontext.repository.ValidateUserCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.ValidateUserCommandService;
import org.contextmapper.generated.usermanagementcontext.service.dto.ValidateUserCommandDTO;
import org.contextmapper.generated.usermanagementcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.usermanagementcontext.domain.ValidateUserCommand}.
 */
@RestController
@RequestMapping("/api")
public class ValidateUserCommandResource {

    private final Logger log = LoggerFactory.getLogger(ValidateUserCommandResource.class);

    private static final String ENTITY_NAME = "userManagementContextValidateUserCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ValidateUserCommandService validateUserCommandService;

    private final ValidateUserCommandRepository validateUserCommandRepository;

    public ValidateUserCommandResource(
        ValidateUserCommandService validateUserCommandService,
        ValidateUserCommandRepository validateUserCommandRepository
    ) {
        this.validateUserCommandService = validateUserCommandService;
        this.validateUserCommandRepository = validateUserCommandRepository;
    }

    /**
     * {@code POST  /validate-user-commands} : Create a new validateUserCommand.
     *
     * @param validateUserCommandDTO the validateUserCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new validateUserCommandDTO, or with status {@code 400 (Bad Request)} if the validateUserCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/validate-user-commands")
    public ResponseEntity<ValidateUserCommandDTO> createValidateUserCommand(@RequestBody ValidateUserCommandDTO validateUserCommandDTO)
        throws URISyntaxException {
        log.debug("REST request to save ValidateUserCommand : {}", validateUserCommandDTO);
        if (validateUserCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new validateUserCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ValidateUserCommandDTO result = validateUserCommandService.save(validateUserCommandDTO);
        return ResponseEntity
            .created(new URI("/api/validate-user-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /validate-user-commands/:id} : Updates an existing validateUserCommand.
     *
     * @param id the id of the validateUserCommandDTO to save.
     * @param validateUserCommandDTO the validateUserCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated validateUserCommandDTO,
     * or with status {@code 400 (Bad Request)} if the validateUserCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the validateUserCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/validate-user-commands/{id}")
    public ResponseEntity<ValidateUserCommandDTO> updateValidateUserCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ValidateUserCommandDTO validateUserCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ValidateUserCommand : {}, {}", id, validateUserCommandDTO);
        if (validateUserCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, validateUserCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!validateUserCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ValidateUserCommandDTO result = validateUserCommandService.update(validateUserCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, validateUserCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /validate-user-commands/:id} : Partial updates given fields of an existing validateUserCommand, field will ignore if it is null
     *
     * @param id the id of the validateUserCommandDTO to save.
     * @param validateUserCommandDTO the validateUserCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated validateUserCommandDTO,
     * or with status {@code 400 (Bad Request)} if the validateUserCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the validateUserCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the validateUserCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/validate-user-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ValidateUserCommandDTO> partialUpdateValidateUserCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ValidateUserCommandDTO validateUserCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ValidateUserCommand partially : {}, {}", id, validateUserCommandDTO);
        if (validateUserCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, validateUserCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!validateUserCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ValidateUserCommandDTO> result = validateUserCommandService.partialUpdate(validateUserCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, validateUserCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /validate-user-commands} : get all the validateUserCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of validateUserCommands in body.
     */
    @GetMapping("/validate-user-commands")
    public List<ValidateUserCommandDTO> getAllValidateUserCommands() {
        log.debug("REST request to get all ValidateUserCommands");
        return validateUserCommandService.findAll();
    }

    /**
     * {@code GET  /validate-user-commands/:id} : get the "id" validateUserCommand.
     *
     * @param id the id of the validateUserCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the validateUserCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/validate-user-commands/{id}")
    public ResponseEntity<ValidateUserCommandDTO> getValidateUserCommand(@PathVariable Long id) {
        log.debug("REST request to get ValidateUserCommand : {}", id);
        Optional<ValidateUserCommandDTO> validateUserCommandDTO = validateUserCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(validateUserCommandDTO);
    }

    /**
     * {@code DELETE  /validate-user-commands/:id} : delete the "id" validateUserCommand.
     *
     * @param id the id of the validateUserCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/validate-user-commands/{id}")
    public ResponseEntity<Void> deleteValidateUserCommand(@PathVariable Long id) {
        log.debug("REST request to delete ValidateUserCommand : {}", id);
        validateUserCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
