package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.TagChoicesListCommandRepository;
import org.contextmapper.generated.answercontext.service.TagChoicesListCommandService;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListCommandDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.TagChoicesListCommand}.
 */
@RestController
@RequestMapping("/api")
public class TagChoicesListCommandResource {

    private final Logger log = LoggerFactory.getLogger(TagChoicesListCommandResource.class);

    private static final String ENTITY_NAME = "answerContextTagChoicesListCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TagChoicesListCommandService tagChoicesListCommandService;

    private final TagChoicesListCommandRepository tagChoicesListCommandRepository;

    public TagChoicesListCommandResource(
        TagChoicesListCommandService tagChoicesListCommandService,
        TagChoicesListCommandRepository tagChoicesListCommandRepository
    ) {
        this.tagChoicesListCommandService = tagChoicesListCommandService;
        this.tagChoicesListCommandRepository = tagChoicesListCommandRepository;
    }

    /**
     * {@code POST  /tag-choices-list-commands} : Create a new tagChoicesListCommand.
     *
     * @param tagChoicesListCommandDTO the tagChoicesListCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tagChoicesListCommandDTO, or with status {@code 400 (Bad Request)} if the tagChoicesListCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tag-choices-list-commands")
    public ResponseEntity<TagChoicesListCommandDTO> createTagChoicesListCommand(
        @RequestBody TagChoicesListCommandDTO tagChoicesListCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TagChoicesListCommand : {}", tagChoicesListCommandDTO);
        if (tagChoicesListCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new tagChoicesListCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TagChoicesListCommandDTO result = tagChoicesListCommandService.save(tagChoicesListCommandDTO);
        return ResponseEntity
            .created(new URI("/api/tag-choices-list-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tag-choices-list-commands/:id} : Updates an existing tagChoicesListCommand.
     *
     * @param id the id of the tagChoicesListCommandDTO to save.
     * @param tagChoicesListCommandDTO the tagChoicesListCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagChoicesListCommandDTO,
     * or with status {@code 400 (Bad Request)} if the tagChoicesListCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tagChoicesListCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tag-choices-list-commands/{id}")
    public ResponseEntity<TagChoicesListCommandDTO> updateTagChoicesListCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TagChoicesListCommandDTO tagChoicesListCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TagChoicesListCommand : {}, {}", id, tagChoicesListCommandDTO);
        if (tagChoicesListCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tagChoicesListCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tagChoicesListCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TagChoicesListCommandDTO result = tagChoicesListCommandService.update(tagChoicesListCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tagChoicesListCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tag-choices-list-commands/:id} : Partial updates given fields of an existing tagChoicesListCommand, field will ignore if it is null
     *
     * @param id the id of the tagChoicesListCommandDTO to save.
     * @param tagChoicesListCommandDTO the tagChoicesListCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagChoicesListCommandDTO,
     * or with status {@code 400 (Bad Request)} if the tagChoicesListCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tagChoicesListCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tagChoicesListCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tag-choices-list-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TagChoicesListCommandDTO> partialUpdateTagChoicesListCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TagChoicesListCommandDTO tagChoicesListCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TagChoicesListCommand partially : {}, {}", id, tagChoicesListCommandDTO);
        if (tagChoicesListCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tagChoicesListCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tagChoicesListCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TagChoicesListCommandDTO> result = tagChoicesListCommandService.partialUpdate(tagChoicesListCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tagChoicesListCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tag-choices-list-commands} : get all the tagChoicesListCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagChoicesListCommands in body.
     */
    @GetMapping("/tag-choices-list-commands")
    public List<TagChoicesListCommandDTO> getAllTagChoicesListCommands() {
        log.debug("REST request to get all TagChoicesListCommands");
        return tagChoicesListCommandService.findAll();
    }

    /**
     * {@code GET  /tag-choices-list-commands/:id} : get the "id" tagChoicesListCommand.
     *
     * @param id the id of the tagChoicesListCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagChoicesListCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-choices-list-commands/{id}")
    public ResponseEntity<TagChoicesListCommandDTO> getTagChoicesListCommand(@PathVariable Long id) {
        log.debug("REST request to get TagChoicesListCommand : {}", id);
        Optional<TagChoicesListCommandDTO> tagChoicesListCommandDTO = tagChoicesListCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagChoicesListCommandDTO);
    }

    /**
     * {@code DELETE  /tag-choices-list-commands/:id} : delete the "id" tagChoicesListCommand.
     *
     * @param id the id of the tagChoicesListCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tag-choices-list-commands/{id}")
    public ResponseEntity<Void> deleteTagChoicesListCommand(@PathVariable Long id) {
        log.debug("REST request to delete TagChoicesListCommand : {}", id);
        tagChoicesListCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
