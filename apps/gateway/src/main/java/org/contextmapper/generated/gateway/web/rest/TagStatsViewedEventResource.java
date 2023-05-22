package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.TagStatsViewedEventRepository;
import org.contextmapper.generated.gateway.service.TagStatsViewedEventService;
import org.contextmapper.generated.gateway.service.dto.TagStatsViewedEventDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.TagStatsViewedEvent}.
 */
@RestController
@RequestMapping("/api")
public class TagStatsViewedEventResource {

    private final Logger log = LoggerFactory.getLogger(TagStatsViewedEventResource.class);

    private final TagStatsViewedEventService tagStatsViewedEventService;

    private final TagStatsViewedEventRepository tagStatsViewedEventRepository;

    public TagStatsViewedEventResource(
        TagStatsViewedEventService tagStatsViewedEventService,
        TagStatsViewedEventRepository tagStatsViewedEventRepository
    ) {
        this.tagStatsViewedEventService = tagStatsViewedEventService;
        this.tagStatsViewedEventRepository = tagStatsViewedEventRepository;
    }

    /**
     * {@code GET  /tag-stats-viewed-events} : get all the tagStatsViewedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagStatsViewedEvents in body.
     */
    @GetMapping("/tag-stats-viewed-events")
    public Mono<List<TagStatsViewedEventDTO>> getAllTagStatsViewedEvents() {
        log.debug("REST request to get all TagStatsViewedEvents");
        return tagStatsViewedEventService.findAll().collectList();
    }

    /**
     * {@code GET  /tag-stats-viewed-events} : get all the tagStatsViewedEvents as a stream.
     * @return the {@link Flux} of tagStatsViewedEvents.
     */
    @GetMapping(value = "/tag-stats-viewed-events", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<TagStatsViewedEventDTO> getAllTagStatsViewedEventsAsStream() {
        log.debug("REST request to get all TagStatsViewedEvents as a stream");
        return tagStatsViewedEventService.findAll();
    }

    /**
     * {@code GET  /tag-stats-viewed-events/:id} : get the "id" tagStatsViewedEvent.
     *
     * @param id the id of the tagStatsViewedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagStatsViewedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-stats-viewed-events/{id}")
    public Mono<ResponseEntity<TagStatsViewedEventDTO>> getTagStatsViewedEvent(@PathVariable Long id) {
        log.debug("REST request to get TagStatsViewedEvent : {}", id);
        Mono<TagStatsViewedEventDTO> tagStatsViewedEventDTO = tagStatsViewedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagStatsViewedEventDTO);
    }
}
