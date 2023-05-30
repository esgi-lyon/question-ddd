package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.QuestionEvaluationViewedEventRepository;
import org.contextmapper.generated.evaluationcontext.service.QuestionEvaluationViewedEventService;
import org.contextmapper.generated.evaluationcontext.service.dto.QuestionEvaluationViewedEventDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.QuestionEvaluationViewedEvent}.
 */
@RestController
@RequestMapping("/api")
public class QuestionEvaluationViewedEventResource {

    private final Logger log = LoggerFactory.getLogger(QuestionEvaluationViewedEventResource.class);

    private final QuestionEvaluationViewedEventService questionEvaluationViewedEventService;

    private final QuestionEvaluationViewedEventRepository questionEvaluationViewedEventRepository;

    public QuestionEvaluationViewedEventResource(
        QuestionEvaluationViewedEventService questionEvaluationViewedEventService,
        QuestionEvaluationViewedEventRepository questionEvaluationViewedEventRepository
    ) {
        this.questionEvaluationViewedEventService = questionEvaluationViewedEventService;
        this.questionEvaluationViewedEventRepository = questionEvaluationViewedEventRepository;
    }

    /**
     * {@code GET  /question-evaluation-viewed-events} : get all the questionEvaluationViewedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionEvaluationViewedEvents in body.
     */
    @GetMapping("/question-evaluation-viewed-events")
    public List<QuestionEvaluationViewedEventDTO> getAllQuestionEvaluationViewedEvents() {
        log.debug("REST request to get all QuestionEvaluationViewedEvents");
        return questionEvaluationViewedEventService.findAll();
    }

    /**
     * {@code GET  /question-evaluation-viewed-events/:id} : get the "id" questionEvaluationViewedEvent.
     *
     * @param id the id of the questionEvaluationViewedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionEvaluationViewedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-evaluation-viewed-events/{id}")
    public ResponseEntity<QuestionEvaluationViewedEventDTO> getQuestionEvaluationViewedEvent(@PathVariable Long id) {
        log.debug("REST request to get QuestionEvaluationViewedEvent : {}", id);
        Optional<QuestionEvaluationViewedEventDTO> questionEvaluationViewedEventDTO = questionEvaluationViewedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionEvaluationViewedEventDTO);
    }
}
