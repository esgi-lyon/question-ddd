package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.AwardedPointEventRepository;
import org.contextmapper.generated.gateway.service.AwardedPointEventService;
import org.contextmapper.generated.gateway.service.dto.AwardedPointEventDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.AwardedPointEvent}.
 */
@RestController
@RequestMapping("/api")
public class AwardedPointEventResource {

    private final Logger log = LoggerFactory.getLogger(AwardedPointEventResource.class);

    private final AwardedPointEventService awardedPointEventService;

    private final AwardedPointEventRepository awardedPointEventRepository;

    public AwardedPointEventResource(
        AwardedPointEventService awardedPointEventService,
        AwardedPointEventRepository awardedPointEventRepository
    ) {
        this.awardedPointEventService = awardedPointEventService;
        this.awardedPointEventRepository = awardedPointEventRepository;
    }

    /**
     * {@code GET  /awarded-point-events} : get all the awardedPointEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of awardedPointEvents in body.
     */
    @GetMapping("/awarded-point-events")
    public Mono<List<AwardedPointEventDTO>> getAllAwardedPointEvents() {
        log.debug("REST request to get all AwardedPointEvents");
        return awardedPointEventService.findAll().collectList();
    }

    /**
     * {@code GET  /awarded-point-events} : get all the awardedPointEvents as a stream.
     * @return the {@link Flux} of awardedPointEvents.
     */
    @GetMapping(value = "/awarded-point-events", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<AwardedPointEventDTO> getAllAwardedPointEventsAsStream() {
        log.debug("REST request to get all AwardedPointEvents as a stream");
        return awardedPointEventService.findAll();
    }

    /**
     * {@code GET  /awarded-point-events/:id} : get the "id" awardedPointEvent.
     *
     * @param id the id of the awardedPointEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the awardedPointEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/awarded-point-events/{id}")
    public Mono<ResponseEntity<AwardedPointEventDTO>> getAwardedPointEvent(@PathVariable Long id) {
        log.debug("REST request to get AwardedPointEvent : {}", id);
        Mono<AwardedPointEventDTO> awardedPointEventDTO = awardedPointEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(awardedPointEventDTO);
    }
}
