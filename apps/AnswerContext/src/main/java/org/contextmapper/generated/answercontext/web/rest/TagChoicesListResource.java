package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.TagChoicesListRepository;
import org.contextmapper.generated.answercontext.service.TagChoicesListService;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.TagChoicesList}.
 */
@RestController
@RequestMapping("/api")
public class TagChoicesListResource {

    private final Logger log = LoggerFactory.getLogger(TagChoicesListResource.class);

    private static final String ENTITY_NAME = "answerContextTagChoicesList";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TagChoicesListService tagChoicesListService;

    private final TagChoicesListRepository tagChoicesListRepository;

    public TagChoicesListResource(TagChoicesListService tagChoicesListService, TagChoicesListRepository tagChoicesListRepository) {
        this.tagChoicesListService = tagChoicesListService;
        this.tagChoicesListRepository = tagChoicesListRepository;
    }

    /**
     * {@code POST  /tag-choices-lists} : Create a new tagChoicesList.
     *
     * @param tagChoicesListDTO the tagChoicesListDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tagChoicesListDTO, or with status {@code 400 (Bad Request)} if the tagChoicesList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tag-choices-lists")
    public ResponseEntity<TagChoicesListDTO> createTagChoicesList(@RequestBody TagChoicesListDTO tagChoicesListDTO)
        throws URISyntaxException {
        log.debug("REST request to save TagChoicesList : {}", tagChoicesListDTO);
        if (tagChoicesListDTO.getId() != null) {
            throw new BadRequestAlertException("A new tagChoicesList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TagChoicesListDTO result = tagChoicesListService.save(tagChoicesListDTO);
        return ResponseEntity
            .created(new URI("/api/tag-choices-lists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tag-choices-lists/:id} : Updates an existing tagChoicesList.
     *
     * @param id the id of the tagChoicesListDTO to save.
     * @param tagChoicesListDTO the tagChoicesListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagChoicesListDTO,
     * or with status {@code 400 (Bad Request)} if the tagChoicesListDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tagChoicesListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tag-choices-lists/{id}")
    public ResponseEntity<TagChoicesListDTO> updateTagChoicesList(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TagChoicesListDTO tagChoicesListDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TagChoicesList : {}, {}", id, tagChoicesListDTO);
        if (tagChoicesListDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tagChoicesListDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tagChoicesListRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TagChoicesListDTO result = tagChoicesListService.update(tagChoicesListDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tagChoicesListDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tag-choices-lists/:id} : Partial updates given fields of an existing tagChoicesList, field will ignore if it is null
     *
     * @param id the id of the tagChoicesListDTO to save.
     * @param tagChoicesListDTO the tagChoicesListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagChoicesListDTO,
     * or with status {@code 400 (Bad Request)} if the tagChoicesListDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tagChoicesListDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tagChoicesListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tag-choices-lists/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TagChoicesListDTO> partialUpdateTagChoicesList(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TagChoicesListDTO tagChoicesListDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TagChoicesList partially : {}, {}", id, tagChoicesListDTO);
        if (tagChoicesListDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tagChoicesListDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tagChoicesListRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TagChoicesListDTO> result = tagChoicesListService.partialUpdate(tagChoicesListDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tagChoicesListDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tag-choices-lists} : get all the tagChoicesLists.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagChoicesLists in body.
     */
    @GetMapping("/tag-choices-lists")
    public List<TagChoicesListDTO> getAllTagChoicesLists() {
        log.debug("REST request to get all TagChoicesLists");
        return tagChoicesListService.findAll();
    }

    /**
     * {@code GET  /tag-choices-lists/:id} : get the "id" tagChoicesList.
     *
     * @param id the id of the tagChoicesListDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagChoicesListDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-choices-lists/{id}")
    public ResponseEntity<TagChoicesListDTO> getTagChoicesList(@PathVariable Long id) {
        log.debug("REST request to get TagChoicesList : {}", id);
        Optional<TagChoicesListDTO> tagChoicesListDTO = tagChoicesListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagChoicesListDTO);
    }

    /**
     * {@code DELETE  /tag-choices-lists/:id} : delete the "id" tagChoicesList.
     *
     * @param id the id of the tagChoicesListDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tag-choices-lists/{id}")
    public ResponseEntity<Void> deleteTagChoicesList(@PathVariable Long id) {
        log.debug("REST request to delete TagChoicesList : {}", id);
        tagChoicesListService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
