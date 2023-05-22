package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.EvaluatedAnswerRepository;
import org.contextmapper.generated.gateway.service.EvaluatedAnswerService;
import org.contextmapper.generated.gateway.service.dto.EvaluatedAnswerDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.EvaluatedAnswer}.
 */
@RestController
@RequestMapping("/api")
public class EvaluatedAnswerResource {

    private final Logger log = LoggerFactory.getLogger(EvaluatedAnswerResource.class);

    private final EvaluatedAnswerService evaluatedAnswerService;

    private final EvaluatedAnswerRepository evaluatedAnswerRepository;

    public EvaluatedAnswerResource(EvaluatedAnswerService evaluatedAnswerService, EvaluatedAnswerRepository evaluatedAnswerRepository) {
        this.evaluatedAnswerService = evaluatedAnswerService;
        this.evaluatedAnswerRepository = evaluatedAnswerRepository;
    }

    /**
     * {@code GET  /evaluated-answers} : get all the evaluatedAnswers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of evaluatedAnswers in body.
     */
    @GetMapping("/evaluated-answers")
    public Mono<List<EvaluatedAnswerDTO>> getAllEvaluatedAnswers() {
        log.debug("REST request to get all EvaluatedAnswers");
        return evaluatedAnswerService.findAll().collectList();
    }

    /**
     * {@code GET  /evaluated-answers} : get all the evaluatedAnswers as a stream.
     * @return the {@link Flux} of evaluatedAnswers.
     */
    @GetMapping(value = "/evaluated-answers", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<EvaluatedAnswerDTO> getAllEvaluatedAnswersAsStream() {
        log.debug("REST request to get all EvaluatedAnswers as a stream");
        return evaluatedAnswerService.findAll();
    }

    /**
     * {@code GET  /evaluated-answers/:id} : get the "id" evaluatedAnswer.
     *
     * @param id the id of the evaluatedAnswerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluatedAnswerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evaluated-answers/{id}")
    public Mono<ResponseEntity<EvaluatedAnswerDTO>> getEvaluatedAnswer(@PathVariable Long id) {
        log.debug("REST request to get EvaluatedAnswer : {}", id);
        Mono<EvaluatedAnswerDTO> evaluatedAnswerDTO = evaluatedAnswerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluatedAnswerDTO);
    }
}
