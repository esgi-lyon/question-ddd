package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.AnswerSubmittedEventRepository;
import org.contextmapper.generated.gateway.service.AnswerSubmittedEventService;
import org.contextmapper.generated.gateway.service.dto.AnswerSubmittedEventDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.AnswerSubmittedEvent}.
 */
@RestController
@RequestMapping("/api")
public class AnswerSubmittedEventResource {

    private final Logger log = LoggerFactory.getLogger(AnswerSubmittedEventResource.class);

    private final AnswerSubmittedEventService answerSubmittedEventService;

    private final AnswerSubmittedEventRepository answerSubmittedEventRepository;

    public AnswerSubmittedEventResource(
        AnswerSubmittedEventService answerSubmittedEventService,
        AnswerSubmittedEventRepository answerSubmittedEventRepository
    ) {
        this.answerSubmittedEventService = answerSubmittedEventService;
        this.answerSubmittedEventRepository = answerSubmittedEventRepository;
    }

    /**
     * {@code GET  /answer-submitted-events} : get all the answerSubmittedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of answerSubmittedEvents in body.
     */
    @GetMapping("/answer-submitted-events")
    public Mono<List<AnswerSubmittedEventDTO>> getAllAnswerSubmittedEvents() {
        log.debug("REST request to get all AnswerSubmittedEvents");
        return answerSubmittedEventService.findAll().collectList();
    }

    /**
     * {@code GET  /answer-submitted-events} : get all the answerSubmittedEvents as a stream.
     * @return the {@link Flux} of answerSubmittedEvents.
     */
    @GetMapping(value = "/answer-submitted-events", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<AnswerSubmittedEventDTO> getAllAnswerSubmittedEventsAsStream() {
        log.debug("REST request to get all AnswerSubmittedEvents as a stream");
        return answerSubmittedEventService.findAll();
    }

    /**
     * {@code GET  /answer-submitted-events/:id} : get the "id" answerSubmittedEvent.
     *
     * @param id the id of the answerSubmittedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the answerSubmittedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/answer-submitted-events/{id}")
    public Mono<ResponseEntity<AnswerSubmittedEventDTO>> getAnswerSubmittedEvent(@PathVariable Long id) {
        log.debug("REST request to get AnswerSubmittedEvent : {}", id);
        Mono<AnswerSubmittedEventDTO> answerSubmittedEventDTO = answerSubmittedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(answerSubmittedEventDTO);
    }
}
