package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.EvaluationCreatedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.EvaluationCreatedEventService;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationCreatedEventDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.EvaluationCreatedEvent}.
 */
@RestController
@RequestMapping("/api")
public class EvaluationCreatedEventResource {

    private final Logger log = LoggerFactory.getLogger(EvaluationCreatedEventResource.class);

    private final EvaluationCreatedEventService evaluationCreatedEventService;

    private final EvaluationCreatedEventRepository evaluationCreatedEventRepository;

    public EvaluationCreatedEventResource(
        EvaluationCreatedEventService evaluationCreatedEventService,
        EvaluationCreatedEventRepository evaluationCreatedEventRepository
    ) {
        this.evaluationCreatedEventService = evaluationCreatedEventService;
        this.evaluationCreatedEventRepository = evaluationCreatedEventRepository;
    }

    /**
     * {@code GET  /evaluation-created-events} : get all the evaluationCreatedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of evaluationCreatedEvents in body.
     */
    @GetMapping("/evaluation-created-events")
    public List<EvaluationCreatedEventDTO> getAllEvaluationCreatedEvents() {
        log.debug("REST request to get all EvaluationCreatedEvents");
        return evaluationCreatedEventService.findAll();
    }

    /**
     * {@code GET  /evaluation-created-events/:id} : get the "id" evaluationCreatedEvent.
     *
     * @param id the id of the evaluationCreatedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluationCreatedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evaluation-created-events/{id}")
    public ResponseEntity<EvaluationCreatedEventDTO> getEvaluationCreatedEvent(@PathVariable Long id) {
        log.debug("REST request to get EvaluationCreatedEvent : {}", id);
        Optional<EvaluationCreatedEventDTO> evaluationCreatedEventDTO = evaluationCreatedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluationCreatedEventDTO);
    }
}
