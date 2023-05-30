package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.LoggedUserStatsViewedEventRepository;
import org.contextmapper.generated.statcontext.service.LoggedUserStatsViewedEventService;
import org.contextmapper.generated.statcontext.service.dto.LoggedUserStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.LoggedUserStatsViewedEvent}.
 */
@RestController
@RequestMapping("/api")
public class LoggedUserStatsViewedEventResource {

    private final Logger log = LoggerFactory.getLogger(LoggedUserStatsViewedEventResource.class);

    private final LoggedUserStatsViewedEventService loggedUserStatsViewedEventService;

    private final LoggedUserStatsViewedEventRepository loggedUserStatsViewedEventRepository;

    public LoggedUserStatsViewedEventResource(
        LoggedUserStatsViewedEventService loggedUserStatsViewedEventService,
        LoggedUserStatsViewedEventRepository loggedUserStatsViewedEventRepository
    ) {
        this.loggedUserStatsViewedEventService = loggedUserStatsViewedEventService;
        this.loggedUserStatsViewedEventRepository = loggedUserStatsViewedEventRepository;
    }

    /**
     * {@code GET  /logged-user-stats-viewed-events} : get all the loggedUserStatsViewedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of loggedUserStatsViewedEvents in body.
     */
    @GetMapping("/logged-user-stats-viewed-events")
    public List<LoggedUserStatsViewedEventDTO> getAllLoggedUserStatsViewedEvents() {
        log.debug("REST request to get all LoggedUserStatsViewedEvents");
        return loggedUserStatsViewedEventService.findAll();
    }

    /**
     * {@code GET  /logged-user-stats-viewed-events/:id} : get the "id" loggedUserStatsViewedEvent.
     *
     * @param id the id of the loggedUserStatsViewedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loggedUserStatsViewedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/logged-user-stats-viewed-events/{id}")
    public ResponseEntity<LoggedUserStatsViewedEventDTO> getLoggedUserStatsViewedEvent(@PathVariable Long id) {
        log.debug("REST request to get LoggedUserStatsViewedEvent : {}", id);
        Optional<LoggedUserStatsViewedEventDTO> loggedUserStatsViewedEventDTO = loggedUserStatsViewedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loggedUserStatsViewedEventDTO);
    }
}
