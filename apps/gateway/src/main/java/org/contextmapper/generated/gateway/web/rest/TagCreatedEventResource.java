package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.TagCreatedEventRepository;
import org.contextmapper.generated.gateway.service.TagCreatedEventService;
import org.contextmapper.generated.gateway.service.dto.TagCreatedEventDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.TagCreatedEvent}.
 */
@RestController
@RequestMapping("/api")
public class TagCreatedEventResource {

    private final Logger log = LoggerFactory.getLogger(TagCreatedEventResource.class);

    private final TagCreatedEventService tagCreatedEventService;

    private final TagCreatedEventRepository tagCreatedEventRepository;

    public TagCreatedEventResource(TagCreatedEventService tagCreatedEventService, TagCreatedEventRepository tagCreatedEventRepository) {
        this.tagCreatedEventService = tagCreatedEventService;
        this.tagCreatedEventRepository = tagCreatedEventRepository;
    }

    /**
     * {@code GET  /tag-created-events} : get all the tagCreatedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagCreatedEvents in body.
     */
    @GetMapping("/tag-created-events")
    public Mono<List<TagCreatedEventDTO>> getAllTagCreatedEvents() {
        log.debug("REST request to get all TagCreatedEvents");
        return tagCreatedEventService.findAll().collectList();
    }

    /**
     * {@code GET  /tag-created-events} : get all the tagCreatedEvents as a stream.
     * @return the {@link Flux} of tagCreatedEvents.
     */
    @GetMapping(value = "/tag-created-events", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<TagCreatedEventDTO> getAllTagCreatedEventsAsStream() {
        log.debug("REST request to get all TagCreatedEvents as a stream");
        return tagCreatedEventService.findAll();
    }

    /**
     * {@code GET  /tag-created-events/:id} : get the "id" tagCreatedEvent.
     *
     * @param id the id of the tagCreatedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagCreatedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-created-events/{id}")
    public Mono<ResponseEntity<TagCreatedEventDTO>> getTagCreatedEvent(@PathVariable Long id) {
        log.debug("REST request to get TagCreatedEvent : {}", id);
        Mono<TagCreatedEventDTO> tagCreatedEventDTO = tagCreatedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagCreatedEventDTO);
    }
}
