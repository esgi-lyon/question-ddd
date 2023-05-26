package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.QuestionSentIdRepository;
import org.contextmapper.generated.answercontext.service.QuestionSentIdService;
import org.contextmapper.generated.answercontext.service.dto.QuestionSentIdDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.QuestionSentId}.
 */
@RestController
@RequestMapping("/api")
public class QuestionSentIdResource {

    private final Logger log = LoggerFactory.getLogger(QuestionSentIdResource.class);

    private final QuestionSentIdService questionSentIdService;

    private final QuestionSentIdRepository questionSentIdRepository;

    public QuestionSentIdResource(QuestionSentIdService questionSentIdService, QuestionSentIdRepository questionSentIdRepository) {
        this.questionSentIdService = questionSentIdService;
        this.questionSentIdRepository = questionSentIdRepository;
    }

    /**
     * {@code GET  /question-sent-ids} : get all the questionSentIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionSentIds in body.
     */
    @GetMapping("/question-sent-ids")
    public List<QuestionSentIdDTO> getAllQuestionSentIds() {
        log.debug("REST request to get all QuestionSentIds");
        return questionSentIdService.findAll();
    }

    /**
     * {@code GET  /question-sent-ids/:id} : get the "id" questionSentId.
     *
     * @param id the id of the questionSentIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionSentIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-sent-ids/{id}")
    public ResponseEntity<QuestionSentIdDTO> getQuestionSentId(@PathVariable Long id) {
        log.debug("REST request to get QuestionSentId : {}", id);
        Optional<QuestionSentIdDTO> questionSentIdDTO = questionSentIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionSentIdDTO);
    }
}
