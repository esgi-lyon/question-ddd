package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.EvaluatedAnswerRepository;
import org.contextmapper.generated.evaluationcontext.service.EvaluatedAnswerService;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluatedAnswerDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.EvaluatedAnswer}.
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
    public List<EvaluatedAnswerDTO> getAllEvaluatedAnswers() {
        log.debug("REST request to get all EvaluatedAnswers");
        return evaluatedAnswerService.findAll();
    }

    /**
     * {@code GET  /evaluated-answers/:id} : get the "id" evaluatedAnswer.
     *
     * @param id the id of the evaluatedAnswerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluatedAnswerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evaluated-answers/{id}")
    public ResponseEntity<EvaluatedAnswerDTO> getEvaluatedAnswer(@PathVariable Long id) {
        log.debug("REST request to get EvaluatedAnswer : {}", id);
        Optional<EvaluatedAnswerDTO> evaluatedAnswerDTO = evaluatedAnswerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluatedAnswerDTO);
    }
}
