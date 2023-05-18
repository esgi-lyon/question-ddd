package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.repository.ResourceRefusedAssociationRepository;
import org.contextmapper.generated.questioncontext.service.ResourceRefusedAssociationService;
import org.contextmapper.generated.questioncontext.service.dto.ResourceRefusedAssociationDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.ResourceRefusedAssociation}.
 */
@RestController
@RequestMapping("/api")
public class ResourceRefusedAssociationResource {

    private final Logger log = LoggerFactory.getLogger(ResourceRefusedAssociationResource.class);

    private static final String ENTITY_NAME = "questionContextResourceRefusedAssociation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResourceRefusedAssociationService resourceRefusedAssociationService;

    private final ResourceRefusedAssociationRepository resourceRefusedAssociationRepository;

    public ResourceRefusedAssociationResource(
        ResourceRefusedAssociationService resourceRefusedAssociationService,
        ResourceRefusedAssociationRepository resourceRefusedAssociationRepository
    ) {
        this.resourceRefusedAssociationService = resourceRefusedAssociationService;
        this.resourceRefusedAssociationRepository = resourceRefusedAssociationRepository;
    }

    /**
     * {@code POST  /resource-refused-associations} : Create a new resourceRefusedAssociation.
     *
     * @param resourceRefusedAssociationDTO the resourceRefusedAssociationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resourceRefusedAssociationDTO, or with status {@code 400 (Bad Request)} if the resourceRefusedAssociation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/resource-refused-associations")
    public ResponseEntity<ResourceRefusedAssociationDTO> createResourceRefusedAssociation(
        @RequestBody ResourceRefusedAssociationDTO resourceRefusedAssociationDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ResourceRefusedAssociation : {}", resourceRefusedAssociationDTO);
        if (resourceRefusedAssociationDTO.getId() != null) {
            throw new BadRequestAlertException("A new resourceRefusedAssociation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResourceRefusedAssociationDTO result = resourceRefusedAssociationService.save(resourceRefusedAssociationDTO);
        return ResponseEntity
            .created(new URI("/api/resource-refused-associations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /resource-refused-associations/:id} : Updates an existing resourceRefusedAssociation.
     *
     * @param id the id of the resourceRefusedAssociationDTO to save.
     * @param resourceRefusedAssociationDTO the resourceRefusedAssociationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourceRefusedAssociationDTO,
     * or with status {@code 400 (Bad Request)} if the resourceRefusedAssociationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resourceRefusedAssociationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/resource-refused-associations/{id}")
    public ResponseEntity<ResourceRefusedAssociationDTO> updateResourceRefusedAssociation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResourceRefusedAssociationDTO resourceRefusedAssociationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ResourceRefusedAssociation : {}, {}", id, resourceRefusedAssociationDTO);
        if (resourceRefusedAssociationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resourceRefusedAssociationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resourceRefusedAssociationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ResourceRefusedAssociationDTO result = resourceRefusedAssociationService.update(resourceRefusedAssociationDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resourceRefusedAssociationDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /resource-refused-associations/:id} : Partial updates given fields of an existing resourceRefusedAssociation, field will ignore if it is null
     *
     * @param id the id of the resourceRefusedAssociationDTO to save.
     * @param resourceRefusedAssociationDTO the resourceRefusedAssociationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourceRefusedAssociationDTO,
     * or with status {@code 400 (Bad Request)} if the resourceRefusedAssociationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the resourceRefusedAssociationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the resourceRefusedAssociationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/resource-refused-associations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResourceRefusedAssociationDTO> partialUpdateResourceRefusedAssociation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResourceRefusedAssociationDTO resourceRefusedAssociationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResourceRefusedAssociation partially : {}, {}", id, resourceRefusedAssociationDTO);
        if (resourceRefusedAssociationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resourceRefusedAssociationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resourceRefusedAssociationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResourceRefusedAssociationDTO> result = resourceRefusedAssociationService.partialUpdate(resourceRefusedAssociationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resourceRefusedAssociationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /resource-refused-associations} : get all the resourceRefusedAssociations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resourceRefusedAssociations in body.
     */
    @GetMapping("/resource-refused-associations")
    public List<ResourceRefusedAssociationDTO> getAllResourceRefusedAssociations() {
        log.debug("REST request to get all ResourceRefusedAssociations");
        return resourceRefusedAssociationService.findAll();
    }

    /**
     * {@code GET  /resource-refused-associations/:id} : get the "id" resourceRefusedAssociation.
     *
     * @param id the id of the resourceRefusedAssociationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourceRefusedAssociationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resource-refused-associations/{id}")
    public ResponseEntity<ResourceRefusedAssociationDTO> getResourceRefusedAssociation(@PathVariable Long id) {
        log.debug("REST request to get ResourceRefusedAssociation : {}", id);
        Optional<ResourceRefusedAssociationDTO> resourceRefusedAssociationDTO = resourceRefusedAssociationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resourceRefusedAssociationDTO);
    }

    /**
     * {@code DELETE  /resource-refused-associations/:id} : delete the "id" resourceRefusedAssociation.
     *
     * @param id the id of the resourceRefusedAssociationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/resource-refused-associations/{id}")
    public ResponseEntity<Void> deleteResourceRefusedAssociation(@PathVariable Long id) {
        log.debug("REST request to delete ResourceRefusedAssociation : {}", id);
        resourceRefusedAssociationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
