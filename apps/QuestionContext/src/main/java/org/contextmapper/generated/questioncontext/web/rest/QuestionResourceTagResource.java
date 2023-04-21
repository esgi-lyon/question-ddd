package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.repository.QuestionResourceTagRepository;
import org.contextmapper.generated.questioncontext.service.QuestionResourceTagService;
import org.contextmapper.generated.questioncontext.service.dto.QuestionResourceTagDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.QuestionResourceTag}.
 */
@RestController
@RequestMapping("/api")
public class QuestionResourceTagResource {

    private final Logger log = LoggerFactory.getLogger(QuestionResourceTagResource.class);

    private static final String ENTITY_NAME = "questionContextQuestionResourceTag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuestionResourceTagService questionResourceTagService;

    private final QuestionResourceTagRepository questionResourceTagRepository;

    public QuestionResourceTagResource(
        QuestionResourceTagService questionResourceTagService,
        QuestionResourceTagRepository questionResourceTagRepository
    ) {
        this.questionResourceTagService = questionResourceTagService;
        this.questionResourceTagRepository = questionResourceTagRepository;
    }

    /**
     * {@code POST  /question-resource-tags} : Create a new questionResourceTag.
     *
     * @param questionResourceTagDTO the questionResourceTagDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new questionResourceTagDTO, or with status {@code 400 (Bad Request)} if the questionResourceTag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/question-resource-tags")
    public ResponseEntity<QuestionResourceTagDTO> createQuestionResourceTag(@RequestBody QuestionResourceTagDTO questionResourceTagDTO)
        throws URISyntaxException {
        log.debug("REST request to save QuestionResourceTag : {}", questionResourceTagDTO);
        if (questionResourceTagDTO.getId() != null) {
            throw new BadRequestAlertException("A new questionResourceTag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuestionResourceTagDTO result = questionResourceTagService.save(questionResourceTagDTO);
        return ResponseEntity
            .created(new URI("/api/question-resource-tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /question-resource-tags/:id} : Updates an existing questionResourceTag.
     *
     * @param id the id of the questionResourceTagDTO to save.
     * @param questionResourceTagDTO the questionResourceTagDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionResourceTagDTO,
     * or with status {@code 400 (Bad Request)} if the questionResourceTagDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the questionResourceTagDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/question-resource-tags/{id}")
    public ResponseEntity<QuestionResourceTagDTO> updateQuestionResourceTag(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody QuestionResourceTagDTO questionResourceTagDTO
    ) throws URISyntaxException {
        log.debug("REST request to update QuestionResourceTag : {}, {}", id, questionResourceTagDTO);
        if (questionResourceTagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionResourceTagDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionResourceTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        QuestionResourceTagDTO result = questionResourceTagService.update(questionResourceTagDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, questionResourceTagDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /question-resource-tags/:id} : Partial updates given fields of an existing questionResourceTag, field will ignore if it is null
     *
     * @param id the id of the questionResourceTagDTO to save.
     * @param questionResourceTagDTO the questionResourceTagDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionResourceTagDTO,
     * or with status {@code 400 (Bad Request)} if the questionResourceTagDTO is not valid,
     * or with status {@code 404 (Not Found)} if the questionResourceTagDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the questionResourceTagDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/question-resource-tags/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QuestionResourceTagDTO> partialUpdateQuestionResourceTag(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody QuestionResourceTagDTO questionResourceTagDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update QuestionResourceTag partially : {}, {}", id, questionResourceTagDTO);
        if (questionResourceTagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, questionResourceTagDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!questionResourceTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QuestionResourceTagDTO> result = questionResourceTagService.partialUpdate(questionResourceTagDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, questionResourceTagDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /question-resource-tags} : get all the questionResourceTags.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionResourceTags in body.
     */
    @GetMapping("/question-resource-tags")
    public List<QuestionResourceTagDTO> getAllQuestionResourceTags() {
        log.debug("REST request to get all QuestionResourceTags");
        return questionResourceTagService.findAll();
    }

    /**
     * {@code GET  /question-resource-tags/:id} : get the "id" questionResourceTag.
     *
     * @param id the id of the questionResourceTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionResourceTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-resource-tags/{id}")
    public ResponseEntity<QuestionResourceTagDTO> getQuestionResourceTag(@PathVariable Long id) {
        log.debug("REST request to get QuestionResourceTag : {}", id);
        Optional<QuestionResourceTagDTO> questionResourceTagDTO = questionResourceTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionResourceTagDTO);
    }

    /**
     * {@code DELETE  /question-resource-tags/:id} : delete the "id" questionResourceTag.
     *
     * @param id the id of the questionResourceTagDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/question-resource-tags/{id}")
    public ResponseEntity<Void> deleteQuestionResourceTag(@PathVariable Long id) {
        log.debug("REST request to delete QuestionResourceTag : {}", id);
        questionResourceTagService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
