package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.repository.QuestionResourceRepository;
import org.contextmapper.generated.questioncontext.service.QuestionResourceService;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.QuestionResource}.
 */
@RestController
@RequestMapping("/api")
public class QuestionResourceResource {

    private final Logger log = LoggerFactory.getLogger(QuestionResourceResource.class);

    private static final String ENTITY_NAME = "questionContextQuestionResource";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuestionResourceService questionResourceService;

    private final QuestionResourceRepository questionResourceRepository;

    public QuestionResourceResource(
        QuestionResourceService questionResourceService,
        QuestionResourceRepository questionResourceRepository
    ) {
        this.questionResourceService = questionResourceService;
        this.questionResourceRepository = questionResourceRepository;
    }

    /**
     * {@code POST  /question-resources} : Create a new questionResource.
     *
     * @param questionResourceDTO the questionResourceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new questionResourceDTO, or with status {@code 400 (Bad Request)} if the questionResource has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/question-resources")
    public ResponseEntity<QuestionResourceDTO> createQuestionResource(@RequestBody QuestionResourceDTO questionResourceDTO)
        throws URISyntaxException {
        log.debug("REST request to save QuestionResource : {}", questionResourceDTO);
        if (questionResourceDTO.getId() != null) {
            throw new BadRequestAlertException("A new questionResource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuestionResourceDTO result = questionResourceService.save(questionResourceDTO);
        return ResponseEntity
            .created(new URI("/api/question-resources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /question-resources/:id} : Updates an existing questionResource.
     *
     * @param id the id of the questionResourceDTO to save.
     * @param questionResourceDTO the questionResourceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionResourceDTO,
     * or with status {@code 400 (Bad Request)} if the questionResourceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the questionResourceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/question-resources/{id}")
    public ResponseEntity<QuestionResourceDTO> updateQuestionResource(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody QuestionResourceDTO questionResourceDTO
    ) throws URISyntaxException {
        log.debug("REST request to update QuestionResource : {}, {}", id, questionResourceDTO);
        if (questionResourceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionResourceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionResourceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        QuestionResourceDTO result = questionResourceService.update(questionResourceDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, questionResourceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /question-resources/:id} : Partial updates given fields of an existing questionResource, field will ignore if it is null
     *
     * @param id the id of the questionResourceDTO to save.
     * @param questionResourceDTO the questionResourceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionResourceDTO,
     * or with status {@code 400 (Bad Request)} if the questionResourceDTO is not valid,
     * or with status {@code 404 (Not Found)} if the questionResourceDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the questionResourceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/question-resources/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QuestionResourceDTO> partialUpdateQuestionResource(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody QuestionResourceDTO questionResourceDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update QuestionResource partially : {}, {}", id, questionResourceDTO);
        if (questionResourceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionResourceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionResourceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QuestionResourceDTO> result = questionResourceService.partialUpdate(questionResourceDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, questionResourceDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /question-resources} : get all the questionResources.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionResources in body.
     */
    @GetMapping("/question-resources")
    public List<QuestionResourceDTO> getAllQuestionResources() {
        log.debug("REST request to get all QuestionResources");
        return questionResourceService.findAll();
    }

    /**
     * {@code GET  /question-resources/:id} : get the "id" questionResource.
     *
     * @param id the id of the questionResourceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionResourceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-resources/{id}")
    public ResponseEntity<QuestionResourceDTO> getQuestionResource(@PathVariable Long id) {
        log.debug("REST request to get QuestionResource : {}", id);
        Optional<QuestionResourceDTO> questionResourceDTO = questionResourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionResourceDTO);
    }

    /**
     * {@code DELETE  /question-resources/:id} : delete the "id" questionResource.
     *
     * @param id the id of the questionResourceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/question-resources/{id}")
    public ResponseEntity<Void> deleteQuestionResource(@PathVariable Long id) {
        log.debug("REST request to delete QuestionResource : {}", id);
        questionResourceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
