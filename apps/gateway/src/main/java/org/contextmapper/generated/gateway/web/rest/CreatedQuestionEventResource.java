package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.CreatedQuestionEventRepository;
import org.contextmapper.generated.gateway.service.CreatedQuestionEventService;
import org.contextmapper.generated.gateway.service.dto.CreatedQuestionEventDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.CreatedQuestionEvent}.
 */
@RestController
@RequestMapping("/api")
public class CreatedQuestionEventResource {

    private final Logger log = LoggerFactory.getLogger(CreatedQuestionEventResource.class);

    private final CreatedQuestionEventService createdQuestionEventService;

    private final CreatedQuestionEventRepository createdQuestionEventRepository;

    public CreatedQuestionEventResource(
        CreatedQuestionEventService createdQuestionEventService,
        CreatedQuestionEventRepository createdQuestionEventRepository
    ) {
        this.createdQuestionEventService = createdQuestionEventService;
        this.createdQuestionEventRepository = createdQuestionEventRepository;
    }

    /**
     * {@code GET  /created-question-events} : get all the createdQuestionEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createdQuestionEvents in body.
     */
    @GetMapping("/created-question-events")
    public Mono<List<CreatedQuestionEventDTO>> getAllCreatedQuestionEvents() {
        log.debug("REST request to get all CreatedQuestionEvents");
        return createdQuestionEventService.findAll().collectList();
    }

    /**
     * {@code GET  /created-question-events} : get all the createdQuestionEvents as a stream.
     * @return the {@link Flux} of createdQuestionEvents.
     */
    @GetMapping(value = "/created-question-events", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<CreatedQuestionEventDTO> getAllCreatedQuestionEventsAsStream() {
        log.debug("REST request to get all CreatedQuestionEvents as a stream");
        return createdQuestionEventService.findAll();
    }

    /**
     * {@code GET  /created-question-events/:id} : get the "id" createdQuestionEvent.
     *
     * @param id the id of the createdQuestionEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createdQuestionEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/created-question-events/{id}")
    public Mono<ResponseEntity<CreatedQuestionEventDTO>> getCreatedQuestionEvent(@PathVariable Long id) {
        log.debug("REST request to get CreatedQuestionEvent : {}", id);
        Mono<CreatedQuestionEventDTO> createdQuestionEventDTO = createdQuestionEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createdQuestionEventDTO);
    }
}
