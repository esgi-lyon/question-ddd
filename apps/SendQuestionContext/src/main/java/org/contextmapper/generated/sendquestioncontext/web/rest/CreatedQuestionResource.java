package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.CreatedQuestionRepository;
import org.contextmapper.generated.sendquestioncontext.service.CreatedQuestionService;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreatedQuestionDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.CreatedQuestion}.
 */
@RestController
@RequestMapping("/api")
public class CreatedQuestionResource {

    private final Logger log = LoggerFactory.getLogger(CreatedQuestionResource.class);

    private static final String ENTITY_NAME = "sendQuestionContextCreatedQuestion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreatedQuestionService createdQuestionService;

    private final CreatedQuestionRepository createdQuestionRepository;

    public CreatedQuestionResource(CreatedQuestionService createdQuestionService, CreatedQuestionRepository createdQuestionRepository) {
        this.createdQuestionService = createdQuestionService;
        this.createdQuestionRepository = createdQuestionRepository;
    }

    /**
     * {@code POST  /created-questions} : Create a new createdQuestion.
     *
     * @param createdQuestionDTO the createdQuestionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createdQuestionDTO, or with status {@code 400 (Bad Request)} if the createdQuestion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/created-questions")
    public ResponseEntity<CreatedQuestionDTO> createCreatedQuestion(@RequestBody CreatedQuestionDTO createdQuestionDTO)
        throws URISyntaxException {
        log.debug("REST request to save CreatedQuestion : {}", createdQuestionDTO);
        if (createdQuestionDTO.getId() != null) {
            throw new BadRequestAlertException("A new createdQuestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreatedQuestionDTO result = createdQuestionService.save(createdQuestionDTO);
        return ResponseEntity
            .created(new URI("/api/created-questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /created-questions/:id} : Updates an existing createdQuestion.
     *
     * @param id the id of the createdQuestionDTO to save.
     * @param createdQuestionDTO the createdQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createdQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the createdQuestionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the createdQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/created-questions/{id}")
    public ResponseEntity<CreatedQuestionDTO> updateCreatedQuestion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreatedQuestionDTO createdQuestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CreatedQuestion : {}, {}", id, createdQuestionDTO);
        if (createdQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createdQuestionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createdQuestionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CreatedQuestionDTO result = createdQuestionService.update(createdQuestionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createdQuestionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /created-questions/:id} : Partial updates given fields of an existing createdQuestion, field will ignore if it is null
     *
     * @param id the id of the createdQuestionDTO to save.
     * @param createdQuestionDTO the createdQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createdQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the createdQuestionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the createdQuestionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the createdQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/created-questions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CreatedQuestionDTO> partialUpdateCreatedQuestion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreatedQuestionDTO createdQuestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreatedQuestion partially : {}, {}", id, createdQuestionDTO);
        if (createdQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createdQuestionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createdQuestionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CreatedQuestionDTO> result = createdQuestionService.partialUpdate(createdQuestionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createdQuestionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /created-questions} : get all the createdQuestions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createdQuestions in body.
     */
    @GetMapping("/created-questions")
    public List<CreatedQuestionDTO> getAllCreatedQuestions() {
        log.debug("REST request to get all CreatedQuestions");
        return createdQuestionService.findAll();
    }

    /**
     * {@code GET  /created-questions/:id} : get the "id" createdQuestion.
     *
     * @param id the id of the createdQuestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createdQuestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/created-questions/{id}")
    public ResponseEntity<CreatedQuestionDTO> getCreatedQuestion(@PathVariable Long id) {
        log.debug("REST request to get CreatedQuestion : {}", id);
        Optional<CreatedQuestionDTO> createdQuestionDTO = createdQuestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createdQuestionDTO);
    }

    /**
     * {@code DELETE  /created-questions/:id} : delete the "id" createdQuestion.
     *
     * @param id the id of the createdQuestionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/created-questions/{id}")
    public ResponseEntity<Void> deleteCreatedQuestion(@PathVariable Long id) {
        log.debug("REST request to delete CreatedQuestion : {}", id);
        createdQuestionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
