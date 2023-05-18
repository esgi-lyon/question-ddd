package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.repository.QuestionResourceTagInfosRepository;
import org.contextmapper.generated.questioncontext.service.QuestionResourceTagInfosService;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagInfosDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.QuestionResourceTagInfos}.
 */
@RestController
@RequestMapping("/api")
public class QuestionResourceTagInfosResource {

    private final Logger log = LoggerFactory.getLogger(QuestionResourceTagInfosResource.class);

    private final QuestionResourceTagInfosService questionResourceTagInfosService;

    private final QuestionResourceTagInfosRepository questionResourceTagInfosRepository;

    public QuestionResourceTagInfosResource(
        QuestionResourceTagInfosService questionResourceTagInfosService,
        QuestionResourceTagInfosRepository questionResourceTagInfosRepository
    ) {
        this.questionResourceTagInfosService = questionResourceTagInfosService;
        this.questionResourceTagInfosRepository = questionResourceTagInfosRepository;
    }

    /**
     * {@code GET  /question-resource-tag-infos} : get all the questionResourceTagInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionResourceTagInfos in body.
     */
    @GetMapping("/question-resource-tag-infos")
    public List<QuestionResourceTagInfosDTO> getAllQuestionResourceTagInfos() {
        log.debug("REST request to get all QuestionResourceTagInfos");
        return questionResourceTagInfosService.findAll();
    }

    /**
     * {@code GET  /question-resource-tag-infos/:id} : get the "id" questionResourceTagInfos.
     *
     * @param id the id of the questionResourceTagInfosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionResourceTagInfosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-resource-tag-infos/{id}")
    public ResponseEntity<QuestionResourceTagInfosDTO> getQuestionResourceTagInfos(@PathVariable Long id) {
        log.debug("REST request to get QuestionResourceTagInfos : {}", id);
        Optional<QuestionResourceTagInfosDTO> questionResourceTagInfosDTO = questionResourceTagInfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionResourceTagInfosDTO);
    }
}
