package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.EvaluationIdRepository;
import org.contextmapper.generated.gateway.service.EvaluationIdService;
import org.contextmapper.generated.gateway.service.dto.EvaluationIdDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.EvaluationId}.
 */
@RestController
@RequestMapping("/api")
public class EvaluationIdResource {

    private final Logger log = LoggerFactory.getLogger(EvaluationIdResource.class);

    private final EvaluationIdService evaluationIdService;

    private final EvaluationIdRepository evaluationIdRepository;

    public EvaluationIdResource(EvaluationIdService evaluationIdService, EvaluationIdRepository evaluationIdRepository) {
        this.evaluationIdService = evaluationIdService;
        this.evaluationIdRepository = evaluationIdRepository;
    }

    /**
     * {@code GET  /evaluation-ids} : get all the evaluationIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of evaluationIds in body.
     */
    @GetMapping("/evaluation-ids")
    public Mono<List<EvaluationIdDTO>> getAllEvaluationIds() {
        log.debug("REST request to get all EvaluationIds");
        return evaluationIdService.findAll().collectList();
    }

    /**
     * {@code GET  /evaluation-ids} : get all the evaluationIds as a stream.
     * @return the {@link Flux} of evaluationIds.
     */
    @GetMapping(value = "/evaluation-ids", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<EvaluationIdDTO> getAllEvaluationIdsAsStream() {
        log.debug("REST request to get all EvaluationIds as a stream");
        return evaluationIdService.findAll();
    }

    /**
     * {@code GET  /evaluation-ids/:id} : get the "id" evaluationId.
     *
     * @param id the id of the evaluationIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluationIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evaluation-ids/{id}")
    public Mono<ResponseEntity<EvaluationIdDTO>> getEvaluationId(@PathVariable Long id) {
        log.debug("REST request to get EvaluationId : {}", id);
        Mono<EvaluationIdDTO> evaluationIdDTO = evaluationIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluationIdDTO);
    }
}
