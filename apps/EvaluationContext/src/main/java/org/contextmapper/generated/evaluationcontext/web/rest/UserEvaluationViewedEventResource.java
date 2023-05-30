package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.UserEvaluationViewedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.UserEvaluationViewedEventService;
import org.contextmapper.generated.evaluationcontext.service.dto.UserEvaluationViewedEventDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.UserEvaluationViewedEvent}.
 */
@RestController
@RequestMapping("/api")
public class UserEvaluationViewedEventResource {

    private final Logger log = LoggerFactory.getLogger(UserEvaluationViewedEventResource.class);

    private final UserEvaluationViewedEventService userEvaluationViewedEventService;

    private final UserEvaluationViewedEventRepository userEvaluationViewedEventRepository;

    public UserEvaluationViewedEventResource(
        UserEvaluationViewedEventService userEvaluationViewedEventService,
        UserEvaluationViewedEventRepository userEvaluationViewedEventRepository
    ) {
        this.userEvaluationViewedEventService = userEvaluationViewedEventService;
        this.userEvaluationViewedEventRepository = userEvaluationViewedEventRepository;
    }

    /**
     * {@code GET  /user-evaluation-viewed-events} : get all the userEvaluationViewedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userEvaluationViewedEvents in body.
     */
    @GetMapping("/user-evaluation-viewed-events")
    public List<UserEvaluationViewedEventDTO> getAllUserEvaluationViewedEvents() {
        log.debug("REST request to get all UserEvaluationViewedEvents");
        return userEvaluationViewedEventService.findAll();
    }

    /**
     * {@code GET  /user-evaluation-viewed-events/:id} : get the "id" userEvaluationViewedEvent.
     *
     * @param id the id of the userEvaluationViewedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userEvaluationViewedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-evaluation-viewed-events/{id}")
    public ResponseEntity<UserEvaluationViewedEventDTO> getUserEvaluationViewedEvent(@PathVariable Long id) {
        log.debug("REST request to get UserEvaluationViewedEvent : {}", id);
        Optional<UserEvaluationViewedEventDTO> userEvaluationViewedEventDTO = userEvaluationViewedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userEvaluationViewedEventDTO);
    }
}
