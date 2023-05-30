package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.TagEvaluationViewedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.TagEvaluationViewedEventService;
import org.contextmapper.generated.evaluationcontext.service.dto.TagEvaluationViewedEventDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.TagEvaluationViewedEvent}.
 */
@RestController
@RequestMapping("/api")
public class TagEvaluationViewedEventResource {

    private final Logger log = LoggerFactory.getLogger(TagEvaluationViewedEventResource.class);

    private final TagEvaluationViewedEventService tagEvaluationViewedEventService;

    private final TagEvaluationViewedEventRepository tagEvaluationViewedEventRepository;

    public TagEvaluationViewedEventResource(
        TagEvaluationViewedEventService tagEvaluationViewedEventService,
        TagEvaluationViewedEventRepository tagEvaluationViewedEventRepository
    ) {
        this.tagEvaluationViewedEventService = tagEvaluationViewedEventService;
        this.tagEvaluationViewedEventRepository = tagEvaluationViewedEventRepository;
    }

    /**
     * {@code GET  /tag-evaluation-viewed-events} : get all the tagEvaluationViewedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagEvaluationViewedEvents in body.
     */
    @GetMapping("/tag-evaluation-viewed-events")
    public List<TagEvaluationViewedEventDTO> getAllTagEvaluationViewedEvents() {
        log.debug("REST request to get all TagEvaluationViewedEvents");
        return tagEvaluationViewedEventService.findAll();
    }

    /**
     * {@code GET  /tag-evaluation-viewed-events/:id} : get the "id" tagEvaluationViewedEvent.
     *
     * @param id the id of the tagEvaluationViewedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagEvaluationViewedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-evaluation-viewed-events/{id}")
    public ResponseEntity<TagEvaluationViewedEventDTO> getTagEvaluationViewedEvent(@PathVariable Long id) {
        log.debug("REST request to get TagEvaluationViewedEvent : {}", id);
        Optional<TagEvaluationViewedEventDTO> tagEvaluationViewedEventDTO = tagEvaluationViewedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagEvaluationViewedEventDTO);
    }
}
