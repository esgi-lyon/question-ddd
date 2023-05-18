package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentTagIdRepository;
import org.contextmapper.generated.sendquestioncontext.service.QuestionSentTagIdService;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagIdDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagId}.
 */
@RestController
@RequestMapping("/api")
public class QuestionSentTagIdResource {

    private final Logger log = LoggerFactory.getLogger(QuestionSentTagIdResource.class);

    private final QuestionSentTagIdService questionSentTagIdService;

    private final QuestionSentTagIdRepository questionSentTagIdRepository;

    public QuestionSentTagIdResource(
        QuestionSentTagIdService questionSentTagIdService,
        QuestionSentTagIdRepository questionSentTagIdRepository
    ) {
        this.questionSentTagIdService = questionSentTagIdService;
        this.questionSentTagIdRepository = questionSentTagIdRepository;
    }

    /**
     * {@code GET  /question-sent-tag-ids} : get all the questionSentTagIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionSentTagIds in body.
     */
    @GetMapping("/question-sent-tag-ids")
    public List<QuestionSentTagIdDTO> getAllQuestionSentTagIds() {
        log.debug("REST request to get all QuestionSentTagIds");
        return questionSentTagIdService.findAll();
    }

    /**
     * {@code GET  /question-sent-tag-ids/:id} : get the "id" questionSentTagId.
     *
     * @param id the id of the questionSentTagIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionSentTagIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-sent-tag-ids/{id}")
    public ResponseEntity<QuestionSentTagIdDTO> getQuestionSentTagId(@PathVariable Long id) {
        log.debug("REST request to get QuestionSentTagId : {}", id);
        Optional<QuestionSentTagIdDTO> questionSentTagIdDTO = questionSentTagIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionSentTagIdDTO);
    }
}
