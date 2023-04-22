package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.repository.ResourceAcceptedAssociationEventRepository;
import org.contextmapper.generated.questioncontext.service.ResourceAcceptedAssociationEventService;
import org.contextmapper.generated.questioncontext.service.dto.ResourceAcceptedAssociationEventDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.ResourceAcceptedAssociationEvent}.
 */
@RestController
@RequestMapping("/api")
public class ResourceAcceptedAssociationEventResource {

    private final Logger log = LoggerFactory.getLogger(ResourceAcceptedAssociationEventResource.class);

    private final ResourceAcceptedAssociationEventService resourceAcceptedAssociationEventService;

    private final ResourceAcceptedAssociationEventRepository resourceAcceptedAssociationEventRepository;

    public ResourceAcceptedAssociationEventResource(
        ResourceAcceptedAssociationEventService resourceAcceptedAssociationEventService,
        ResourceAcceptedAssociationEventRepository resourceAcceptedAssociationEventRepository
    ) {
        this.resourceAcceptedAssociationEventService = resourceAcceptedAssociationEventService;
        this.resourceAcceptedAssociationEventRepository = resourceAcceptedAssociationEventRepository;
    }

    /**
     * {@code GET  /resource-accepted-association-events} : get all the resourceAcceptedAssociationEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resourceAcceptedAssociationEvents in body.
     */
    @GetMapping("/resource-accepted-association-events")
    public List<ResourceAcceptedAssociationEventDTO> getAllResourceAcceptedAssociationEvents() {
        log.debug("REST request to get all ResourceAcceptedAssociationEvents");
        return resourceAcceptedAssociationEventService.findAll();
    }

    /**
     * {@code GET  /resource-accepted-association-events/:id} : get the "id" resourceAcceptedAssociationEvent.
     *
     * @param id the id of the resourceAcceptedAssociationEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourceAcceptedAssociationEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resource-accepted-association-events/{id}")
    public ResponseEntity<ResourceAcceptedAssociationEventDTO> getResourceAcceptedAssociationEvent(@PathVariable Long id) {
        log.debug("REST request to get ResourceAcceptedAssociationEvent : {}", id);
        Optional<ResourceAcceptedAssociationEventDTO> resourceAcceptedAssociationEventDTO = resourceAcceptedAssociationEventService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(resourceAcceptedAssociationEventDTO);
    }
}
