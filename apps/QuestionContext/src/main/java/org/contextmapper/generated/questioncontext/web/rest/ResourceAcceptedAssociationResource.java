package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.repository.ResourceAcceptedAssociationRepository;
import org.contextmapper.generated.questioncontext.service.ResourceAcceptedAssociationService;
import org.contextmapper.generated.questioncontext.service.dto.ResourceAcceptedAssociationDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.ResourceAcceptedAssociation}.
 */
@RestController
@RequestMapping("/api")
public class ResourceAcceptedAssociationResource {

    private final Logger log = LoggerFactory.getLogger(ResourceAcceptedAssociationResource.class);

    private static final String ENTITY_NAME = "questionContextResourceAcceptedAssociation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResourceAcceptedAssociationService resourceAcceptedAssociationService;

    private final ResourceAcceptedAssociationRepository resourceAcceptedAssociationRepository;

    public ResourceAcceptedAssociationResource(
        ResourceAcceptedAssociationService resourceAcceptedAssociationService,
        ResourceAcceptedAssociationRepository resourceAcceptedAssociationRepository
    ) {
        this.resourceAcceptedAssociationService = resourceAcceptedAssociationService;
        this.resourceAcceptedAssociationRepository = resourceAcceptedAssociationRepository;
    }

    /**
     * {@code POST  /resource-accepted-associations} : Create a new resourceAcceptedAssociation.
     *
     * @param resourceAcceptedAssociationDTO the resourceAcceptedAssociationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resourceAcceptedAssociationDTO, or with status {@code 400 (Bad Request)} if the resourceAcceptedAssociation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/resource-accepted-associations")
    public ResponseEntity<ResourceAcceptedAssociationDTO> createResourceAcceptedAssociation(
        @RequestBody ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ResourceAcceptedAssociation : {}", resourceAcceptedAssociationDTO);
        if (resourceAcceptedAssociationDTO.getId() != null) {
            throw new BadRequestAlertException("A new resourceAcceptedAssociation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResourceAcceptedAssociationDTO result = resourceAcceptedAssociationService.save(resourceAcceptedAssociationDTO);
        return ResponseEntity
            .created(new URI("/api/resource-accepted-associations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /resource-accepted-associations/:id} : Updates an existing resourceAcceptedAssociation.
     *
     * @param id the id of the resourceAcceptedAssociationDTO to save.
     * @param resourceAcceptedAssociationDTO the resourceAcceptedAssociationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourceAcceptedAssociationDTO,
     * or with status {@code 400 (Bad Request)} if the resourceAcceptedAssociationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resourceAcceptedAssociationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/resource-accepted-associations/{id}")
    public ResponseEntity<ResourceAcceptedAssociationDTO> updateResourceAcceptedAssociation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ResourceAcceptedAssociation : {}, {}", id, resourceAcceptedAssociationDTO);
        if (resourceAcceptedAssociationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resourceAcceptedAssociationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resourceAcceptedAssociationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ResourceAcceptedAssociationDTO result = resourceAcceptedAssociationService.update(resourceAcceptedAssociationDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resourceAcceptedAssociationDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /resource-accepted-associations/:id} : Partial updates given fields of an existing resourceAcceptedAssociation, field will ignore if it is null
     *
     * @param id the id of the resourceAcceptedAssociationDTO to save.
     * @param resourceAcceptedAssociationDTO the resourceAcceptedAssociationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resourceAcceptedAssociationDTO,
     * or with status {@code 400 (Bad Request)} if the resourceAcceptedAssociationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the resourceAcceptedAssociationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the resourceAcceptedAssociationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/resource-accepted-associations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResourceAcceptedAssociationDTO> partialUpdateResourceAcceptedAssociation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResourceAcceptedAssociationDTO resourceAcceptedAssociationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResourceAcceptedAssociation partially : {}, {}", id, resourceAcceptedAssociationDTO);
        if (resourceAcceptedAssociationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resourceAcceptedAssociationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resourceAcceptedAssociationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResourceAcceptedAssociationDTO> result = resourceAcceptedAssociationService.partialUpdate(resourceAcceptedAssociationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resourceAcceptedAssociationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /resource-accepted-associations} : get all the resourceAcceptedAssociations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resourceAcceptedAssociations in body.
     */
    @GetMapping("/resource-accepted-associations")
    public List<ResourceAcceptedAssociationDTO> getAllResourceAcceptedAssociations() {
        log.debug("REST request to get all ResourceAcceptedAssociations");
        return resourceAcceptedAssociationService.findAll();
    }

    /**
     * {@code GET  /resource-accepted-associations/:id} : get the "id" resourceAcceptedAssociation.
     *
     * @param id the id of the resourceAcceptedAssociationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourceAcceptedAssociationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resource-accepted-associations/{id}")
    public ResponseEntity<ResourceAcceptedAssociationDTO> getResourceAcceptedAssociation(@PathVariable Long id) {
        log.debug("REST request to get ResourceAcceptedAssociation : {}", id);
        Optional<ResourceAcceptedAssociationDTO> resourceAcceptedAssociationDTO = resourceAcceptedAssociationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resourceAcceptedAssociationDTO);
    }

    /**
     * {@code DELETE  /resource-accepted-associations/:id} : delete the "id" resourceAcceptedAssociation.
     *
     * @param id the id of the resourceAcceptedAssociationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/resource-accepted-associations/{id}")
    public ResponseEntity<Void> deleteResourceAcceptedAssociation(@PathVariable Long id) {
        log.debug("REST request to delete ResourceAcceptedAssociation : {}", id);
        resourceAcceptedAssociationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
