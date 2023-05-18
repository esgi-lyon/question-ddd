package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentTagInfosRepository;
import org.contextmapper.generated.sendquestioncontext.service.QuestionSentTagInfosService;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagInfosDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagInfos}.
 */
@RestController
@RequestMapping("/api")
public class QuestionSentTagInfosResource {

    private final Logger log = LoggerFactory.getLogger(QuestionSentTagInfosResource.class);

    private final QuestionSentTagInfosService questionSentTagInfosService;

    private final QuestionSentTagInfosRepository questionSentTagInfosRepository;

    public QuestionSentTagInfosResource(
        QuestionSentTagInfosService questionSentTagInfosService,
        QuestionSentTagInfosRepository questionSentTagInfosRepository
    ) {
        this.questionSentTagInfosService = questionSentTagInfosService;
        this.questionSentTagInfosRepository = questionSentTagInfosRepository;
    }

    /**
     * {@code GET  /question-sent-tag-infos} : get all the questionSentTagInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionSentTagInfos in body.
     */
    @GetMapping("/question-sent-tag-infos")
    public List<QuestionSentTagInfosDTO> getAllQuestionSentTagInfos() {
        log.debug("REST request to get all QuestionSentTagInfos");
        return questionSentTagInfosService.findAll();
    }

    /**
     * {@code GET  /question-sent-tag-infos/:id} : get the "id" questionSentTagInfos.
     *
     * @param id the id of the questionSentTagInfosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionSentTagInfosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-sent-tag-infos/{id}")
    public ResponseEntity<QuestionSentTagInfosDTO> getQuestionSentTagInfos(@PathVariable Long id) {
        log.debug("REST request to get QuestionSentTagInfos : {}", id);
        Optional<QuestionSentTagInfosDTO> questionSentTagInfosDTO = questionSentTagInfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionSentTagInfosDTO);
    }
}
