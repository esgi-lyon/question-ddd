package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.ResourceRefusedAssociationEventRepository;
import org.contextmapper.generated.gateway.service.ResourceRefusedAssociationEventService;
import org.contextmapper.generated.gateway.service.dto.ResourceRefusedAssociationEventDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.ResourceRefusedAssociationEvent}.
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
    public Mono<List<ResourceRefusedAssociationEventDTO>> getAllResourceRefusedAssociationEvents() {
        log.debug("REST request to get all ResourceRefusedAssociationEvents");
        return resourceRefusedAssociationEventService.findAll().collectList();
    }

    /**
     * {@code GET  /resource-refused-association-events} : get all the resourceRefusedAssociationEvents as a stream.
     * @return the {@link Flux} of resourceRefusedAssociationEvents.
     */
    @GetMapping(value = "/resource-refused-association-events", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<ResourceRefusedAssociationEventDTO> getAllResourceRefusedAssociationEventsAsStream() {
        log.debug("REST request to get all ResourceRefusedAssociationEvents as a stream");
        return resourceRefusedAssociationEventService.findAll();
    }

    /**
     * {@code GET  /resource-refused-association-events/:id} : get the "id" resourceRefusedAssociationEvent.
     *
     * @param id the id of the resourceRefusedAssociationEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourceRefusedAssociationEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resource-refused-association-events/{id}")
    public Mono<ResponseEntity<ResourceRefusedAssociationEventDTO>> getResourceRefusedAssociationEvent(@PathVariable Long id) {
        log.debug("REST request to get ResourceRefusedAssociationEvent : {}", id);
        Mono<ResourceRefusedAssociationEventDTO> resourceRefusedAssociationEventDTO = resourceRefusedAssociationEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resourceRefusedAssociationEventDTO);
    }
}
