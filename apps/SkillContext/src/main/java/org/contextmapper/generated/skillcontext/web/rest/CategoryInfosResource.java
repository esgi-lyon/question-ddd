package org.contextmapper.generated.skillcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.repository.CategoryInfosRepository;
import org.contextmapper.generated.skillcontext.service.CategoryInfosService;
import org.contextmapper.generated.skillcontext.service.dto.CategoryInfosDTO;
import org.contextmapper.generated.skillcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.skillcontext.domain.CategoryInfos}.
 */
@RestController
@RequestMapping("/api")
public class CategoryInfosResource {

    private final Logger log = LoggerFactory.getLogger(CategoryInfosResource.class);

    private final CategoryInfosService categoryInfosService;

    private final CategoryInfosRepository categoryInfosRepository;

    public CategoryInfosResource(CategoryInfosService categoryInfosService, CategoryInfosRepository categoryInfosRepository) {
        this.categoryInfosService = categoryInfosService;
        this.categoryInfosRepository = categoryInfosRepository;
    }

    /**
     * {@code GET  /category-infos} : get all the categoryInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoryInfos in body.
     */
    @GetMapping("/category-infos")
    public List<CategoryInfosDTO> getAllCategoryInfos() {
        log.debug("REST request to get all CategoryInfos");
        return categoryInfosService.findAll();
    }

    /**
     * {@code GET  /category-infos/:id} : get the "id" categoryInfos.
     *
     * @param id the id of the categoryInfosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryInfosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/category-infos/{id}")
    public ResponseEntity<CategoryInfosDTO> getCategoryInfos(@PathVariable Long id) {
        log.debug("REST request to get CategoryInfos : {}", id);
        Optional<CategoryInfosDTO> categoryInfosDTO = categoryInfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryInfosDTO);
    }
}
