package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.PrepareQuestionCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.PrepareQuestionCommandService;
import org.contextmapper.generated.sendquestioncontext.service.dto.PrepareQuestionCommandDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionCommand}.
 */
@RestController
@RequestMapping("/api")
public class PrepareQuestionCommandResource {

    private final Logger log = LoggerFactory.getLogger(PrepareQuestionCommandResource.class);

    private static final String ENTITY_NAME = "sendQuestionContextPrepareQuestionCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrepareQuestionCommandService prepareQuestionCommandService;

    private final PrepareQuestionCommandRepository prepareQuestionCommandRepository;

    public PrepareQuestionCommandResource(
        PrepareQuestionCommandService prepareQuestionCommandService,
        PrepareQuestionCommandRepository prepareQuestionCommandRepository
    ) {
        this.prepareQuestionCommandService = prepareQuestionCommandService;
        this.prepareQuestionCommandRepository = prepareQuestionCommandRepository;
    }

    /**
     * {@code POST  /prepare-question-commands} : Create a new prepareQuestionCommand.
     *
     * @param prepareQuestionCommandDTO the prepareQuestionCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new prepareQuestionCommandDTO, or with status {@code 400 (Bad Request)} if the prepareQuestionCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prepare-question-commands")
    public ResponseEntity<PrepareQuestionCommandDTO> createPrepareQuestionCommand(
        @RequestBody PrepareQuestionCommandDTO prepareQuestionCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PrepareQuestionCommand : {}", prepareQuestionCommandDTO);
        if (prepareQuestionCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new prepareQuestionCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrepareQuestionCommandDTO result = prepareQuestionCommandService.save(prepareQuestionCommandDTO);
        return ResponseEntity
            .created(new URI("/api/prepare-question-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prepare-question-commands/:id} : Updates an existing prepareQuestionCommand.
     *
     * @param id the id of the prepareQuestionCommandDTO to save.
     * @param prepareQuestionCommandDTO the prepareQuestionCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prepareQuestionCommandDTO,
     * or with status {@code 400 (Bad Request)} if the prepareQuestionCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the prepareQuestionCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prepare-question-commands/{id}")
    public ResponseEntity<PrepareQuestionCommandDTO> updatePrepareQuestionCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PrepareQuestionCommandDTO prepareQuestionCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PrepareQuestionCommand : {}, {}", id, prepareQuestionCommandDTO);
        if (prepareQuestionCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, prepareQuestionCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!prepareQuestionCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PrepareQuestionCommandDTO result = prepareQuestionCommandService.update(prepareQuestionCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, prepareQuestionCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /prepare-question-commands/:id} : Partial updates given fields of an existing prepareQuestionCommand, field will ignore if it is null
     *
     * @param id the id of the prepareQuestionCommandDTO to save.
     * @param prepareQuestionCommandDTO the prepareQuestionCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prepareQuestionCommandDTO,
     * or with status {@code 400 (Bad Request)} if the prepareQuestionCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the prepareQuestionCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the prepareQuestionCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/prepare-question-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PrepareQuestionCommandDTO> partialUpdatePrepareQuestionCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PrepareQuestionCommandDTO prepareQuestionCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PrepareQuestionCommand partially : {}, {}", id, prepareQuestionCommandDTO);
        if (prepareQuestionCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, prepareQuestionCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!prepareQuestionCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PrepareQuestionCommandDTO> result = prepareQuestionCommandService.partialUpdate(prepareQuestionCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, prepareQuestionCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /prepare-question-commands} : get all the prepareQuestionCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of prepareQuestionCommands in body.
     */
    @GetMapping("/prepare-question-commands")
    public List<PrepareQuestionCommandDTO> getAllPrepareQuestionCommands() {
        log.debug("REST request to get all PrepareQuestionCommands");
        return prepareQuestionCommandService.findAll();
    }

    /**
     * {@code GET  /prepare-question-commands/:id} : get the "id" prepareQuestionCommand.
     *
     * @param id the id of the prepareQuestionCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the prepareQuestionCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prepare-question-commands/{id}")
    public ResponseEntity<PrepareQuestionCommandDTO> getPrepareQuestionCommand(@PathVariable Long id) {
        log.debug("REST request to get PrepareQuestionCommand : {}", id);
        Optional<PrepareQuestionCommandDTO> prepareQuestionCommandDTO = prepareQuestionCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(prepareQuestionCommandDTO);
    }

    /**
     * {@code DELETE  /prepare-question-commands/:id} : delete the "id" prepareQuestionCommand.
     *
     * @param id the id of the prepareQuestionCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prepare-question-commands/{id}")
    public ResponseEntity<Void> deletePrepareQuestionCommand(@PathVariable Long id) {
        log.debug("REST request to delete PrepareQuestionCommand : {}", id);
        prepareQuestionCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
