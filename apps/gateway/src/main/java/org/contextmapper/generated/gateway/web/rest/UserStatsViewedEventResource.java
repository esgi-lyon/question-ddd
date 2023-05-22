package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.UserStatsViewedEventRepository;
import org.contextmapper.generated.gateway.service.UserStatsViewedEventService;
import org.contextmapper.generated.gateway.service.dto.UserStatsViewedEventDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.UserStatsViewedEvent}.
 */
@RestController
@RequestMapping("/api")
public class UserStatsViewedEventResource {

    private final Logger log = LoggerFactory.getLogger(UserStatsViewedEventResource.class);

    private final UserStatsViewedEventService userStatsViewedEventService;

    private final UserStatsViewedEventRepository userStatsViewedEventRepository;

    public UserStatsViewedEventResource(
        UserStatsViewedEventService userStatsViewedEventService,
        UserStatsViewedEventRepository userStatsViewedEventRepository
    ) {
        this.userStatsViewedEventService = userStatsViewedEventService;
        this.userStatsViewedEventRepository = userStatsViewedEventRepository;
    }

    /**
     * {@code GET  /user-stats-viewed-events} : get all the userStatsViewedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userStatsViewedEvents in body.
     */
    @GetMapping("/user-stats-viewed-events")
    public Mono<List<UserStatsViewedEventDTO>> getAllUserStatsViewedEvents() {
        log.debug("REST request to get all UserStatsViewedEvents");
        return userStatsViewedEventService.findAll().collectList();
    }

    /**
     * {@code GET  /user-stats-viewed-events} : get all the userStatsViewedEvents as a stream.
     * @return the {@link Flux} of userStatsViewedEvents.
     */
    @GetMapping(value = "/user-stats-viewed-events", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<UserStatsViewedEventDTO> getAllUserStatsViewedEventsAsStream() {
        log.debug("REST request to get all UserStatsViewedEvents as a stream");
        return userStatsViewedEventService.findAll();
    }

    /**
     * {@code GET  /user-stats-viewed-events/:id} : get the "id" userStatsViewedEvent.
     *
     * @param id the id of the userStatsViewedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userStatsViewedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-stats-viewed-events/{id}")
    public Mono<ResponseEntity<UserStatsViewedEventDTO>> getUserStatsViewedEvent(@PathVariable Long id) {
        log.debug("REST request to get UserStatsViewedEvent : {}", id);
        Mono<UserStatsViewedEventDTO> userStatsViewedEventDTO = userStatsViewedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userStatsViewedEventDTO);
    }
}
