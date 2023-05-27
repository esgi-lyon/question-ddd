package org.contextmapper.generated.skillcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.repository.CreateTagCommandRepository;
import org.contextmapper.generated.skillcontext.service.CreateTagCommandService;
import org.contextmapper.generated.skillcontext.service.dto.CreateTagCommandDTO;
import org.contextmapper.generated.skillcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.skillcontext.domain.CreateTagCommand}.
 */
@RestController
@RequestMapping("/api")
public class CreateTagCommandResource {

    private final Logger log = LoggerFactory.getLogger(CreateTagCommandResource.class);

    private static final String ENTITY_NAME = "skillContextCreateTagCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreateTagCommandService createTagCommandService;

    private final CreateTagCommandRepository createTagCommandRepository;

    public CreateTagCommandResource(
        CreateTagCommandService createTagCommandService,
        CreateTagCommandRepository createTagCommandRepository
    ) {
        this.createTagCommandService = createTagCommandService;
        this.createTagCommandRepository = createTagCommandRepository;
    }

    /**
     * {@code POST  /create-tag-commands} : Create a new createTagCommand.
     *
     * @param createTagCommandDTO the createTagCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createTagCommandDTO, or with status {@code 400 (Bad Request)} if the createTagCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-tag-commands")
    public ResponseEntity<CreateTagCommandDTO> createCreateTagCommand(@RequestBody CreateTagCommandDTO createTagCommandDTO)
        throws URISyntaxException {
        log.debug("REST request to save CreateTagCommand : {}", createTagCommandDTO);
        if (createTagCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new createTagCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreateTagCommandDTO result = createTagCommandService.save(createTagCommandDTO);
        return ResponseEntity
            .created(new URI("/api/create-tag-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /create-tag-commands/:id} : Updates an existing createTagCommand.
     *
     * @param id the id of the createTagCommandDTO to save.
     * @param createTagCommandDTO the createTagCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createTagCommandDTO,
     * or with status {@code 400 (Bad Request)} if the createTagCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the createTagCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/create-tag-commands/{id}")
    public ResponseEntity<CreateTagCommandDTO> updateCreateTagCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateTagCommandDTO createTagCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CreateTagCommand : {}, {}", id, createTagCommandDTO);
        if (createTagCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createTagCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createTagCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CreateTagCommandDTO result = createTagCommandService.update(createTagCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createTagCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /create-tag-commands/:id} : Partial updates given fields of an existing createTagCommand, field will ignore if it is null
     *
     * @param id the id of the createTagCommandDTO to save.
     * @param createTagCommandDTO the createTagCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createTagCommandDTO,
     * or with status {@code 400 (Bad Request)} if the createTagCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the createTagCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the createTagCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/create-tag-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CreateTagCommandDTO> partialUpdateCreateTagCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateTagCommandDTO createTagCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreateTagCommand partially : {}, {}", id, createTagCommandDTO);
        if (createTagCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createTagCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createTagCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CreateTagCommandDTO> result = createTagCommandService.partialUpdate(createTagCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createTagCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /create-tag-commands} : get all the createTagCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createTagCommands in body.
     */
    @GetMapping("/create-tag-commands")
    public List<CreateTagCommandDTO> getAllCreateTagCommands() {
        log.debug("REST request to get all CreateTagCommands");
        return createTagCommandService.findAll();
    }

    /**
     * {@code GET  /create-tag-commands/:id} : get the "id" createTagCommand.
     *
     * @param id the id of the createTagCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createTagCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/create-tag-commands/{id}")
    public ResponseEntity<CreateTagCommandDTO> getCreateTagCommand(@PathVariable Long id) {
        log.debug("REST request to get CreateTagCommand : {}", id);
        Optional<CreateTagCommandDTO> createTagCommandDTO = createTagCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createTagCommandDTO);
    }

    /**
     * {@code DELETE  /create-tag-commands/:id} : delete the "id" createTagCommand.
     *
     * @param id the id of the createTagCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/create-tag-commands/{id}")
    public ResponseEntity<Void> deleteCreateTagCommand(@PathVariable Long id) {
        log.debug("REST request to delete CreateTagCommand : {}", id);
        createTagCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
