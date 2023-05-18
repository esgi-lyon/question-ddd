package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.CreateQuestionRepository;
import org.contextmapper.generated.sendquestioncontext.service.CreateQuestionService;
import org.contextmapper.generated.sendquestioncontext.service.dto.CreateQuestionDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.CreateQuestion}.
 */
@RestController
@RequestMapping("/api")
public class CreateQuestionResource {

    private final Logger log = LoggerFactory.getLogger(CreateQuestionResource.class);

    private static final String ENTITY_NAME = "sendQuestionContextCreateQuestion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreateQuestionService createQuestionService;

    private final CreateQuestionRepository createQuestionRepository;

    public CreateQuestionResource(CreateQuestionService createQuestionService, CreateQuestionRepository createQuestionRepository) {
        this.createQuestionService = createQuestionService;
        this.createQuestionRepository = createQuestionRepository;
    }

    /**
     * {@code POST  /create-questions} : Create a new createQuestion.
     *
     * @param createQuestionDTO the createQuestionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createQuestionDTO, or with status {@code 400 (Bad Request)} if the createQuestion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-questions")
    public ResponseEntity<CreateQuestionDTO> createCreateQuestion(@RequestBody CreateQuestionDTO createQuestionDTO)
        throws URISyntaxException {
        log.debug("REST request to save CreateQuestion : {}", createQuestionDTO);
        if (createQuestionDTO.getId() != null) {
            throw new BadRequestAlertException("A new createQuestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreateQuestionDTO result = createQuestionService.save(createQuestionDTO);
        return ResponseEntity
            .created(new URI("/api/create-questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /create-questions/:id} : Updates an existing createQuestion.
     *
     * @param id the id of the createQuestionDTO to save.
     * @param createQuestionDTO the createQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the createQuestionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the createQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/create-questions/{id}")
    public ResponseEntity<CreateQuestionDTO> updateCreateQuestion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateQuestionDTO createQuestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CreateQuestion : {}, {}", id, createQuestionDTO);
        if (createQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createQuestionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createQuestionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CreateQuestionDTO result = createQuestionService.update(createQuestionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createQuestionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /create-questions/:id} : Partial updates given fields of an existing createQuestion, field will ignore if it is null
     *
     * @param id the id of the createQuestionDTO to save.
     * @param createQuestionDTO the createQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the createQuestionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the createQuestionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the createQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/create-questions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CreateQuestionDTO> partialUpdateCreateQuestion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateQuestionDTO createQuestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreateQuestion partially : {}, {}", id, createQuestionDTO);
        if (createQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createQuestionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createQuestionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CreateQuestionDTO> result = createQuestionService.partialUpdate(createQuestionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createQuestionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /create-questions} : get all the createQuestions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createQuestions in body.
     */
    @GetMapping("/create-questions")
    public List<CreateQuestionDTO> getAllCreateQuestions() {
        log.debug("REST request to get all CreateQuestions");
        return createQuestionService.findAll();
    }

    /**
     * {@code GET  /create-questions/:id} : get the "id" createQuestion.
     *
     * @param id the id of the createQuestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createQuestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/create-questions/{id}")
    public ResponseEntity<CreateQuestionDTO> getCreateQuestion(@PathVariable Long id) {
        log.debug("REST request to get CreateQuestion : {}", id);
        Optional<CreateQuestionDTO> createQuestionDTO = createQuestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createQuestionDTO);
    }

    /**
     * {@code DELETE  /create-questions/:id} : delete the "id" createQuestion.
     *
     * @param id the id of the createQuestionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/create-questions/{id}")
    public ResponseEntity<Void> deleteCreateQuestion(@PathVariable Long id) {
        log.debug("REST request to delete CreateQuestion : {}", id);
        createQuestionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
