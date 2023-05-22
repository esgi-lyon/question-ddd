package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.QuestionStatsViewedEventRepository;
import org.contextmapper.generated.gateway.service.QuestionStatsViewedEventService;
import org.contextmapper.generated.gateway.service.dto.QuestionStatsViewedEventDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.QuestionStatsViewedEvent}.
 */
@RestController
@RequestMapping("/api")
public class QuestionStatsViewedEventResource {

    private final Logger log = LoggerFactory.getLogger(QuestionStatsViewedEventResource.class);

    private final QuestionStatsViewedEventService questionStatsViewedEventService;

    private final QuestionStatsViewedEventRepository questionStatsViewedEventRepository;

    public QuestionStatsViewedEventResource(
        QuestionStatsViewedEventService questionStatsViewedEventService,
        QuestionStatsViewedEventRepository questionStatsViewedEventRepository
    ) {
        this.questionStatsViewedEventService = questionStatsViewedEventService;
        this.questionStatsViewedEventRepository = questionStatsViewedEventRepository;
    }

    /**
     * {@code GET  /question-stats-viewed-events} : get all the questionStatsViewedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionStatsViewedEvents in body.
     */
    @GetMapping("/question-stats-viewed-events")
    public Mono<List<QuestionStatsViewedEventDTO>> getAllQuestionStatsViewedEvents() {
        log.debug("REST request to get all QuestionStatsViewedEvents");
        return questionStatsViewedEventService.findAll().collectList();
    }

    /**
     * {@code GET  /question-stats-viewed-events} : get all the questionStatsViewedEvents as a stream.
     * @return the {@link Flux} of questionStatsViewedEvents.
     */
    @GetMapping(value = "/question-stats-viewed-events", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<QuestionStatsViewedEventDTO> getAllQuestionStatsViewedEventsAsStream() {
        log.debug("REST request to get all QuestionStatsViewedEvents as a stream");
        return questionStatsViewedEventService.findAll();
    }

    /**
     * {@code GET  /question-stats-viewed-events/:id} : get the "id" questionStatsViewedEvent.
     *
     * @param id the id of the questionStatsViewedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionStatsViewedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-stats-viewed-events/{id}")
    public Mono<ResponseEntity<QuestionStatsViewedEventDTO>> getQuestionStatsViewedEvent(@PathVariable Long id) {
        log.debug("REST request to get QuestionStatsViewedEvent : {}", id);
        Mono<QuestionStatsViewedEventDTO> questionStatsViewedEventDTO = questionStatsViewedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionStatsViewedEventDTO);
    }
}
