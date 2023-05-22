package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.QuestionRepository;
import org.contextmapper.generated.gateway.service.QuestionService;
import org.contextmapper.generated.gateway.service.dto.QuestionDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.Question}.
 */
@RestController
@RequestMapping("/api")
public class QuestionResource {

    private final Logger log = LoggerFactory.getLogger(QuestionResource.class);

    private final QuestionService questionService;

    private final QuestionRepository questionRepository;

    public QuestionResource(QuestionService questionService, QuestionRepository questionRepository) {
        this.questionService = questionService;
        this.questionRepository = questionRepository;
    }

    /**
     * {@code GET  /questions} : get all the questions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questions in body.
     */
    @GetMapping("/questions")
    public Mono<List<QuestionDTO>> getAllQuestions() {
        log.debug("REST request to get all Questions");
        return questionService.findAll().collectList();
    }

    /**
     * {@code GET  /questions} : get all the questions as a stream.
     * @return the {@link Flux} of questions.
     */
    @GetMapping(value = "/questions", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<QuestionDTO> getAllQuestionsAsStream() {
        log.debug("REST request to get all Questions as a stream");
        return questionService.findAll();
    }

    /**
     * {@code GET  /questions/:id} : get the "id" question.
     *
     * @param id the id of the questionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/questions/{id}")
    public Mono<ResponseEntity<QuestionDTO>> getQuestion(@PathVariable Long id) {
        log.debug("REST request to get Question : {}", id);
        Mono<QuestionDTO> questionDTO = questionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionDTO);
    }
}
