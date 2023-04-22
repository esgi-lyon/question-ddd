package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.NotifiedQuestionEventRepository;
import org.contextmapper.generated.sendquestioncontext.service.NotifiedQuestionEventService;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedQuestionEventDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.NotifiedQuestionEvent}.
 */
@RestController
@RequestMapping("/api")
public class NotifiedQuestionEventResource {

    private final Logger log = LoggerFactory.getLogger(NotifiedQuestionEventResource.class);

    private final NotifiedQuestionEventService notifiedQuestionEventService;

    private final NotifiedQuestionEventRepository notifiedQuestionEventRepository;

    public NotifiedQuestionEventResource(
        NotifiedQuestionEventService notifiedQuestionEventService,
        NotifiedQuestionEventRepository notifiedQuestionEventRepository
    ) {
        this.notifiedQuestionEventService = notifiedQuestionEventService;
        this.notifiedQuestionEventRepository = notifiedQuestionEventRepository;
    }

    /**
     * {@code GET  /notified-question-events} : get all the notifiedQuestionEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notifiedQuestionEvents in body.
     */
    @GetMapping("/notified-question-events")
    public List<NotifiedQuestionEventDTO> getAllNotifiedQuestionEvents() {
        log.debug("REST request to get all NotifiedQuestionEvents");
        return notifiedQuestionEventService.findAll();
    }

    /**
     * {@code GET  /notified-question-events/:id} : get the "id" notifiedQuestionEvent.
     *
     * @param id the id of the notifiedQuestionEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notifiedQuestionEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notified-question-events/{id}")
    public ResponseEntity<NotifiedQuestionEventDTO> getNotifiedQuestionEvent(@PathVariable Long id) {
        log.debug("REST request to get NotifiedQuestionEvent : {}", id);
        Optional<NotifiedQuestionEventDTO> notifiedQuestionEventDTO = notifiedQuestionEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notifiedQuestionEventDTO);
    }
}
