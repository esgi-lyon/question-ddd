package org.contextmapper.generated.skillcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.repository.CreateTagRepository;
import org.contextmapper.generated.skillcontext.service.CreateTagService;
import org.contextmapper.generated.skillcontext.service.dto.CreateTagDTO;
import org.contextmapper.generated.skillcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.skillcontext.domain.CreateTag}.
 */
@RestController
@RequestMapping("/api")
public class CreateTagResource {

    private final Logger log = LoggerFactory.getLogger(CreateTagResource.class);

    private static final String ENTITY_NAME = "skillContextCreateTag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreateTagService createTagService;

    private final CreateTagRepository createTagRepository;

    public CreateTagResource(CreateTagService createTagService, CreateTagRepository createTagRepository) {
        this.createTagService = createTagService;
        this.createTagRepository = createTagRepository;
    }

    /**
     * {@code POST  /create-tags} : Create a new createTag.
     *
     * @param createTagDTO the createTagDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createTagDTO, or with status {@code 400 (Bad Request)} if the createTag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-tags")
    public ResponseEntity<CreateTagDTO> createCreateTag(@RequestBody CreateTagDTO createTagDTO) throws URISyntaxException {
        log.debug("REST request to save CreateTag : {}", createTagDTO);
        if (createTagDTO.getId() != null) {
            throw new BadRequestAlertException("A new createTag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreateTagDTO result = createTagService.save(createTagDTO);
        return ResponseEntity
            .created(new URI("/api/create-tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /create-tags/:id} : Updates an existing createTag.
     *
     * @param id the id of the createTagDTO to save.
     * @param createTagDTO the createTagDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createTagDTO,
     * or with status {@code 400 (Bad Request)} if the createTagDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the createTagDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/create-tags/{id}")
    public ResponseEntity<CreateTagDTO> updateCreateTag(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateTagDTO createTagDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CreateTag : {}, {}", id, createTagDTO);
        if (createTagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createTagDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CreateTagDTO result = createTagService.update(createTagDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createTagDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /create-tags/:id} : Partial updates given fields of an existing createTag, field will ignore if it is null
     *
     * @param id the id of the createTagDTO to save.
     * @param createTagDTO the createTagDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createTagDTO,
     * or with status {@code 400 (Bad Request)} if the createTagDTO is not valid,
     * or with status {@code 404 (Not Found)} if the createTagDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the createTagDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/create-tags/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CreateTagDTO> partialUpdateCreateTag(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateTagDTO createTagDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreateTag partially : {}, {}", id, createTagDTO);
        if (createTagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createTagDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CreateTagDTO> result = createTagService.partialUpdate(createTagDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createTagDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /create-tags} : get all the createTags.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createTags in body.
     */
    @GetMapping("/create-tags")
    public List<CreateTagDTO> getAllCreateTags() {
        log.debug("REST request to get all CreateTags");
        return createTagService.findAll();
    }

    /**
     * {@code GET  /create-tags/:id} : get the "id" createTag.
     *
     * @param id the id of the createTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/create-tags/{id}")
    public ResponseEntity<CreateTagDTO> getCreateTag(@PathVariable Long id) {
        log.debug("REST request to get CreateTag : {}", id);
        Optional<CreateTagDTO> createTagDTO = createTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createTagDTO);
    }

    /**
     * {@code DELETE  /create-tags/:id} : delete the "id" createTag.
     *
     * @param id the id of the createTagDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/create-tags/{id}")
    public ResponseEntity<Void> deleteCreateTag(@PathVariable Long id) {
        log.debug("REST request to delete CreateTag : {}", id);
        createTagService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
