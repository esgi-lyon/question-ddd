package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.EvaluationStatsRepository;
import org.contextmapper.generated.statcontext.service.EvaluationStatsService;
import org.contextmapper.generated.statcontext.service.dto.EvaluationStatsDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.EvaluationStats}.
 */
@RestController
@RequestMapping("/api")
public class EvaluationStatsResource {

    private final Logger log = LoggerFactory.getLogger(EvaluationStatsResource.class);

    private final EvaluationStatsService evaluationStatsService;

    private final EvaluationStatsRepository evaluationStatsRepository;

    public EvaluationStatsResource(EvaluationStatsService evaluationStatsService, EvaluationStatsRepository evaluationStatsRepository) {
        this.evaluationStatsService = evaluationStatsService;
        this.evaluationStatsRepository = evaluationStatsRepository;
    }

    /**
     * {@code GET  /evaluation-stats} : get all the evaluationStats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of evaluationStats in body.
     */
    @GetMapping("/evaluation-stats")
    public List<EvaluationStatsDTO> getAllEvaluationStats() {
        log.debug("REST request to get all EvaluationStats");
        return evaluationStatsService.findAll();
    }

    /**
     * {@code GET  /evaluation-stats/:id} : get the "id" evaluationStats.
     *
     * @param id the id of the evaluationStatsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluationStatsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evaluation-stats/{id}")
    public ResponseEntity<EvaluationStatsDTO> getEvaluationStats(@PathVariable Long id) {
        log.debug("REST request to get EvaluationStats : {}", id);
        Optional<EvaluationStatsDTO> evaluationStatsDTO = evaluationStatsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluationStatsDTO);
    }
}
