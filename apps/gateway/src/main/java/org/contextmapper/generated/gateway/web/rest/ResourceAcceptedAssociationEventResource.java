package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.ResourceAcceptedAssociationEventRepository;
import org.contextmapper.generated.gateway.service.ResourceAcceptedAssociationEventService;
import org.contextmapper.generated.gateway.service.dto.ResourceAcceptedAssociationEventDTO;
import org.contextmapper.generated.gateway.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.ResourceAcceptedAssociationEvent}.
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
    public Mono<List<ResourceAcceptedAssociationEventDTO>> getAllResourceAcceptedAssociationEvents() {
        log.debug("REST request to get all ResourceAcceptedAssociationEvents");
        return resourceAcceptedAssociationEventService.findAll().collectList();
    }

    /**
     * {@code GET  /resource-accepted-association-events} : get all the resourceAcceptedAssociationEvents as a stream.
     * @return the {@link Flux} of resourceAcceptedAssociationEvents.
     */
    @GetMapping(value = "/resource-accepted-association-events", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<ResourceAcceptedAssociationEventDTO> getAllResourceAcceptedAssociationEventsAsStream() {
        log.debug("REST request to get all ResourceAcceptedAssociationEvents as a stream");
        return resourceAcceptedAssociationEventService.findAll();
    }

    /**
     * {@code GET  /resource-accepted-association-events/:id} : get the "id" resourceAcceptedAssociationEvent.
     *
     * @param id the id of the resourceAcceptedAssociationEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourceAcceptedAssociationEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resource-accepted-association-events/{id}")
    public Mono<ResponseEntity<ResourceAcceptedAssociationEventDTO>> getResourceAcceptedAssociationEvent(@PathVariable Long id) {
        log.debug("REST request to get ResourceAcceptedAssociationEvent : {}", id);
        Mono<ResourceAcceptedAssociationEventDTO> resourceAcceptedAssociationEventDTO = resourceAcceptedAssociationEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resourceAcceptedAssociationEventDTO);
    }
}
