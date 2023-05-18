package org.contextmapper.generated.skillcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.repository.CreateCategoryRepository;
import org.contextmapper.generated.skillcontext.service.CreateCategoryService;
import org.contextmapper.generated.skillcontext.service.dto.CreateCategoryDTO;
import org.contextmapper.generated.skillcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.skillcontext.domain.CreateCategory}.
 */
@RestController
@RequestMapping("/api")
public class CreateCategoryResource {

    private final Logger log = LoggerFactory.getLogger(CreateCategoryResource.class);

    private static final String ENTITY_NAME = "skillContextCreateCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreateCategoryService createCategoryService;

    private final CreateCategoryRepository createCategoryRepository;

    public CreateCategoryResource(CreateCategoryService createCategoryService, CreateCategoryRepository createCategoryRepository) {
        this.createCategoryService = createCategoryService;
        this.createCategoryRepository = createCategoryRepository;
    }

    /**
     * {@code POST  /create-categories} : Create a new createCategory.
     *
     * @param createCategoryDTO the createCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createCategoryDTO, or with status {@code 400 (Bad Request)} if the createCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-categories")
    public ResponseEntity<CreateCategoryDTO> createCreateCategory(@RequestBody CreateCategoryDTO createCategoryDTO)
        throws URISyntaxException {
        log.debug("REST request to save CreateCategory : {}", createCategoryDTO);
        if (createCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new createCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreateCategoryDTO result = createCategoryService.save(createCategoryDTO);
        return ResponseEntity
            .created(new URI("/api/create-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /create-categories/:id} : Updates an existing createCategory.
     *
     * @param id the id of the createCategoryDTO to save.
     * @param createCategoryDTO the createCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the createCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the createCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/create-categories/{id}")
    public ResponseEntity<CreateCategoryDTO> updateCreateCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateCategoryDTO createCategoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CreateCategory : {}, {}", id, createCategoryDTO);
        if (createCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createCategoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CreateCategoryDTO result = createCategoryService.update(createCategoryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /create-categories/:id} : Partial updates given fields of an existing createCategory, field will ignore if it is null
     *
     * @param id the id of the createCategoryDTO to save.
     * @param createCategoryDTO the createCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the createCategoryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the createCategoryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the createCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/create-categories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CreateCategoryDTO> partialUpdateCreateCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateCategoryDTO createCategoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreateCategory partially : {}, {}", id, createCategoryDTO);
        if (createCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createCategoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CreateCategoryDTO> result = createCategoryService.partialUpdate(createCategoryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createCategoryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /create-categories} : get all the createCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createCategories in body.
     */
    @GetMapping("/create-categories")
    public List<CreateCategoryDTO> getAllCreateCategories() {
        log.debug("REST request to get all CreateCategories");
        return createCategoryService.findAll();
    }

    /**
     * {@code GET  /create-categories/:id} : get the "id" createCategory.
     *
     * @param id the id of the createCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/create-categories/{id}")
    public ResponseEntity<CreateCategoryDTO> getCreateCategory(@PathVariable Long id) {
        log.debug("REST request to get CreateCategory : {}", id);
        Optional<CreateCategoryDTO> createCategoryDTO = createCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createCategoryDTO);
    }

    /**
     * {@code DELETE  /create-categories/:id} : delete the "id" createCategory.
     *
     * @param id the id of the createCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/create-categories/{id}")
    public ResponseEntity<Void> deleteCreateCategory(@PathVariable Long id) {
        log.debug("REST request to delete CreateCategory : {}", id);
        createCategoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
