package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.repository.CreateResourceRepository;
import org.contextmapper.generated.questioncontext.service.CreateResourceService;
import org.contextmapper.generated.questioncontext.service.dto.CreateResourceDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.CreateResource}.
 */
@RestController
@RequestMapping("/api")
public class CreateResourceResource {

    private final Logger log = LoggerFactory.getLogger(CreateResourceResource.class);

    private static final String ENTITY_NAME = "questionContextCreateResource";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreateResourceService createResourceService;

    private final CreateResourceRepository createResourceRepository;

    public CreateResourceResource(CreateResourceService createResourceService, CreateResourceRepository createResourceRepository) {
        this.createResourceService = createResourceService;
        this.createResourceRepository = createResourceRepository;
    }

    /**
     * {@code POST  /create-resources} : Create a new createResource.
     *
     * @param createResourceDTO the createResourceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createResourceDTO, or with status {@code 400 (Bad Request)} if the createResource has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-resources")
    public ResponseEntity<CreateResourceDTO> createCreateResource(@RequestBody CreateResourceDTO createResourceDTO)
        throws URISyntaxException {
        log.debug("REST request to save CreateResource : {}", createResourceDTO);
        if (createResourceDTO.getId() != null) {
            throw new BadRequestAlertException("A new createResource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreateResourceDTO result = createResourceService.save(createResourceDTO);
        return ResponseEntity
            .created(new URI("/api/create-resources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /create-resources/:id} : Updates an existing createResource.
     *
     * @param id the id of the createResourceDTO to save.
     * @param createResourceDTO the createResourceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createResourceDTO,
     * or with status {@code 400 (Bad Request)} if the createResourceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the createResourceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/create-resources/{id}")
    public ResponseEntity<CreateResourceDTO> updateCreateResource(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateResourceDTO createResourceDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CreateResource : {}, {}", id, createResourceDTO);
        if (createResourceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createResourceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createResourceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CreateResourceDTO result = createResourceService.update(createResourceDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createResourceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /create-resources/:id} : Partial updates given fields of an existing createResource, field will ignore if it is null
     *
     * @param id the id of the createResourceDTO to save.
     * @param createResourceDTO the createResourceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createResourceDTO,
     * or with status {@code 400 (Bad Request)} if the createResourceDTO is not valid,
     * or with status {@code 404 (Not Found)} if the createResourceDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the createResourceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/create-resources/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CreateResourceDTO> partialUpdateCreateResource(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateResourceDTO createResourceDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreateResource partially : {}, {}", id, createResourceDTO);
        if (createResourceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createResourceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createResourceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CreateResourceDTO> result = createResourceService.partialUpdate(createResourceDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createResourceDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /create-resources} : get all the createResources.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createResources in body.
     */
    @GetMapping("/create-resources")
    public List<CreateResourceDTO> getAllCreateResources() {
        log.debug("REST request to get all CreateResources");
        return createResourceService.findAll();
    }

    /**
     * {@code GET  /create-resources/:id} : get the "id" createResource.
     *
     * @param id the id of the createResourceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createResourceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/create-resources/{id}")
    public ResponseEntity<CreateResourceDTO> getCreateResource(@PathVariable Long id) {
        log.debug("REST request to get CreateResource : {}", id);
        Optional<CreateResourceDTO> createResourceDTO = createResourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createResourceDTO);
    }

    /**
     * {@code DELETE  /create-resources/:id} : delete the "id" createResource.
     *
     * @param id the id of the createResourceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/create-resources/{id}")
    public ResponseEntity<Void> deleteCreateResource(@PathVariable Long id) {
        log.debug("REST request to delete CreateResource : {}", id);
        createResourceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
