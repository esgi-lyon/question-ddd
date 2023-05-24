package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.NewAnswerNotifiedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.NewAnswerNotifiedEventService;
import org.contextmapper.generated.evaluationcontext.service.dto.NewAnswerNotifiedEventDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.NewAnswerNotifiedEvent}.
 */
@RestController
@RequestMapping("/api")
public class NewAnswerNotifiedEventResource {

    private final Logger log = LoggerFactory.getLogger(NewAnswerNotifiedEventResource.class);

    private final NewAnswerNotifiedEventService newAnswerNotifiedEventService;

    private final NewAnswerNotifiedEventRepository newAnswerNotifiedEventRepository;

    public NewAnswerNotifiedEventResource(
        NewAnswerNotifiedEventService newAnswerNotifiedEventService,
        NewAnswerNotifiedEventRepository newAnswerNotifiedEventRepository
    ) {
        this.newAnswerNotifiedEventService = newAnswerNotifiedEventService;
        this.newAnswerNotifiedEventRepository = newAnswerNotifiedEventRepository;
    }

    /**
     * {@code GET  /new-answer-notified-events} : get all the newAnswerNotifiedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of newAnswerNotifiedEvents in body.
     */
    @GetMapping("/new-answer-notified-events")
    public List<NewAnswerNotifiedEventDTO> getAllNewAnswerNotifiedEvents() {
        log.debug("REST request to get all NewAnswerNotifiedEvents");
        return newAnswerNotifiedEventService.findAll();
    }

    /**
     * {@code GET  /new-answer-notified-events/:id} : get the "id" newAnswerNotifiedEvent.
     *
     * @param id the id of the newAnswerNotifiedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the newAnswerNotifiedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/new-answer-notified-events/{id}")
    public ResponseEntity<NewAnswerNotifiedEventDTO> getNewAnswerNotifiedEvent(@PathVariable Long id) {
        log.debug("REST request to get NewAnswerNotifiedEvent : {}", id);
        Optional<NewAnswerNotifiedEventDTO> newAnswerNotifiedEventDTO = newAnswerNotifiedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(newAnswerNotifiedEventDTO);
    }
}
