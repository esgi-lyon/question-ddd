package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.EvaluationTagRepository;
import org.contextmapper.generated.gateway.service.EvaluationTagService;
import org.contextmapper.generated.gateway.service.dto.EvaluationTagDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.EvaluationTag}.
 */
@RestController
@RequestMapping("/api")
public class EvaluationTagResource {

    private final Logger log = LoggerFactory.getLogger(EvaluationTagResource.class);

    private final EvaluationTagService evaluationTagService;

    private final EvaluationTagRepository evaluationTagRepository;

    public EvaluationTagResource(EvaluationTagService evaluationTagService, EvaluationTagRepository evaluationTagRepository) {
        this.evaluationTagService = evaluationTagService;
        this.evaluationTagRepository = evaluationTagRepository;
    }

    /**
     * {@code GET  /evaluation-tags} : get all the evaluationTags.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of evaluationTags in body.
     */
    @GetMapping("/evaluation-tags")
    public Mono<List<EvaluationTagDTO>> getAllEvaluationTags() {
        log.debug("REST request to get all EvaluationTags");
        return evaluationTagService.findAll().collectList();
    }

    /**
     * {@code GET  /evaluation-tags} : get all the evaluationTags as a stream.
     * @return the {@link Flux} of evaluationTags.
     */
    @GetMapping(value = "/evaluation-tags", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<EvaluationTagDTO> getAllEvaluationTagsAsStream() {
        log.debug("REST request to get all EvaluationTags as a stream");
        return evaluationTagService.findAll();
    }

    /**
     * {@code GET  /evaluation-tags/:id} : get the "id" evaluationTag.
     *
     * @param id the id of the evaluationTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluationTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evaluation-tags/{id}")
    public Mono<ResponseEntity<EvaluationTagDTO>> getEvaluationTag(@PathVariable Long id) {
        log.debug("REST request to get EvaluationTag : {}", id);
        Mono<EvaluationTagDTO> evaluationTagDTO = evaluationTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluationTagDTO);
    }
}
