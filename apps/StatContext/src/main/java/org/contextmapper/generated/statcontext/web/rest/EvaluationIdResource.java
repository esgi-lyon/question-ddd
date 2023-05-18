package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.EvaluationIdRepository;
import org.contextmapper.generated.statcontext.service.EvaluationIdService;
import org.contextmapper.generated.statcontext.service.dto.EvaluationIdDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.EvaluationId}.
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
    public List<EvaluationIdDTO> getAllEvaluationIds() {
        log.debug("REST request to get all EvaluationIds");
        return evaluationIdService.findAll();
    }

    /**
     * {@code GET  /evaluation-ids/:id} : get the "id" evaluationId.
     *
     * @param id the id of the evaluationIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluationIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evaluation-ids/{id}")
    public ResponseEntity<EvaluationIdDTO> getEvaluationId(@PathVariable Long id) {
        log.debug("REST request to get EvaluationId : {}", id);
        Optional<EvaluationIdDTO> evaluationIdDTO = evaluationIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluationIdDTO);
    }
}
