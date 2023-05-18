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

    private final CategoryIdService categoryIdService;

    private final CategoryIdRepository categoryIdRepository;

    public CategoryIdResource(CategoryIdService categoryIdService, CategoryIdRepository categoryIdRepository) {
        this.categoryIdService = categoryIdService;
        this.categoryIdRepository = categoryIdRepository;
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
}
