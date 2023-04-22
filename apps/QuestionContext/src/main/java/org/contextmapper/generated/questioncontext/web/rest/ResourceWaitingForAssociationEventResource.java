package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.repository.ResourceWaitingForAssociationEventRepository;
import org.contextmapper.generated.questioncontext.service.ResourceWaitingForAssociationEventService;
import org.contextmapper.generated.questioncontext.service.dto.ResourceWaitingForAssociationEventDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.ResourceWaitingForAssociationEvent}.
 */
@RestController
@RequestMapping("/api")
public class ResourceWaitingForAssociationEventResource {

    private final Logger log = LoggerFactory.getLogger(ResourceWaitingForAssociationEventResource.class);

    private final ResourceWaitingForAssociationEventService resourceWaitingForAssociationEventService;

    private final ResourceWaitingForAssociationEventRepository resourceWaitingForAssociationEventRepository;

    public ResourceWaitingForAssociationEventResource(
        ResourceWaitingForAssociationEventService resourceWaitingForAssociationEventService,
        ResourceWaitingForAssociationEventRepository resourceWaitingForAssociationEventRepository
    ) {
        this.resourceWaitingForAssociationEventService = resourceWaitingForAssociationEventService;
        this.resourceWaitingForAssociationEventRepository = resourceWaitingForAssociationEventRepository;
    }

    /**
     * {@code GET  /resource-waiting-for-association-events} : get all the resourceWaitingForAssociationEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resourceWaitingForAssociationEvents in body.
     */
    @GetMapping("/resource-waiting-for-association-events")
    public List<ResourceWaitingForAssociationEventDTO> getAllResourceWaitingForAssociationEvents() {
        log.debug("REST request to get all ResourceWaitingForAssociationEvents");
        return resourceWaitingForAssociationEventService.findAll();
    }

    /**
     * {@code GET  /resource-waiting-for-association-events/:id} : get the "id" resourceWaitingForAssociationEvent.
     *
     * @param id the id of the resourceWaitingForAssociationEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourceWaitingForAssociationEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resource-waiting-for-association-events/{id}")
    public ResponseEntity<ResourceWaitingForAssociationEventDTO> getResourceWaitingForAssociationEvent(@PathVariable Long id) {
        log.debug("REST request to get ResourceWaitingForAssociationEvent : {}", id);
        Optional<ResourceWaitingForAssociationEventDTO> resourceWaitingForAssociationEventDTO = resourceWaitingForAssociationEventService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(resourceWaitingForAssociationEventDTO);
    }
}
