package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.EvaluationTagRepository;
import org.contextmapper.generated.evaluationcontext.service.EvaluationTagService;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationTagDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.EvaluationTag}.
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
    public List<EvaluationTagDTO> getAllEvaluationTags() {
        log.debug("REST request to get all EvaluationTags");
        return evaluationTagService.findAll();
    }

    /**
     * {@code GET  /evaluation-tags/:id} : get the "id" evaluationTag.
     *
     * @param id the id of the evaluationTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluationTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evaluation-tags/{id}")
    public ResponseEntity<EvaluationTagDTO> getEvaluationTag(@PathVariable Long id) {
        log.debug("REST request to get EvaluationTag : {}", id);
        Optional<EvaluationTagDTO> evaluationTagDTO = evaluationTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluationTagDTO);
    }
}
