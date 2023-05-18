package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.QuestionIdRepository;
import org.contextmapper.generated.answercontext.service.QuestionIdService;
import org.contextmapper.generated.answercontext.service.dto.QuestionIdDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.QuestionId}.
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
    public List<QuestionIdDTO> getAllQuestionIds() {
        log.debug("REST request to get all QuestionIds");
        return questionIdService.findAll();
    }

    /**
     * {@code GET  /question-ids/:id} : get the "id" questionId.
     *
     * @param id the id of the questionIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-ids/{id}")
    public ResponseEntity<QuestionIdDTO> getQuestionId(@PathVariable Long id) {
        log.debug("REST request to get QuestionId : {}", id);
        Optional<QuestionIdDTO> questionIdDTO = questionIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionIdDTO);
    }
}
