package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentQuestionResourceTagIdRepository;
import org.contextmapper.generated.sendquestioncontext.service.QuestionSentQuestionResourceTagIdService;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentQuestionResourceTagIdDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.QuestionSentQuestionResourceTagId}.
 */
@RestController
@RequestMapping("/api")
public class QuestionSentQuestionResourceTagIdResource {

    private final Logger log = LoggerFactory.getLogger(QuestionSentQuestionResourceTagIdResource.class);

    private final QuestionSentQuestionResourceTagIdService questionSentQuestionResourceTagIdService;

    private final QuestionSentQuestionResourceTagIdRepository questionSentQuestionResourceTagIdRepository;

    public QuestionSentQuestionResourceTagIdResource(
        QuestionSentQuestionResourceTagIdService questionSentQuestionResourceTagIdService,
        QuestionSentQuestionResourceTagIdRepository questionSentQuestionResourceTagIdRepository
    ) {
        this.questionSentQuestionResourceTagIdService = questionSentQuestionResourceTagIdService;
        this.questionSentQuestionResourceTagIdRepository = questionSentQuestionResourceTagIdRepository;
    }

    /**
     * {@code GET  /question-sent-question-resource-tag-ids} : get all the questionSentQuestionResourceTagIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionSentQuestionResourceTagIds in body.
     */
    @GetMapping("/question-sent-question-resource-tag-ids")
    public List<QuestionSentQuestionResourceTagIdDTO> getAllQuestionSentQuestionResourceTagIds() {
        log.debug("REST request to get all QuestionSentQuestionResourceTagIds");
        return questionSentQuestionResourceTagIdService.findAll();
    }

    /**
     * {@code GET  /question-sent-question-resource-tag-ids/:id} : get the "id" questionSentQuestionResourceTagId.
     *
     * @param id the id of the questionSentQuestionResourceTagIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionSentQuestionResourceTagIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-sent-question-resource-tag-ids/{id}")
    public ResponseEntity<QuestionSentQuestionResourceTagIdDTO> getQuestionSentQuestionResourceTagId(@PathVariable Long id) {
        log.debug("REST request to get QuestionSentQuestionResourceTagId : {}", id);
        Optional<QuestionSentQuestionResourceTagIdDTO> questionSentQuestionResourceTagIdDTO = questionSentQuestionResourceTagIdService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(questionSentQuestionResourceTagIdDTO);
    }
}
