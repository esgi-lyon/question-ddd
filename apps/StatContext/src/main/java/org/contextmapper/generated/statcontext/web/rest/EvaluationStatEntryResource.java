package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.EvaluationStatEntryRepository;
import org.contextmapper.generated.statcontext.service.EvaluationStatEntryService;
import org.contextmapper.generated.statcontext.service.dto.EvaluationStatEntryDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.EvaluationStatEntry}.
 */
@RestController
@RequestMapping("/api")
public class EvaluationStatEntryResource {

    private final Logger log = LoggerFactory.getLogger(EvaluationStatEntryResource.class);

    private final EvaluationStatEntryService evaluationStatEntryService;

    private final EvaluationStatEntryRepository evaluationStatEntryRepository;

    public EvaluationStatEntryResource(
        EvaluationStatEntryService evaluationStatEntryService,
        EvaluationStatEntryRepository evaluationStatEntryRepository
    ) {
        this.evaluationStatEntryService = evaluationStatEntryService;
        this.evaluationStatEntryRepository = evaluationStatEntryRepository;
    }

    /**
     * {@code GET  /evaluation-stat-entries} : get all the evaluationStatEntries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of evaluationStatEntries in body.
     */
    @GetMapping("/evaluation-stat-entries")
    public List<EvaluationStatEntryDTO> getAllEvaluationStatEntries() {
        log.debug("REST request to get all EvaluationStatEntries");
        return evaluationStatEntryService.findAll();
    }

    /**
     * {@code GET  /evaluation-stat-entries/:id} : get the "id" evaluationStatEntry.
     *
     * @param id the id of the evaluationStatEntryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluationStatEntryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evaluation-stat-entries/{id}")
    public ResponseEntity<EvaluationStatEntryDTO> getEvaluationStatEntry(@PathVariable Long id) {
        log.debug("REST request to get EvaluationStatEntry : {}", id);
        Optional<EvaluationStatEntryDTO> evaluationStatEntryDTO = evaluationStatEntryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluationStatEntryDTO);
    }
}
