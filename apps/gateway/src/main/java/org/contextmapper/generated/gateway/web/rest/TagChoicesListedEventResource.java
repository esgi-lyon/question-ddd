package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.TagChoicesListedEventRepository;
import org.contextmapper.generated.gateway.service.TagChoicesListedEventService;
import org.contextmapper.generated.gateway.service.dto.TagChoicesListedEventDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.TagChoicesListedEvent}.
 */
@RestController
@RequestMapping("/api")
public class TagChoicesListedEventResource {

    private final Logger log = LoggerFactory.getLogger(TagChoicesListedEventResource.class);

    private final TagChoicesListedEventService tagChoicesListedEventService;

    private final TagChoicesListedEventRepository tagChoicesListedEventRepository;

    public TagChoicesListedEventResource(
        TagChoicesListedEventService tagChoicesListedEventService,
        TagChoicesListedEventRepository tagChoicesListedEventRepository
    ) {
        this.tagChoicesListedEventService = tagChoicesListedEventService;
        this.tagChoicesListedEventRepository = tagChoicesListedEventRepository;
    }

    /**
     * {@code GET  /tag-choices-listed-events} : get all the tagChoicesListedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagChoicesListedEvents in body.
     */
    @GetMapping("/tag-choices-listed-events")
    public Mono<List<TagChoicesListedEventDTO>> getAllTagChoicesListedEvents() {
        log.debug("REST request to get all TagChoicesListedEvents");
        return tagChoicesListedEventService.findAll().collectList();
    }

    /**
     * {@code GET  /tag-choices-listed-events} : get all the tagChoicesListedEvents as a stream.
     * @return the {@link Flux} of tagChoicesListedEvents.
     */
    @GetMapping(value = "/tag-choices-listed-events", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<TagChoicesListedEventDTO> getAllTagChoicesListedEventsAsStream() {
        log.debug("REST request to get all TagChoicesListedEvents as a stream");
        return tagChoicesListedEventService.findAll();
    }

    /**
     * {@code GET  /tag-choices-listed-events/:id} : get the "id" tagChoicesListedEvent.
     *
     * @param id the id of the tagChoicesListedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagChoicesListedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-choices-listed-events/{id}")
    public Mono<ResponseEntity<TagChoicesListedEventDTO>> getTagChoicesListedEvent(@PathVariable Long id) {
        log.debug("REST request to get TagChoicesListedEvent : {}", id);
        Mono<TagChoicesListedEventDTO> tagChoicesListedEventDTO = tagChoicesListedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagChoicesListedEventDTO);
    }
}
