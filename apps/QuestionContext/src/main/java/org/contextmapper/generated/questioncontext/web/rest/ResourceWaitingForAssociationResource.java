package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.repository.ResourceWaitingForAssociationRepository;
import org.contextmapper.generated.questioncontext.service.ResourceWaitingForAssociationService;
import org.contextmapper.generated.questioncontext.service.dto.ResourceWaitingForAssociationDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.ResourceWaitingForAssociation}.
 */
@RestController
@RequestMapping("/api")
public class ResourceWaitingForAssociationResource {

    private final Logger log = LoggerFactory.getLogger(ResourceWaitingForAssociationResource.class);

    private static final String ENTITY_NAME = "questionContextResourceWaitingForAssociation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResourceWaitingForAssociationService resourceWaitingForAssociationService;

    private final ResourceWaitingForAssociationRepository resourceWaitingForAssociationRepository;

    public ResourceWaitingForAssociationResource(
        ResourceWaitingForAssociationService resourceWaitingForAssociationService,
        ResourceWaitingForAssociationRepository resourceWaitingForAssociationRepository
    ) {
        this.resourceWaitingForAssociationService = resourceWaitingForAssociationService;
        this.resourceWaitingForAssociationRepository = resourceWaitingForAssociationRepository;
    }

    /**
     * {@code POST  /resource-waiting-for-associations} : Create a new resourceWaitingForAssociation.
     *
     * @param resourceWaitingForAssociationDTO the resourceWaitingForAssociationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resourceWaitingForAssociationDTO, or with status {@code 400 (Bad Request)} if the resourceWaitingForAssociation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/resource-waiting-for-associations")
    public ResponseEntity<ResourceWaitingForAssociationDTO> createResourceWaitingForAssociation(
        @RequestBody ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ResourceWaitingForAssociation : {}", resourceWaitingForAssociationDTO);
        if (resourceWaitingForAssociationDTO.getId() != null) {
            throw new BadRequestAlertException("A new resourceWaitingForAssociation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResourceWaitingForAssociationDTO result = resourceWaitingForAssociationService.save(resourceWaitingForAssociationDTO);
        return ResponseEntity
            .created(new URI("/api/resource-waiting-for-associations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /resource-waiting-for-associations/:id} : Updates an existing resourceWaitingForAssociation.
     *
     * @param id the id of the resourceWaitingForAssociationDTO to save.
     * @param resourceWaitingForAssociationDTO the resourceWaitingForAssociationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourceWaitingForAssociationDTO,
     * or with status {@code 400 (Bad Request)} if the resourceWaitingForAssociationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resourceWaitingForAssociationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/resource-waiting-for-associations/{id}")
    public ResponseEntity<ResourceWaitingForAssociationDTO> updateResourceWaitingForAssociation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ResourceWaitingForAssociation : {}, {}", id, resourceWaitingForAssociationDTO);
        if (resourceWaitingForAssociationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resourceWaitingForAssociationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resourceWaitingForAssociationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ResourceWaitingForAssociationDTO result = resourceWaitingForAssociationService.update(resourceWaitingForAssociationDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resourceWaitingForAssociationDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /resource-waiting-for-associations/:id} : Partial updates given fields of an existing resourceWaitingForAssociation, field will ignore if it is null
     *
     * @param id the id of the resourceWaitingForAssociationDTO to save.
     * @param resourceWaitingForAssociationDTO the resourceWaitingForAssociationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourceWaitingForAssociationDTO,
     * or with status {@code 400 (Bad Request)} if the resourceWaitingForAssociationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the resourceWaitingForAssociationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the resourceWaitingForAssociationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/resource-waiting-for-associations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResourceWaitingForAssociationDTO> partialUpdateResourceWaitingForAssociation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResourceWaitingForAssociationDTO resourceWaitingForAssociationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResourceWaitingForAssociation partially : {}, {}", id, resourceWaitingForAssociationDTO);
        if (resourceWaitingForAssociationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resourceWaitingForAssociationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resourceWaitingForAssociationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResourceWaitingForAssociationDTO> result = resourceWaitingForAssociationService.partialUpdate(
            resourceWaitingForAssociationDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resourceWaitingForAssociationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /resource-waiting-for-associations} : get all the resourceWaitingForAssociations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resourceWaitingForAssociations in body.
     */
    @GetMapping("/resource-waiting-for-associations")
    public List<ResourceWaitingForAssociationDTO> getAllResourceWaitingForAssociations() {
        log.debug("REST request to get all ResourceWaitingForAssociations");
        return resourceWaitingForAssociationService.findAll();
    }

    /**
     * {@code GET  /resource-waiting-for-associations/:id} : get the "id" resourceWaitingForAssociation.
     *
     * @param id the id of the resourceWaitingForAssociationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourceWaitingForAssociationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resource-waiting-for-associations/{id}")
    public ResponseEntity<ResourceWaitingForAssociationDTO> getResourceWaitingForAssociation(@PathVariable Long id) {
        log.debug("REST request to get ResourceWaitingForAssociation : {}", id);
        Optional<ResourceWaitingForAssociationDTO> resourceWaitingForAssociationDTO = resourceWaitingForAssociationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resourceWaitingForAssociationDTO);
    }

    /**
     * {@code DELETE  /resource-waiting-for-associations/:id} : delete the "id" resourceWaitingForAssociation.
     *
     * @param id the id of the resourceWaitingForAssociationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/resource-waiting-for-associations/{id}")
    public ResponseEntity<Void> deleteResourceWaitingForAssociation(@PathVariable Long id) {
        log.debug("REST request to delete ResourceWaitingForAssociation : {}", id);
        resourceWaitingForAssociationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
