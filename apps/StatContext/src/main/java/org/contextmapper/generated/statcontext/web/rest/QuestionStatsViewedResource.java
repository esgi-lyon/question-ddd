package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.QuestionStatsViewedRepository;
import org.contextmapper.generated.statcontext.service.QuestionStatsViewedService;
import org.contextmapper.generated.statcontext.service.dto.QuestionStatsViewedDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.QuestionStatsViewed}.
 */
@RestController
@RequestMapping("/api")
public class QuestionStatsViewedResource {

    private final Logger log = LoggerFactory.getLogger(QuestionStatsViewedResource.class);

    private static final String ENTITY_NAME = "statContextQuestionStatsViewed";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuestionStatsViewedService questionStatsViewedService;

    private final QuestionStatsViewedRepository questionStatsViewedRepository;

    public QuestionStatsViewedResource(
        QuestionStatsViewedService questionStatsViewedService,
        QuestionStatsViewedRepository questionStatsViewedRepository
    ) {
        this.questionStatsViewedService = questionStatsViewedService;
        this.questionStatsViewedRepository = questionStatsViewedRepository;
    }

    /**
     * {@code POST  /question-stats-vieweds} : Create a new questionStatsViewed.
     *
     * @param questionStatsViewedDTO the questionStatsViewedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new questionStatsViewedDTO, or with status {@code 400 (Bad Request)} if the questionStatsViewed has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/question-stats-vieweds")
    public ResponseEntity<QuestionStatsViewedDTO> createQuestionStatsViewed(@RequestBody QuestionStatsViewedDTO questionStatsViewedDTO)
        throws URISyntaxException {
        log.debug("REST request to save QuestionStatsViewed : {}", questionStatsViewedDTO);
        if (questionStatsViewedDTO.getId() != null) {
            throw new BadRequestAlertException("A new questionStatsViewed cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuestionStatsViewedDTO result = questionStatsViewedService.save(questionStatsViewedDTO);
        return ResponseEntity
            .created(new URI("/api/question-stats-vieweds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /question-stats-vieweds/:id} : Updates an existing questionStatsViewed.
     *
     * @param id the id of the questionStatsViewedDTO to save.
     * @param questionStatsViewedDTO the questionStatsViewedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionStatsViewedDTO,
     * or with status {@code 400 (Bad Request)} if the questionStatsViewedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the questionStatsViewedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/question-stats-vieweds/{id}")
    public ResponseEntity<QuestionStatsViewedDTO> updateQuestionStatsViewed(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody QuestionStatsViewedDTO questionStatsViewedDTO
    ) throws URISyntaxException {
        log.debug("REST request to update QuestionStatsViewed : {}, {}", id, questionStatsViewedDTO);
        if (questionStatsViewedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionStatsViewedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionStatsViewedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        QuestionStatsViewedDTO result = questionStatsViewedService.update(questionStatsViewedDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, questionStatsViewedDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /question-stats-vieweds/:id} : Partial updates given fields of an existing questionStatsViewed, field will ignore if it is null
     *
     * @param id the id of the questionStatsViewedDTO to save.
     * @param questionStatsViewedDTO the questionStatsViewedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionStatsViewedDTO,
     * or with status {@code 400 (Bad Request)} if the questionStatsViewedDTO is not valid,
     * or with status {@code 404 (Not Found)} if the questionStatsViewedDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the questionStatsViewedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/question-stats-vieweds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QuestionStatsViewedDTO> partialUpdateQuestionStatsViewed(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody QuestionStatsViewedDTO questionStatsViewedDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update QuestionStatsViewed partially : {}, {}", id, questionStatsViewedDTO);
        if (questionStatsViewedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionStatsViewedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionStatsViewedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QuestionStatsViewedDTO> result = questionStatsViewedService.partialUpdate(questionStatsViewedDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, questionStatsViewedDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /question-stats-vieweds} : get all the questionStatsVieweds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionStatsVieweds in body.
     */
    @GetMapping("/question-stats-vieweds")
    public List<QuestionStatsViewedDTO> getAllQuestionStatsVieweds() {
        log.debug("REST request to get all QuestionStatsVieweds");
        return questionStatsViewedService.findAll();
    }

    /**
     * {@code GET  /question-stats-vieweds/:id} : get the "id" questionStatsViewed.
     *
     * @param id the id of the questionStatsViewedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionStatsViewedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-stats-vieweds/{id}")
    public ResponseEntity<QuestionStatsViewedDTO> getQuestionStatsViewed(@PathVariable Long id) {
        log.debug("REST request to get QuestionStatsViewed : {}", id);
        Optional<QuestionStatsViewedDTO> questionStatsViewedDTO = questionStatsViewedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionStatsViewedDTO);
    }

    /**
     * {@code DELETE  /question-stats-vieweds/:id} : delete the "id" questionStatsViewed.
     *
     * @param id the id of the questionStatsViewedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/question-stats-vieweds/{id}")
    public ResponseEntity<Void> deleteQuestionStatsViewed(@PathVariable Long id) {
        log.debug("REST request to delete QuestionStatsViewed : {}", id);
        questionStatsViewedService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
