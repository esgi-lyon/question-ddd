package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.repository.ResourceRefusedAssociationEventRepository;
import org.contextmapper.generated.questioncontext.service.ResourceRefusedAssociationEventService;
import org.contextmapper.generated.questioncontext.service.dto.ResourceRefusedAssociationEventDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.ResourceRefusedAssociationEvent}.
 */
@RestController
@RequestMapping("/api")
public class ResourceRefusedAssociationEventResource {

    private final Logger log = LoggerFactory.getLogger(ResourceRefusedAssociationEventResource.class);

    private final ResourceRefusedAssociationEventService resourceRefusedAssociationEventService;

    private final ResourceRefusedAssociationEventRepository resourceRefusedAssociationEventRepository;

    public ResourceRefusedAssociationEventResource(
        ResourceRefusedAssociationEventService resourceRefusedAssociationEventService,
        ResourceRefusedAssociationEventRepository resourceRefusedAssociationEventRepository
    ) {
        this.resourceRefusedAssociationEventService = resourceRefusedAssociationEventService;
        this.resourceRefusedAssociationEventRepository = resourceRefusedAssociationEventRepository;
    }

    /**
     * {@code GET  /resource-refused-association-events} : get all the resourceRefusedAssociationEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resourceRefusedAssociationEvents in body.
     */
    @GetMapping("/resource-refused-association-events")
    public List<ResourceRefusedAssociationEventDTO> getAllResourceRefusedAssociationEvents() {
        log.debug("REST request to get all ResourceRefusedAssociationEvents");
        return resourceRefusedAssociationEventService.findAll();
    }

    /**
     * {@code GET  /resource-refused-association-events/:id} : get the "id" resourceRefusedAssociationEvent.
     *
     * @param id the id of the resourceRefusedAssociationEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourceRefusedAssociationEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resource-refused-association-events/{id}")
    public ResponseEntity<ResourceRefusedAssociationEventDTO> getResourceRefusedAssociationEvent(@PathVariable Long id) {
        log.debug("REST request to get ResourceRefusedAssociationEvent : {}", id);
        Optional<ResourceRefusedAssociationEventDTO> resourceRefusedAssociationEventDTO = resourceRefusedAssociationEventService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(resourceRefusedAssociationEventDTO);
    }
}
