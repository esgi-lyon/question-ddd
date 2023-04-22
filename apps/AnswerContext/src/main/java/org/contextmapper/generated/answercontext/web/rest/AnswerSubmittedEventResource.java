package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.AnswerSubmittedEventRepository;
import org.contextmapper.generated.answercontext.service.AnswerSubmittedEventService;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmittedEventDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.AnswerSubmittedEvent}.
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
    public List<AnswerSubmittedEventDTO> getAllAnswerSubmittedEvents() {
        log.debug("REST request to get all AnswerSubmittedEvents");
        return answerSubmittedEventService.findAll();
    }

    /**
     * {@code GET  /answer-submitted-events/:id} : get the "id" answerSubmittedEvent.
     *
     * @param id the id of the answerSubmittedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the answerSubmittedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/answer-submitted-events/{id}")
    public ResponseEntity<AnswerSubmittedEventDTO> getAnswerSubmittedEvent(@PathVariable Long id) {
        log.debug("REST request to get AnswerSubmittedEvent : {}", id);
        Optional<AnswerSubmittedEventDTO> answerSubmittedEventDTO = answerSubmittedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(answerSubmittedEventDTO);
    }
}
