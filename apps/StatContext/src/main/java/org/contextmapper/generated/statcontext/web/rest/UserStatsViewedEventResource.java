package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.UserStatsViewedEventRepository;
import org.contextmapper.generated.statcontext.service.UserStatsViewedEventService;
import org.contextmapper.generated.statcontext.service.dto.UserStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.UserStatsViewedEvent}.
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
    public List<UserStatsViewedEventDTO> getAllUserStatsViewedEvents() {
        log.debug("REST request to get all UserStatsViewedEvents");
        return userStatsViewedEventService.findAll();
    }

    /**
     * {@code GET  /user-stats-viewed-events/:id} : get the "id" userStatsViewedEvent.
     *
     * @param id the id of the userStatsViewedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userStatsViewedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-stats-viewed-events/{id}")
    public ResponseEntity<UserStatsViewedEventDTO> getUserStatsViewedEvent(@PathVariable Long id) {
        log.debug("REST request to get UserStatsViewedEvent : {}", id);
        Optional<UserStatsViewedEventDTO> userStatsViewedEventDTO = userStatsViewedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userStatsViewedEventDTO);
    }
}
