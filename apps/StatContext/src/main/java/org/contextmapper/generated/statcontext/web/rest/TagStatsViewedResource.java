package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.TagStatsViewedRepository;
import org.contextmapper.generated.statcontext.service.TagStatsViewedService;
import org.contextmapper.generated.statcontext.service.dto.TagStatsViewedDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.TagStatsViewed}.
 */
@RestController
@RequestMapping("/api")
public class TagStatsViewedResource {

    private final Logger log = LoggerFactory.getLogger(TagStatsViewedResource.class);

    private static final String ENTITY_NAME = "statContextTagStatsViewed";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TagStatsViewedService tagStatsViewedService;

    private final TagStatsViewedRepository tagStatsViewedRepository;

    public TagStatsViewedResource(TagStatsViewedService tagStatsViewedService, TagStatsViewedRepository tagStatsViewedRepository) {
        this.tagStatsViewedService = tagStatsViewedService;
        this.tagStatsViewedRepository = tagStatsViewedRepository;
    }

    /**
     * {@code POST  /tag-stats-vieweds} : Create a new tagStatsViewed.
     *
     * @param tagStatsViewedDTO the tagStatsViewedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tagStatsViewedDTO, or with status {@code 400 (Bad Request)} if the tagStatsViewed has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tag-stats-vieweds")
    public ResponseEntity<TagStatsViewedDTO> createTagStatsViewed(@RequestBody TagStatsViewedDTO tagStatsViewedDTO)
        throws URISyntaxException {
        log.debug("REST request to save TagStatsViewed : {}", tagStatsViewedDTO);
        if (tagStatsViewedDTO.getId() != null) {
            throw new BadRequestAlertException("A new tagStatsViewed cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TagStatsViewedDTO result = tagStatsViewedService.save(tagStatsViewedDTO);
        return ResponseEntity
            .created(new URI("/api/tag-stats-vieweds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tag-stats-vieweds/:id} : Updates an existing tagStatsViewed.
     *
     * @param id the id of the tagStatsViewedDTO to save.
     * @param tagStatsViewedDTO the tagStatsViewedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagStatsViewedDTO,
     * or with status {@code 400 (Bad Request)} if the tagStatsViewedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tagStatsViewedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tag-stats-vieweds/{id}")
    public ResponseEntity<TagStatsViewedDTO> updateTagStatsViewed(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TagStatsViewedDTO tagStatsViewedDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TagStatsViewed : {}, {}", id, tagStatsViewedDTO);
        if (tagStatsViewedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tagStatsViewedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tagStatsViewedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TagStatsViewedDTO result = tagStatsViewedService.update(tagStatsViewedDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tagStatsViewedDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tag-stats-vieweds/:id} : Partial updates given fields of an existing tagStatsViewed, field will ignore if it is null
     *
     * @param id the id of the tagStatsViewedDTO to save.
     * @param tagStatsViewedDTO the tagStatsViewedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagStatsViewedDTO,
     * or with status {@code 400 (Bad Request)} if the tagStatsViewedDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tagStatsViewedDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tagStatsViewedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tag-stats-vieweds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TagStatsViewedDTO> partialUpdateTagStatsViewed(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TagStatsViewedDTO tagStatsViewedDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TagStatsViewed partially : {}, {}", id, tagStatsViewedDTO);
        if (tagStatsViewedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tagStatsViewedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tagStatsViewedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TagStatsViewedDTO> result = tagStatsViewedService.partialUpdate(tagStatsViewedDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tagStatsViewedDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tag-stats-vieweds} : get all the tagStatsVieweds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagStatsVieweds in body.
     */
    @GetMapping("/tag-stats-vieweds")
    public List<TagStatsViewedDTO> getAllTagStatsVieweds() {
        log.debug("REST request to get all TagStatsVieweds");
        return tagStatsViewedService.findAll();
    }

    /**
     * {@code GET  /tag-stats-vieweds/:id} : get the "id" tagStatsViewed.
     *
     * @param id the id of the tagStatsViewedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagStatsViewedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-stats-vieweds/{id}")
    public ResponseEntity<TagStatsViewedDTO> getTagStatsViewed(@PathVariable Long id) {
        log.debug("REST request to get TagStatsViewed : {}", id);
        Optional<TagStatsViewedDTO> tagStatsViewedDTO = tagStatsViewedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagStatsViewedDTO);
    }

    /**
     * {@code DELETE  /tag-stats-vieweds/:id} : delete the "id" tagStatsViewed.
     *
     * @param id the id of the tagStatsViewedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tag-stats-vieweds/{id}")
    public ResponseEntity<Void> deleteTagStatsViewed(@PathVariable Long id) {
        log.debug("REST request to delete TagStatsViewed : {}", id);
        tagStatsViewedService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
