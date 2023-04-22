package org.contextmapper.generated.skillcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.repository.CategoryCreatedEventRepository;
import org.contextmapper.generated.skillcontext.service.CategoryCreatedEventService;
import org.contextmapper.generated.skillcontext.service.dto.CategoryCreatedEventDTO;
import org.contextmapper.generated.skillcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.skillcontext.domain.CategoryCreatedEvent}.
 */
@RestController
@RequestMapping("/api")
public class CategoryCreatedEventResource {

    private final Logger log = LoggerFactory.getLogger(CategoryCreatedEventResource.class);

    private final CategoryCreatedEventService categoryCreatedEventService;

    private final CategoryCreatedEventRepository categoryCreatedEventRepository;

    public CategoryCreatedEventResource(
        CategoryCreatedEventService categoryCreatedEventService,
        CategoryCreatedEventRepository categoryCreatedEventRepository
    ) {
        this.categoryCreatedEventService = categoryCreatedEventService;
        this.categoryCreatedEventRepository = categoryCreatedEventRepository;
    }

    /**
     * {@code GET  /category-created-events} : get all the categoryCreatedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoryCreatedEvents in body.
     */
    @GetMapping("/category-created-events")
    public List<CategoryCreatedEventDTO> getAllCategoryCreatedEvents() {
        log.debug("REST request to get all CategoryCreatedEvents");
        return categoryCreatedEventService.findAll();
    }

    /**
     * {@code GET  /category-created-events/:id} : get the "id" categoryCreatedEvent.
     *
     * @param id the id of the categoryCreatedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryCreatedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/category-created-events/{id}")
    public ResponseEntity<CategoryCreatedEventDTO> getCategoryCreatedEvent(@PathVariable Long id) {
        log.debug("REST request to get CategoryCreatedEvent : {}", id);
        Optional<CategoryCreatedEventDTO> categoryCreatedEventDTO = categoryCreatedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryCreatedEventDTO);
    }
}
