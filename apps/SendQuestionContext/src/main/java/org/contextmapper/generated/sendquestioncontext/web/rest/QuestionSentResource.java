package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentRepository;
import org.contextmapper.generated.sendquestioncontext.service.QuestionSentService;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.QuestionSent}.
 */
@RestController
@RequestMapping("/api")
public class QuestionSentResource {

    private final Logger log = LoggerFactory.getLogger(QuestionSentResource.class);

    private static final String ENTITY_NAME = "sendQuestionContextQuestionSent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuestionSentService questionSentService;

    private final QuestionSentRepository questionSentRepository;

    public QuestionSentResource(QuestionSentService questionSentService, QuestionSentRepository questionSentRepository) {
        this.questionSentService = questionSentService;
        this.questionSentRepository = questionSentRepository;
    }

    /**
     * {@code POST  /question-sents} : Create a new questionSent.
     *
     * @param questionSentDTO the questionSentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new questionSentDTO, or with status {@code 400 (Bad Request)} if the questionSent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/question-sents")
    public ResponseEntity<QuestionSentDTO> createQuestionSent(@RequestBody QuestionSentDTO questionSentDTO) throws URISyntaxException {
        log.debug("REST request to save QuestionSent : {}", questionSentDTO);
        if (questionSentDTO.getId() != null) {
            throw new BadRequestAlertException("A new questionSent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuestionSentDTO result = questionSentService.save(questionSentDTO);
        return ResponseEntity
            .created(new URI("/api/question-sents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /question-sents/:id} : Updates an existing questionSent.
     *
     * @param id the id of the questionSentDTO to save.
     * @param questionSentDTO the questionSentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionSentDTO,
     * or with status {@code 400 (Bad Request)} if the questionSentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the questionSentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/question-sents/{id}")
    public ResponseEntity<QuestionSentDTO> updateQuestionSent(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody QuestionSentDTO questionSentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update QuestionSent : {}, {}", id, questionSentDTO);
        if (questionSentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionSentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionSentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        QuestionSentDTO result = questionSentService.update(questionSentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, questionSentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /question-sents/:id} : Partial updates given fields of an existing questionSent, field will ignore if it is null
     *
     * @param id the id of the questionSentDTO to save.
     * @param questionSentDTO the questionSentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionSentDTO,
     * or with status {@code 400 (Bad Request)} if the questionSentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the questionSentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the questionSentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/question-sents/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QuestionSentDTO> partialUpdateQuestionSent(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody QuestionSentDTO questionSentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update QuestionSent partially : {}, {}", id, questionSentDTO);
        if (questionSentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionSentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionSentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QuestionSentDTO> result = questionSentService.partialUpdate(questionSentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, questionSentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /question-sents} : get all the questionSents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionSents in body.
     */
    @GetMapping("/question-sents")
    public List<QuestionSentDTO> getAllQuestionSents() {
        log.debug("REST request to get all QuestionSents");
        return questionSentService.findAll();
    }

    /**
     * {@code GET  /question-sents/:id} : get the "id" questionSent.
     *
     * @param id the id of the questionSentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionSentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-sents/{id}")
    public ResponseEntity<QuestionSentDTO> getQuestionSent(@PathVariable Long id) {
        log.debug("REST request to get QuestionSent : {}", id);
        Optional<QuestionSentDTO> questionSentDTO = questionSentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionSentDTO);
    }

    /**
     * {@code DELETE  /question-sents/:id} : delete the "id" questionSent.
     *
     * @param id the id of the questionSentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/question-sents/{id}")
    public ResponseEntity<Void> deleteQuestionSent(@PathVariable Long id) {
        log.debug("REST request to delete QuestionSent : {}", id);
        questionSentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
