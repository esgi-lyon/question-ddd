package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.AnswerCheckedEventRepository;
import org.contextmapper.generated.gateway.service.AnswerCheckedEventService;
import org.contextmapper.generated.gateway.service.dto.AnswerCheckedEventDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.AnswerCheckedEvent}.
 */
@RestController
@RequestMapping("/api")
public class AnswerCheckedEventResource {

    private final Logger log = LoggerFactory.getLogger(AnswerCheckedEventResource.class);

    private final AnswerCheckedEventService answerCheckedEventService;

    private final AnswerCheckedEventRepository answerCheckedEventRepository;

    public AnswerCheckedEventResource(
        AnswerCheckedEventService answerCheckedEventService,
        AnswerCheckedEventRepository answerCheckedEventRepository
    ) {
        this.answerCheckedEventService = answerCheckedEventService;
        this.answerCheckedEventRepository = answerCheckedEventRepository;
    }

    /**
     * {@code GET  /answer-checked-events} : get all the answerCheckedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of answerCheckedEvents in body.
     */
    @GetMapping("/answer-checked-events")
    public Mono<List<AnswerCheckedEventDTO>> getAllAnswerCheckedEvents() {
        log.debug("REST request to get all AnswerCheckedEvents");
        return answerCheckedEventService.findAll().collectList();
    }

    /**
     * {@code GET  /answer-checked-events} : get all the answerCheckedEvents as a stream.
     * @return the {@link Flux} of answerCheckedEvents.
     */
    @GetMapping(value = "/answer-checked-events", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<AnswerCheckedEventDTO> getAllAnswerCheckedEventsAsStream() {
        log.debug("REST request to get all AnswerCheckedEvents as a stream");
        return answerCheckedEventService.findAll();
    }

    /**
     * {@code GET  /answer-checked-events/:id} : get the "id" answerCheckedEvent.
     *
     * @param id the id of the answerCheckedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the answerCheckedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/answer-checked-events/{id}")
    public Mono<ResponseEntity<AnswerCheckedEventDTO>> getAnswerCheckedEvent(@PathVariable Long id) {
        log.debug("REST request to get AnswerCheckedEvent : {}", id);
        Mono<AnswerCheckedEventDTO> answerCheckedEventDTO = answerCheckedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(answerCheckedEventDTO);
    }
}
