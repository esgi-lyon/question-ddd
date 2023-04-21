package org.contextmapper.generated.skillcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.repository.CategoryIdRepository;
import org.contextmapper.generated.skillcontext.service.CategoryIdService;
import org.contextmapper.generated.skillcontext.service.dto.CategoryIdDTO;
import org.contextmapper.generated.skillcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.skillcontext.domain.CategoryId}.
 */
@RestController
@RequestMapping("/api")
public class CategoryIdResource {

    private final Logger log = LoggerFactory.getLogger(CategoryIdResource.class);

    private static final String ENTITY_NAME = "skillContextCategoryId";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoryIdService categoryIdService;

    private final CategoryIdRepository categoryIdRepository;

    public CategoryIdResource(CategoryIdService categoryIdService, CategoryIdRepository categoryIdRepository) {
        this.categoryIdService = categoryIdService;
        this.categoryIdRepository = categoryIdRepository;
    }

    /**
     * {@code POST  /category-ids} : Create a new categoryId.
     *
     * @param categoryIdDTO the categoryIdDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoryIdDTO, or with status {@code 400 (Bad Request)} if the categoryId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/category-ids")
    public ResponseEntity<CategoryIdDTO> createCategoryId(@RequestBody CategoryIdDTO categoryIdDTO) throws URISyntaxException {
        log.debug("REST request to save CategoryId : {}", categoryIdDTO);
        if (categoryIdDTO.getId() != null) {
            throw new BadRequestAlertException("A new categoryId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoryIdDTO result = categoryIdService.save(categoryIdDTO);
        return ResponseEntity
            .created(new URI("/api/category-ids/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /category-ids/:id} : Updates an existing categoryId.
     *
     * @param id the id of the categoryIdDTO to save.
     * @param categoryIdDTO the categoryIdDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryIdDTO,
     * or with status {@code 400 (Bad Request)} if the categoryIdDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoryIdDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/category-ids/{id}")
    public ResponseEntity<CategoryIdDTO> updateCategoryId(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CategoryIdDTO categoryIdDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CategoryId : {}, {}", id, categoryIdDTO);
        if (categoryIdDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoryIdDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoryIdRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CategoryIdDTO result = categoryIdService.update(categoryIdDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoryIdDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /category-ids/:id} : Partial updates given fields of an existing categoryId, field will ignore if it is null
     *
     * @param id the id of the categoryIdDTO to save.
     * @param categoryIdDTO the categoryIdDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryIdDTO,
     * or with status {@code 400 (Bad Request)} if the categoryIdDTO is not valid,
     * or with status {@code 404 (Not Found)} if the categoryIdDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the categoryIdDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/category-ids/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CategoryIdDTO> partialUpdateCategoryId(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CategoryIdDTO categoryIdDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CategoryId partially : {}, {}", id, categoryIdDTO);
        if (categoryIdDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoryIdDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoryIdRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CategoryIdDTO> result = categoryIdService.partialUpdate(categoryIdDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoryIdDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /category-ids} : get all the categoryIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoryIds in body.
     */
    @GetMapping("/category-ids")
    public List<CategoryIdDTO> getAllCategoryIds() {
        log.debug("REST request to get all CategoryIds");
        return categoryIdService.findAll();
    }

    /**
     * {@code GET  /category-ids/:id} : get the "id" categoryId.
     *
     * @param id the id of the categoryIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/category-ids/{id}")
    public ResponseEntity<CategoryIdDTO> getCategoryId(@PathVariable Long id) {
        log.debug("REST request to get CategoryId : {}", id);
        Optional<CategoryIdDTO> categoryIdDTO = categoryIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryIdDTO);
    }

    /**
     * {@code DELETE  /category-ids/:id} : delete the "id" categoryId.
     *
     * @param id the id of the categoryIdDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/category-ids/{id}")
    public ResponseEntity<Void> deleteCategoryId(@PathVariable Long id) {
        log.debug("REST request to delete CategoryId : {}", id);
        categoryIdService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
