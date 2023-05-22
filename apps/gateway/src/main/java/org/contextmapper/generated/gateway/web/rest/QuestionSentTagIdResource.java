package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.QuestionSentTagIdRepository;
import org.contextmapper.generated.gateway.service.QuestionSentTagIdService;
import org.contextmapper.generated.gateway.service.dto.QuestionSentTagIdDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.QuestionSentTagId}.
 */
@RestController
@RequestMapping("/api")
public class QuestionSentTagIdResource {

    private final Logger log = LoggerFactory.getLogger(QuestionSentTagIdResource.class);

    private final QuestionSentTagIdService questionSentTagIdService;

    private final QuestionSentTagIdRepository questionSentTagIdRepository;

    public QuestionSentTagIdResource(
        QuestionSentTagIdService questionSentTagIdService,
        QuestionSentTagIdRepository questionSentTagIdRepository
    ) {
        this.questionSentTagIdService = questionSentTagIdService;
        this.questionSentTagIdRepository = questionSentTagIdRepository;
    }

    /**
     * {@code GET  /question-sent-tag-ids} : get all the questionSentTagIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionSentTagIds in body.
     */
    @GetMapping("/question-sent-tag-ids")
    public Mono<List<QuestionSentTagIdDTO>> getAllQuestionSentTagIds() {
        log.debug("REST request to get all QuestionSentTagIds");
        return questionSentTagIdService.findAll().collectList();
    }

    /**
     * {@code GET  /question-sent-tag-ids} : get all the questionSentTagIds as a stream.
     * @return the {@link Flux} of questionSentTagIds.
     */
    @GetMapping(value = "/question-sent-tag-ids", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<QuestionSentTagIdDTO> getAllQuestionSentTagIdsAsStream() {
        log.debug("REST request to get all QuestionSentTagIds as a stream");
        return questionSentTagIdService.findAll();
    }

    /**
     * {@code GET  /question-sent-tag-ids/:id} : get the "id" questionSentTagId.
     *
     * @param id the id of the questionSentTagIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionSentTagIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-sent-tag-ids/{id}")
    public Mono<ResponseEntity<QuestionSentTagIdDTO>> getQuestionSentTagId(@PathVariable Long id) {
        log.debug("REST request to get QuestionSentTagId : {}", id);
        Mono<QuestionSentTagIdDTO> questionSentTagIdDTO = questionSentTagIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionSentTagIdDTO);
    }
}
