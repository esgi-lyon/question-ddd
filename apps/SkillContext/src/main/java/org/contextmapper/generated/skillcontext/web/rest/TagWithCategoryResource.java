package org.contextmapper.generated.skillcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.repository.TagWithCategoryRepository;
import org.contextmapper.generated.skillcontext.service.TagWithCategoryService;
import org.contextmapper.generated.skillcontext.service.dto.TagWithCategoryDTO;
import org.contextmapper.generated.skillcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.skillcontext.domain.TagWithCategory}.
 */
@RestController
@RequestMapping("/api")
public class TagWithCategoryResource {

    private final Logger log = LoggerFactory.getLogger(TagWithCategoryResource.class);

    private final TagWithCategoryService tagWithCategoryService;

    private final TagWithCategoryRepository tagWithCategoryRepository;

    public TagWithCategoryResource(TagWithCategoryService tagWithCategoryService, TagWithCategoryRepository tagWithCategoryRepository) {
        this.tagWithCategoryService = tagWithCategoryService;
        this.tagWithCategoryRepository = tagWithCategoryRepository;
    }

    /**
     * {@code GET  /tag-with-categories} : get all the tagWithCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagWithCategories in body.
     */
    @GetMapping("/tag-with-categories")
    public List<TagWithCategoryDTO> getAllTagWithCategories() {
        log.debug("REST request to get all TagWithCategories");
        return tagWithCategoryService.findAll();
    }

    /**
     * {@code GET  /tag-with-categories/:id} : get the "id" tagWithCategory.
     *
     * @param id the id of the tagWithCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagWithCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-with-categories/{id}")
    public ResponseEntity<TagWithCategoryDTO> getTagWithCategory(@PathVariable Long id) {
        log.debug("REST request to get TagWithCategory : {}", id);
        Optional<TagWithCategoryDTO> tagWithCategoryDTO = tagWithCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagWithCategoryDTO);
    }
}
