package org.contextmapper.generated.skillcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.repository.TagCreatedRepository;
import org.contextmapper.generated.skillcontext.service.TagCreatedService;
import org.contextmapper.generated.skillcontext.service.dto.TagCreatedDTO;
import org.contextmapper.generated.skillcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.skillcontext.domain.TagCreated}.
 */
@RestController
@RequestMapping("/api")
public class TagCreatedResource {

    private final Logger log = LoggerFactory.getLogger(TagCreatedResource.class);

    private static final String ENTITY_NAME = "skillContextTagCreated";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TagCreatedService tagCreatedService;

    private final TagCreatedRepository tagCreatedRepository;

    public TagCreatedResource(TagCreatedService tagCreatedService, TagCreatedRepository tagCreatedRepository) {
        this.tagCreatedService = tagCreatedService;
        this.tagCreatedRepository = tagCreatedRepository;
    }

    /**
     * {@code POST  /tag-createds} : Create a new tagCreated.
     *
     * @param tagCreatedDTO the tagCreatedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tagCreatedDTO, or with status {@code 400 (Bad Request)} if the tagCreated has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tag-createds")
    public ResponseEntity<TagCreatedDTO> createTagCreated(@RequestBody TagCreatedDTO tagCreatedDTO) throws URISyntaxException {
        log.debug("REST request to save TagCreated : {}", tagCreatedDTO);
        if (tagCreatedDTO.getId() != null) {
            throw new BadRequestAlertException("A new tagCreated cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TagCreatedDTO result = tagCreatedService.save(tagCreatedDTO);
        return ResponseEntity
            .created(new URI("/api/tag-createds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tag-createds/:id} : Updates an existing tagCreated.
     *
     * @param id the id of the tagCreatedDTO to save.
     * @param tagCreatedDTO the tagCreatedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagCreatedDTO,
     * or with status {@code 400 (Bad Request)} if the tagCreatedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tagCreatedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tag-createds/{id}")
    public ResponseEntity<TagCreatedDTO> updateTagCreated(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TagCreatedDTO tagCreatedDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TagCreated : {}, {}", id, tagCreatedDTO);
        if (tagCreatedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tagCreatedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tagCreatedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TagCreatedDTO result = tagCreatedService.update(tagCreatedDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tagCreatedDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tag-createds/:id} : Partial updates given fields of an existing tagCreated, field will ignore if it is null
     *
     * @param id the id of the tagCreatedDTO to save.
     * @param tagCreatedDTO the tagCreatedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagCreatedDTO,
     * or with status {@code 400 (Bad Request)} if the tagCreatedDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tagCreatedDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tagCreatedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tag-createds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TagCreatedDTO> partialUpdateTagCreated(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TagCreatedDTO tagCreatedDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TagCreated partially : {}, {}", id, tagCreatedDTO);
        if (tagCreatedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tagCreatedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tagCreatedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TagCreatedDTO> result = tagCreatedService.partialUpdate(tagCreatedDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tagCreatedDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tag-createds} : get all the tagCreateds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagCreateds in body.
     */
    @GetMapping("/tag-createds")
    public List<TagCreatedDTO> getAllTagCreateds() {
        log.debug("REST request to get all TagCreateds");
        return tagCreatedService.findAll();
    }

    /**
     * {@code GET  /tag-createds/:id} : get the "id" tagCreated.
     *
     * @param id the id of the tagCreatedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagCreatedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-createds/{id}")
    public ResponseEntity<TagCreatedDTO> getTagCreated(@PathVariable Long id) {
        log.debug("REST request to get TagCreated : {}", id);
        Optional<TagCreatedDTO> tagCreatedDTO = tagCreatedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagCreatedDTO);
    }

    /**
     * {@code DELETE  /tag-createds/:id} : delete the "id" tagCreated.
     *
     * @param id the id of the tagCreatedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tag-createds/{id}")
    public ResponseEntity<Void> deleteTagCreated(@PathVariable Long id) {
        log.debug("REST request to delete TagCreated : {}", id);
        tagCreatedService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
