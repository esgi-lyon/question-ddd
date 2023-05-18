package org.contextmapper.generated.skillcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.repository.CategoryCreatedRepository;
import org.contextmapper.generated.skillcontext.service.CategoryCreatedService;
import org.contextmapper.generated.skillcontext.service.dto.CategoryCreatedDTO;
import org.contextmapper.generated.skillcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.skillcontext.domain.CategoryCreated}.
 */
@RestController
@RequestMapping("/api")
public class CategoryCreatedResource {

    private final Logger log = LoggerFactory.getLogger(CategoryCreatedResource.class);

    private static final String ENTITY_NAME = "skillContextCategoryCreated";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoryCreatedService categoryCreatedService;

    private final CategoryCreatedRepository categoryCreatedRepository;

    public CategoryCreatedResource(CategoryCreatedService categoryCreatedService, CategoryCreatedRepository categoryCreatedRepository) {
        this.categoryCreatedService = categoryCreatedService;
        this.categoryCreatedRepository = categoryCreatedRepository;
    }

    /**
     * {@code POST  /category-createds} : Create a new categoryCreated.
     *
     * @param categoryCreatedDTO the categoryCreatedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoryCreatedDTO, or with status {@code 400 (Bad Request)} if the categoryCreated has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/category-createds")
    public ResponseEntity<CategoryCreatedDTO> createCategoryCreated(@RequestBody CategoryCreatedDTO categoryCreatedDTO)
        throws URISyntaxException {
        log.debug("REST request to save CategoryCreated : {}", categoryCreatedDTO);
        if (categoryCreatedDTO.getId() != null) {
            throw new BadRequestAlertException("A new categoryCreated cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoryCreatedDTO result = categoryCreatedService.save(categoryCreatedDTO);
        return ResponseEntity
            .created(new URI("/api/category-createds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /category-createds/:id} : Updates an existing categoryCreated.
     *
     * @param id the id of the categoryCreatedDTO to save.
     * @param categoryCreatedDTO the categoryCreatedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryCreatedDTO,
     * or with status {@code 400 (Bad Request)} if the categoryCreatedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoryCreatedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/category-createds/{id}")
    public ResponseEntity<CategoryCreatedDTO> updateCategoryCreated(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CategoryCreatedDTO categoryCreatedDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CategoryCreated : {}, {}", id, categoryCreatedDTO);
        if (categoryCreatedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoryCreatedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoryCreatedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CategoryCreatedDTO result = categoryCreatedService.update(categoryCreatedDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoryCreatedDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /category-createds/:id} : Partial updates given fields of an existing categoryCreated, field will ignore if it is null
     *
     * @param id the id of the categoryCreatedDTO to save.
     * @param categoryCreatedDTO the categoryCreatedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryCreatedDTO,
     * or with status {@code 400 (Bad Request)} if the categoryCreatedDTO is not valid,
     * or with status {@code 404 (Not Found)} if the categoryCreatedDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the categoryCreatedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/category-createds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CategoryCreatedDTO> partialUpdateCategoryCreated(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CategoryCreatedDTO categoryCreatedDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CategoryCreated partially : {}, {}", id, categoryCreatedDTO);
        if (categoryCreatedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoryCreatedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoryCreatedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CategoryCreatedDTO> result = categoryCreatedService.partialUpdate(categoryCreatedDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoryCreatedDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /category-createds} : get all the categoryCreateds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoryCreateds in body.
     */
    @GetMapping("/category-createds")
    public List<CategoryCreatedDTO> getAllCategoryCreateds() {
        log.debug("REST request to get all CategoryCreateds");
        return categoryCreatedService.findAll();
    }

    /**
     * {@code GET  /category-createds/:id} : get the "id" categoryCreated.
     *
     * @param id the id of the categoryCreatedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryCreatedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/category-createds/{id}")
    public ResponseEntity<CategoryCreatedDTO> getCategoryCreated(@PathVariable Long id) {
        log.debug("REST request to get CategoryCreated : {}", id);
        Optional<CategoryCreatedDTO> categoryCreatedDTO = categoryCreatedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryCreatedDTO);
    }

    /**
     * {@code DELETE  /category-createds/:id} : delete the "id" categoryCreated.
     *
     * @param id the id of the categoryCreatedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/category-createds/{id}")
    public ResponseEntity<Void> deleteCategoryCreated(@PathVariable Long id) {
        log.debug("REST request to delete CategoryCreated : {}", id);
        categoryCreatedService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
