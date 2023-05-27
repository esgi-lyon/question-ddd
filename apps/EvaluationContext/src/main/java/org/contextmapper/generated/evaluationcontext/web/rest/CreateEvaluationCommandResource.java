package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.CreateEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.CreateEvaluationCommandService;
import org.contextmapper.generated.evaluationcontext.service.dto.CreateEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.CreateEvaluationCommand}.
 */
@RestController
@RequestMapping("/api")
public class CreateEvaluationCommandResource {

    private final Logger log = LoggerFactory.getLogger(CreateEvaluationCommandResource.class);

    private static final String ENTITY_NAME = "evaluationContextCreateEvaluationCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreateEvaluationCommandService createEvaluationCommandService;

    private final CreateEvaluationCommandRepository createEvaluationCommandRepository;

    public CreateEvaluationCommandResource(
        CreateEvaluationCommandService createEvaluationCommandService,
        CreateEvaluationCommandRepository createEvaluationCommandRepository
    ) {
        this.createEvaluationCommandService = createEvaluationCommandService;
        this.createEvaluationCommandRepository = createEvaluationCommandRepository;
    }

    /**
     * {@code POST  /create-evaluation-commands} : Create a new createEvaluationCommand.
     *
     * @param createEvaluationCommandDTO the createEvaluationCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new createEvaluationCommandDTO, or with status {@code 400 (Bad Request)} if the createEvaluationCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/create-evaluation-commands")
    public ResponseEntity<CreateEvaluationCommandDTO> createCreateEvaluationCommand(
        @RequestBody CreateEvaluationCommandDTO createEvaluationCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to save CreateEvaluationCommand : {}", createEvaluationCommandDTO);
        if (createEvaluationCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new createEvaluationCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreateEvaluationCommandDTO result = createEvaluationCommandService.save(createEvaluationCommandDTO);
        return ResponseEntity
            .created(new URI("/api/create-evaluation-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /create-evaluation-commands/:id} : Updates an existing createEvaluationCommand.
     *
     * @param id the id of the createEvaluationCommandDTO to save.
     * @param createEvaluationCommandDTO the createEvaluationCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createEvaluationCommandDTO,
     * or with status {@code 400 (Bad Request)} if the createEvaluationCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the createEvaluationCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/create-evaluation-commands/{id}")
    public ResponseEntity<CreateEvaluationCommandDTO> updateCreateEvaluationCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateEvaluationCommandDTO createEvaluationCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CreateEvaluationCommand : {}, {}", id, createEvaluationCommandDTO);
        if (createEvaluationCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createEvaluationCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createEvaluationCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CreateEvaluationCommandDTO result = createEvaluationCommandService.update(createEvaluationCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createEvaluationCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /create-evaluation-commands/:id} : Partial updates given fields of an existing createEvaluationCommand, field will ignore if it is null
     *
     * @param id the id of the createEvaluationCommandDTO to save.
     * @param createEvaluationCommandDTO the createEvaluationCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated createEvaluationCommandDTO,
     * or with status {@code 400 (Bad Request)} if the createEvaluationCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the createEvaluationCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the createEvaluationCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/create-evaluation-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CreateEvaluationCommandDTO> partialUpdateCreateEvaluationCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreateEvaluationCommandDTO createEvaluationCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreateEvaluationCommand partially : {}, {}", id, createEvaluationCommandDTO);
        if (createEvaluationCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, createEvaluationCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!createEvaluationCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CreateEvaluationCommandDTO> result = createEvaluationCommandService.partialUpdate(createEvaluationCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, createEvaluationCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /create-evaluation-commands} : get all the createEvaluationCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createEvaluationCommands in body.
     */
    @GetMapping("/create-evaluation-commands")
    public List<CreateEvaluationCommandDTO> getAllCreateEvaluationCommands() {
        log.debug("REST request to get all CreateEvaluationCommands");
        return createEvaluationCommandService.findAll();
    }

    /**
     * {@code GET  /create-evaluation-commands/:id} : get the "id" createEvaluationCommand.
     *
     * @param id the id of the createEvaluationCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createEvaluationCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/create-evaluation-commands/{id}")
    public ResponseEntity<CreateEvaluationCommandDTO> getCreateEvaluationCommand(@PathVariable Long id) {
        log.debug("REST request to get CreateEvaluationCommand : {}", id);
        Optional<CreateEvaluationCommandDTO> createEvaluationCommandDTO = createEvaluationCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createEvaluationCommandDTO);
    }

    /**
     * {@code DELETE  /create-evaluation-commands/:id} : delete the "id" createEvaluationCommand.
     *
     * @param id the id of the createEvaluationCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/create-evaluation-commands/{id}")
    public ResponseEntity<Void> deleteCreateEvaluationCommand(@PathVariable Long id) {
        log.debug("REST request to delete CreateEvaluationCommand : {}", id);
        createEvaluationCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
