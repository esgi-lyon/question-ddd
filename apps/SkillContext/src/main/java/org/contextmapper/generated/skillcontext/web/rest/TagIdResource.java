package org.contextmapper.generated.skillcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.repository.TagIdRepository;
import org.contextmapper.generated.skillcontext.service.TagIdService;
import org.contextmapper.generated.skillcontext.service.dto.TagIdDTO;
import org.contextmapper.generated.skillcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.skillcontext.domain.TagId}.
 */
@RestController
@RequestMapping("/api")
public class TagIdResource {

    private final Logger log = LoggerFactory.getLogger(TagIdResource.class);

    private static final String ENTITY_NAME = "skillContextTagId";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TagIdService tagIdService;

    private final TagIdRepository tagIdRepository;

    public TagIdResource(TagIdService tagIdService, TagIdRepository tagIdRepository) {
        this.tagIdService = tagIdService;
        this.tagIdRepository = tagIdRepository;
    }

    /**
     * {@code POST  /tag-ids} : Create a new tagId.
     *
     * @param tagIdDTO the tagIdDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tagIdDTO, or with status {@code 400 (Bad Request)} if the tagId has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tag-ids")
    public ResponseEntity<TagIdDTO> createTagId(@RequestBody TagIdDTO tagIdDTO) throws URISyntaxException {
        log.debug("REST request to save TagId : {}", tagIdDTO);
        if (tagIdDTO.getId() != null) {
            throw new BadRequestAlertException("A new tagId cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TagIdDTO result = tagIdService.save(tagIdDTO);
        return ResponseEntity
            .created(new URI("/api/tag-ids/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tag-ids/:id} : Updates an existing tagId.
     *
     * @param id the id of the tagIdDTO to save.
     * @param tagIdDTO the tagIdDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagIdDTO,
     * or with status {@code 400 (Bad Request)} if the tagIdDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tagIdDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tag-ids/{id}")
    public ResponseEntity<TagIdDTO> updateTagId(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TagIdDTO tagIdDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TagId : {}, {}", id, tagIdDTO);
        if (tagIdDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tagIdDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tagIdRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TagIdDTO result = tagIdService.update(tagIdDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tagIdDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tag-ids/:id} : Partial updates given fields of an existing tagId, field will ignore if it is null
     *
     * @param id the id of the tagIdDTO to save.
     * @param tagIdDTO the tagIdDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagIdDTO,
     * or with status {@code 400 (Bad Request)} if the tagIdDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tagIdDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tagIdDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tag-ids/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TagIdDTO> partialUpdateTagId(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TagIdDTO tagIdDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TagId partially : {}, {}", id, tagIdDTO);
        if (tagIdDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tagIdDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tagIdRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TagIdDTO> result = tagIdService.partialUpdate(tagIdDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tagIdDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tag-ids} : get all the tagIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagIds in body.
     */
    @GetMapping("/tag-ids")
    public List<TagIdDTO> getAllTagIds() {
        log.debug("REST request to get all TagIds");
        return tagIdService.findAll();
    }

    /**
     * {@code GET  /tag-ids/:id} : get the "id" tagId.
     *
     * @param id the id of the tagIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-ids/{id}")
    public ResponseEntity<TagIdDTO> getTagId(@PathVariable Long id) {
        log.debug("REST request to get TagId : {}", id);
        Optional<TagIdDTO> tagIdDTO = tagIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagIdDTO);
    }

    /**
     * {@code DELETE  /tag-ids/:id} : delete the "id" tagId.
     *
     * @param id the id of the tagIdDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tag-ids/{id}")
    public ResponseEntity<Void> deleteTagId(@PathVariable Long id) {
        log.debug("REST request to delete TagId : {}", id);
        tagIdService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
