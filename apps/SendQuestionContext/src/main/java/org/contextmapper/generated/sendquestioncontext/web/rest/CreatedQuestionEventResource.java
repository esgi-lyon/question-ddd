package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.CreatedQuestionEventRepository;
import org.contextmapper.generated.sendquestioncontext.service.CreatedQuestionEventService;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreatedQuestionEventDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.CreatedQuestionEvent}.
 */
@RestController
@RequestMapping("/api")
public class CreatedQuestionEventResource {

    private final Logger log = LoggerFactory.getLogger(CreatedQuestionEventResource.class);

    private final CreatedQuestionEventService createdQuestionEventService;

    private final CreatedQuestionEventRepository createdQuestionEventRepository;

    public CreatedQuestionEventResource(
        CreatedQuestionEventService createdQuestionEventService,
        CreatedQuestionEventRepository createdQuestionEventRepository
    ) {
        this.createdQuestionEventService = createdQuestionEventService;
        this.createdQuestionEventRepository = createdQuestionEventRepository;
    }

    /**
     * {@code GET  /created-question-events} : get all the createdQuestionEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createdQuestionEvents in body.
     */
    @GetMapping("/created-question-events")
    public List<CreatedQuestionEventDTO> getAllCreatedQuestionEvents() {
        log.debug("REST request to get all CreatedQuestionEvents");
        return createdQuestionEventService.findAll();
    }

    /**
     * {@code GET  /created-question-events/:id} : get the "id" createdQuestionEvent.
     *
     * @param id the id of the createdQuestionEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createdQuestionEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/created-question-events/{id}")
    public ResponseEntity<CreatedQuestionEventDTO> getCreatedQuestionEvent(@PathVariable Long id) {
        log.debug("REST request to get CreatedQuestionEvent : {}", id);
        Optional<CreatedQuestionEventDTO> createdQuestionEventDTO = createdQuestionEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createdQuestionEventDTO);
    }
}
