package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.AnswerCreatedEventRepository;
import org.contextmapper.generated.answercontext.service.AnswerCreatedEventService;
import org.contextmapper.generated.answercontext.service.dto.AnswerCreatedEventDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.AnswerCreatedEvent}.
 */
@RestController
@RequestMapping("/api")
public class AnswerCreatedEventResource {

    private final Logger log = LoggerFactory.getLogger(AnswerCreatedEventResource.class);

    private final AnswerCreatedEventService answerCreatedEventService;

    private final AnswerCreatedEventRepository answerCreatedEventRepository;

    public AnswerCreatedEventResource(
        AnswerCreatedEventService answerCreatedEventService,
        AnswerCreatedEventRepository answerCreatedEventRepository
    ) {
        this.answerCreatedEventService = answerCreatedEventService;
        this.answerCreatedEventRepository = answerCreatedEventRepository;
    }

    /**
     * {@code GET  /answer-created-events} : get all the answerCreatedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of answerCreatedEvents in body.
     */
    @GetMapping("/answer-created-events")
    public List<AnswerCreatedEventDTO> getAllAnswerCreatedEvents() {
        log.debug("REST request to get all AnswerCreatedEvents");
        return answerCreatedEventService.findAll();
    }

    /**
     * {@code GET  /answer-created-events/:id} : get the "id" answerCreatedEvent.
     *
     * @param id the id of the answerCreatedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the answerCreatedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/answer-created-events/{id}")
    public ResponseEntity<AnswerCreatedEventDTO> getAnswerCreatedEvent(@PathVariable Long id) {
        log.debug("REST request to get AnswerCreatedEvent : {}", id);
        Optional<AnswerCreatedEventDTO> answerCreatedEventDTO = answerCreatedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(answerCreatedEventDTO);
    }
}
