package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.EvaluationQuestionRepository;
import org.contextmapper.generated.gateway.service.EvaluationQuestionService;
import org.contextmapper.generated.gateway.service.dto.EvaluationQuestionDTO;
import org.contextmapper.generated.gateway.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.EvaluationQuestion}.
 */
@RestController
@RequestMapping("/api")
public class EvaluationQuestionResource {

    private final Logger log = LoggerFactory.getLogger(EvaluationQuestionResource.class);

    private final EvaluationQuestionService evaluationQuestionService;

    private final EvaluationQuestionRepository evaluationQuestionRepository;

    public EvaluationQuestionResource(
        EvaluationQuestionService evaluationQuestionService,
        EvaluationQuestionRepository evaluationQuestionRepository
    ) {
        this.evaluationQuestionService = evaluationQuestionService;
        this.evaluationQuestionRepository = evaluationQuestionRepository;
    }

    /**
     * {@code GET  /evaluation-questions} : get all the evaluationQuestions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of evaluationQuestions in body.
     */
    @GetMapping("/evaluation-questions")
    public Mono<List<EvaluationQuestionDTO>> getAllEvaluationQuestions() {
        log.debug("REST request to get all EvaluationQuestions");
        return evaluationQuestionService.findAll().collectList();
    }

    /**
     * {@code GET  /evaluation-questions} : get all the evaluationQuestions as a stream.
     * @return the {@link Flux} of evaluationQuestions.
     */
    @GetMapping(value = "/evaluation-questions", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<EvaluationQuestionDTO> getAllEvaluationQuestionsAsStream() {
        log.debug("REST request to get all EvaluationQuestions as a stream");
        return evaluationQuestionService.findAll();
    }

    /**
     * {@code GET  /evaluation-questions/:id} : get the "id" evaluationQuestion.
     *
     * @param id the id of the evaluationQuestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluationQuestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evaluation-questions/{id}")
    public Mono<ResponseEntity<EvaluationQuestionDTO>> getEvaluationQuestion(@PathVariable Long id) {
        log.debug("REST request to get EvaluationQuestion : {}", id);
        Mono<EvaluationQuestionDTO> evaluationQuestionDTO = evaluationQuestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluationQuestionDTO);
    }
}
