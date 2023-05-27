package org.contextmapper.generated.usermanagementcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.usermanagementcontext.repository.RegisterCommandRepository;
import org.contextmapper.generated.usermanagementcontext.service.RegisterCommandService;
import org.contextmapper.generated.usermanagementcontext.service.dto.RegisterCommandDTO;
import org.contextmapper.generated.usermanagementcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.usermanagementcontext.domain.RegisterCommand}.
 */
@RestController
@RequestMapping("/api")
public class RegisterCommandResource {

    private final Logger log = LoggerFactory.getLogger(RegisterCommandResource.class);

    private static final String ENTITY_NAME = "userManagementContextRegisterCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RegisterCommandService registerCommandService;

    private final RegisterCommandRepository registerCommandRepository;

    public RegisterCommandResource(RegisterCommandService registerCommandService, RegisterCommandRepository registerCommandRepository) {
        this.registerCommandService = registerCommandService;
        this.registerCommandRepository = registerCommandRepository;
    }

    /**
     * {@code POST  /register-commands} : Create a new registerCommand.
     *
     * @param registerCommandDTO the registerCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new registerCommandDTO, or with status {@code 400 (Bad Request)} if the registerCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/register-commands")
    public ResponseEntity<RegisterCommandDTO> createRegisterCommand(@RequestBody RegisterCommandDTO registerCommandDTO)
        throws URISyntaxException {
        log.debug("REST request to save RegisterCommand : {}", registerCommandDTO);
        if (registerCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new registerCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RegisterCommandDTO result = registerCommandService.save(registerCommandDTO);
        return ResponseEntity
            .created(new URI("/api/register-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /register-commands/:id} : Updates an existing registerCommand.
     *
     * @param id the id of the registerCommandDTO to save.
     * @param registerCommandDTO the registerCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated registerCommandDTO,
     * or with status {@code 400 (Bad Request)} if the registerCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the registerCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/register-commands/{id}")
    public ResponseEntity<RegisterCommandDTO> updateRegisterCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RegisterCommandDTO registerCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RegisterCommand : {}, {}", id, registerCommandDTO);
        if (registerCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, registerCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!registerCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RegisterCommandDTO result = registerCommandService.update(registerCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, registerCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /register-commands/:id} : Partial updates given fields of an existing registerCommand, field will ignore if it is null
     *
     * @param id the id of the registerCommandDTO to save.
     * @param registerCommandDTO the registerCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated registerCommandDTO,
     * or with status {@code 400 (Bad Request)} if the registerCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the registerCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the registerCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/register-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RegisterCommandDTO> partialUpdateRegisterCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RegisterCommandDTO registerCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RegisterCommand partially : {}, {}", id, registerCommandDTO);
        if (registerCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, registerCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!registerCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RegisterCommandDTO> result = registerCommandService.partialUpdate(registerCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, registerCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /register-commands} : get all the registerCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of registerCommands in body.
     */
    @GetMapping("/register-commands")
    public List<RegisterCommandDTO> getAllRegisterCommands() {
        log.debug("REST request to get all RegisterCommands");
        return registerCommandService.findAll();
    }

    /**
     * {@code GET  /register-commands/:id} : get the "id" registerCommand.
     *
     * @param id the id of the registerCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the registerCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/register-commands/{id}")
    public ResponseEntity<RegisterCommandDTO> getRegisterCommand(@PathVariable Long id) {
        log.debug("REST request to get RegisterCommand : {}", id);
        Optional<RegisterCommandDTO> registerCommandDTO = registerCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(registerCommandDTO);
    }

    /**
     * {@code DELETE  /register-commands/:id} : delete the "id" registerCommand.
     *
     * @param id the id of the registerCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/register-commands/{id}")
    public ResponseEntity<Void> deleteRegisterCommand(@PathVariable Long id) {
        log.debug("REST request to delete RegisterCommand : {}", id);
        registerCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
