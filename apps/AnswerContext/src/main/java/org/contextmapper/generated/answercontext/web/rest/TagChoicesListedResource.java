package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.TagChoicesListedRepository;
import org.contextmapper.generated.answercontext.service.TagChoicesListedService;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListedDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.TagChoicesListed}.
 */
@RestController
@RequestMapping("/api")
public class TagChoicesListedResource {

    private final Logger log = LoggerFactory.getLogger(TagChoicesListedResource.class);

    private static final String ENTITY_NAME = "answerContextTagChoicesListed";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TagChoicesListedService tagChoicesListedService;

    private final TagChoicesListedRepository tagChoicesListedRepository;

    public TagChoicesListedResource(
        TagChoicesListedService tagChoicesListedService,
        TagChoicesListedRepository tagChoicesListedRepository
    ) {
        this.tagChoicesListedService = tagChoicesListedService;
        this.tagChoicesListedRepository = tagChoicesListedRepository;
    }

    /**
     * {@code POST  /tag-choices-listeds} : Create a new tagChoicesListed.
     *
     * @param tagChoicesListedDTO the tagChoicesListedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tagChoicesListedDTO, or with status {@code 400 (Bad Request)} if the tagChoicesListed has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tag-choices-listeds")
    public ResponseEntity<TagChoicesListedDTO> createTagChoicesListed(@RequestBody TagChoicesListedDTO tagChoicesListedDTO)
        throws URISyntaxException {
        log.debug("REST request to save TagChoicesListed : {}", tagChoicesListedDTO);
        if (tagChoicesListedDTO.getId() != null) {
            throw new BadRequestAlertException("A new tagChoicesListed cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TagChoicesListedDTO result = tagChoicesListedService.save(tagChoicesListedDTO);
        return ResponseEntity
            .created(new URI("/api/tag-choices-listeds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tag-choices-listeds/:id} : Updates an existing tagChoicesListed.
     *
     * @param id the id of the tagChoicesListedDTO to save.
     * @param tagChoicesListedDTO the tagChoicesListedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagChoicesListedDTO,
     * or with status {@code 400 (Bad Request)} if the tagChoicesListedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tagChoicesListedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tag-choices-listeds/{id}")
    public ResponseEntity<TagChoicesListedDTO> updateTagChoicesListed(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TagChoicesListedDTO tagChoicesListedDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TagChoicesListed : {}, {}", id, tagChoicesListedDTO);
        if (tagChoicesListedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tagChoicesListedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tagChoicesListedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TagChoicesListedDTO result = tagChoicesListedService.update(tagChoicesListedDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tagChoicesListedDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tag-choices-listeds/:id} : Partial updates given fields of an existing tagChoicesListed, field will ignore if it is null
     *
     * @param id the id of the tagChoicesListedDTO to save.
     * @param tagChoicesListedDTO the tagChoicesListedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagChoicesListedDTO,
     * or with status {@code 400 (Bad Request)} if the tagChoicesListedDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tagChoicesListedDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tagChoicesListedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tag-choices-listeds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TagChoicesListedDTO> partialUpdateTagChoicesListed(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TagChoicesListedDTO tagChoicesListedDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TagChoicesListed partially : {}, {}", id, tagChoicesListedDTO);
        if (tagChoicesListedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tagChoicesListedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tagChoicesListedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TagChoicesListedDTO> result = tagChoicesListedService.partialUpdate(tagChoicesListedDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tagChoicesListedDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tag-choices-listeds} : get all the tagChoicesListeds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagChoicesListeds in body.
     */
    @GetMapping("/tag-choices-listeds")
    public List<TagChoicesListedDTO> getAllTagChoicesListeds() {
        log.debug("REST request to get all TagChoicesListeds");
        return tagChoicesListedService.findAll();
    }

    /**
     * {@code GET  /tag-choices-listeds/:id} : get the "id" tagChoicesListed.
     *
     * @param id the id of the tagChoicesListedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagChoicesListedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-choices-listeds/{id}")
    public ResponseEntity<TagChoicesListedDTO> getTagChoicesListed(@PathVariable Long id) {
        log.debug("REST request to get TagChoicesListed : {}", id);
        Optional<TagChoicesListedDTO> tagChoicesListedDTO = tagChoicesListedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagChoicesListedDTO);
    }

    /**
     * {@code DELETE  /tag-choices-listeds/:id} : delete the "id" tagChoicesListed.
     *
     * @param id the id of the tagChoicesListedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tag-choices-listeds/{id}")
    public ResponseEntity<Void> deleteTagChoicesListed(@PathVariable Long id) {
        log.debug("REST request to delete TagChoicesListed : {}", id);
        tagChoicesListedService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
