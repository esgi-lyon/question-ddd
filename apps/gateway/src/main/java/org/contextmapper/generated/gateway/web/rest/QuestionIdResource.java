package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.QuestionIdRepository;
import org.contextmapper.generated.gateway.service.QuestionIdService;
import org.contextmapper.generated.gateway.service.dto.QuestionIdDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.QuestionId}.
 */
@RestController
@RequestMapping("/api")
public class QuestionIdResource {

    private final Logger log = LoggerFactory.getLogger(QuestionIdResource.class);

    private final QuestionIdService questionIdService;

    private final QuestionIdRepository questionIdRepository;

    public QuestionIdResource(QuestionIdService questionIdService, QuestionIdRepository questionIdRepository) {
        this.questionIdService = questionIdService;
        this.questionIdRepository = questionIdRepository;
    }

    /**
     * {@code GET  /question-ids} : get all the questionIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionIds in body.
     */
    @GetMapping("/question-ids")
    public Mono<List<QuestionIdDTO>> getAllQuestionIds() {
        log.debug("REST request to get all QuestionIds");
        return questionIdService.findAll().collectList();
    }

    /**
     * {@code GET  /question-ids} : get all the questionIds as a stream.
     * @return the {@link Flux} of questionIds.
     */
    @GetMapping(value = "/question-ids", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<QuestionIdDTO> getAllQuestionIdsAsStream() {
        log.debug("REST request to get all QuestionIds as a stream");
        return questionIdService.findAll();
    }

    /**
     * {@code GET  /question-ids/:id} : get the "id" questionId.
     *
     * @param id the id of the questionIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-ids/{id}")
    public Mono<ResponseEntity<QuestionIdDTO>> getQuestionId(@PathVariable Long id) {
        log.debug("REST request to get QuestionId : {}", id);
        Mono<QuestionIdDTO> questionIdDTO = questionIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionIdDTO);
    }
}
