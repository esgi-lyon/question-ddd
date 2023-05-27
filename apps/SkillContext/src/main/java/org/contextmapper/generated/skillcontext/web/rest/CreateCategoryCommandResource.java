package org.contextmapper.generated.skillcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.repository.CreateCategoryCommandRepository;
import org.contextmapper.generated.skillcontext.service.CreateCategoryCommandService;
import org.contextmapper.generated.skillcontext.service.dto.CreateCategoryCommandDTO;
import org.contextmapper.generated.skillcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.skillcontext.domain.CreateCategoryCommand}.
 */
@RestController
@RequestMapping("/api")
public class CreateCategoryCommandResource {

    private final Logger log = LoggerFactory.getLogger(CreateCategoryCommandResource.class);

    private static final String ENTITY_NAME = "skillContextCreateCategoryCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreateCategoryCommandService createCategoryCommandService;

    private final CreateCategoryCommandRepository createCategoryCommandRepository;

    public CreateCategoryCommandResource(
        CreateCategoryCommandService createCategoryCommandService,
        CreateCategoryCommandRepository createCategoryCommandRepository
    ) {
        this.createCategoryCommandService = createCategoryCommandService;
        this.createCategoryCommandRepository = createCategoryCommandRepository;
    }

    /**
     * {@code POST  /create-category-commands} : Create a new createCategoryCommand.
     *
     * @param createCategoryCommandDTO the createCategoryCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createCategoryCommandDTO, or with status {@code 400 (Bad Request)} if the createCategoryCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-category-commands")
    public ResponseEntity<CreateCategoryCommandDTO> createCreateCategoryCommand(
        @RequestBody CreateCategoryCommandDTO createCategoryCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to save CreateCategoryCommand : {}", createCategoryCommandDTO);
        if (createCategoryCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new createCategoryCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreateCategoryCommandDTO result = createCategoryCommandService.save(createCategoryCommandDTO);
        return ResponseEntity
            .created(new URI("/api/create-category-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /create-category-commands/:id} : Updates an existing createCategoryCommand.
     *
     * @param id the id of the createCategoryCommandDTO to save.
     * @param createCategoryCommandDTO the createCategoryCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createCategoryCommandDTO,
     * or with status {@code 400 (Bad Request)} if the createCategoryCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the createCategoryCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/create-category-commands/{id}")
    public ResponseEntity<CreateCategoryCommandDTO> updateCreateCategoryCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateCategoryCommandDTO createCategoryCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CreateCategoryCommand : {}, {}", id, createCategoryCommandDTO);
        if (createCategoryCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createCategoryCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createCategoryCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CreateCategoryCommandDTO result = createCategoryCommandService.update(createCategoryCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createCategoryCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /create-category-commands/:id} : Partial updates given fields of an existing createCategoryCommand, field will ignore if it is null
     *
     * @param id the id of the createCategoryCommandDTO to save.
     * @param createCategoryCommandDTO the createCategoryCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createCategoryCommandDTO,
     * or with status {@code 400 (Bad Request)} if the createCategoryCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the createCategoryCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the createCategoryCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/create-category-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CreateCategoryCommandDTO> partialUpdateCreateCategoryCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateCategoryCommandDTO createCategoryCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreateCategoryCommand partially : {}, {}", id, createCategoryCommandDTO);
        if (createCategoryCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createCategoryCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createCategoryCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CreateCategoryCommandDTO> result = createCategoryCommandService.partialUpdate(createCategoryCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createCategoryCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /create-category-commands} : get all the createCategoryCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createCategoryCommands in body.
     */
    @GetMapping("/create-category-commands")
    public List<CreateCategoryCommandDTO> getAllCreateCategoryCommands() {
        log.debug("REST request to get all CreateCategoryCommands");
        return createCategoryCommandService.findAll();
    }

    /**
     * {@code GET  /create-category-commands/:id} : get the "id" createCategoryCommand.
     *
     * @param id the id of the createCategoryCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createCategoryCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/create-category-commands/{id}")
    public ResponseEntity<CreateCategoryCommandDTO> getCreateCategoryCommand(@PathVariable Long id) {
        log.debug("REST request to get CreateCategoryCommand : {}", id);
        Optional<CreateCategoryCommandDTO> createCategoryCommandDTO = createCategoryCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createCategoryCommandDTO);
    }

    /**
     * {@code DELETE  /create-category-commands/:id} : delete the "id" createCategoryCommand.
     *
     * @param id the id of the createCategoryCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/create-category-commands/{id}")
    public ResponseEntity<Void> deleteCreateCategoryCommand(@PathVariable Long id) {
        log.debug("REST request to delete CreateCategoryCommand : {}", id);
        createCategoryCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
