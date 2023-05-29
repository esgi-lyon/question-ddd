package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.LeaderBoardViewedEventRepository;
import org.contextmapper.generated.statcontext.service.LeaderBoardViewedEventService;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardViewedEventDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.LeaderBoardViewedEvent}.
 */
@RestController
@RequestMapping("/api")
public class LeaderBoardViewedEventResource {

    private final Logger log = LoggerFactory.getLogger(LeaderBoardViewedEventResource.class);

    private final LeaderBoardViewedEventService leaderBoardViewedEventService;

    private final LeaderBoardViewedEventRepository leaderBoardViewedEventRepository;

    public LeaderBoardViewedEventResource(
        LeaderBoardViewedEventService leaderBoardViewedEventService,
        LeaderBoardViewedEventRepository leaderBoardViewedEventRepository
    ) {
        this.leaderBoardViewedEventService = leaderBoardViewedEventService;
        this.leaderBoardViewedEventRepository = leaderBoardViewedEventRepository;
    }

    /**
     * {@code GET  /leader-board-viewed-events} : get all the leaderBoardViewedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of leaderBoardViewedEvents in body.
     */
    @GetMapping("/leader-board-viewed-events")
    public List<LeaderBoardViewedEventDTO> getAllLeaderBoardViewedEvents() {
        log.debug("REST request to get all LeaderBoardViewedEvents");
        return leaderBoardViewedEventService.findAll();
    }

    /**
     * {@code GET  /leader-board-viewed-events/:id} : get the "id" leaderBoardViewedEvent.
     *
     * @param id the id of the leaderBoardViewedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the leaderBoardViewedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/leader-board-viewed-events/{id}")
    public ResponseEntity<LeaderBoardViewedEventDTO> getLeaderBoardViewedEvent(@PathVariable Long id) {
        log.debug("REST request to get LeaderBoardViewedEvent : {}", id);
        Optional<LeaderBoardViewedEventDTO> leaderBoardViewedEventDTO = leaderBoardViewedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(leaderBoardViewedEventDTO);
    }
}
