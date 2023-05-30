package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.CheckAnswerCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.CheckAnswerCommandService;
import org.contextmapper.generated.evaluationcontext.service.dto.CheckAnswerCommandDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.CheckAnswerCommand}.
 */
@RestController
@RequestMapping("/api")
public class CheckAnswerCommandResource {

    private final Logger log = LoggerFactory.getLogger(CheckAnswerCommandResource.class);

    private static final String ENTITY_NAME = "evaluationContextCheckAnswerCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CheckAnswerCommandService checkAnswerCommandService;

    private final CheckAnswerCommandRepository checkAnswerCommandRepository;

    public CheckAnswerCommandResource(
        CheckAnswerCommandService checkAnswerCommandService,
        CheckAnswerCommandRepository checkAnswerCommandRepository
    ) {
        this.checkAnswerCommandService = checkAnswerCommandService;
        this.checkAnswerCommandRepository = checkAnswerCommandRepository;
    }

    /**
     * {@code POST  /check-answer-commands} : Create a new checkAnswerCommand.
     *
     * @param checkAnswerCommandDTO the checkAnswerCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new checkAnswerCommandDTO, or with status {@code 400 (Bad Request)} if the checkAnswerCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/check-answer-commands")
    public ResponseEntity<CheckAnswerCommandDTO> createCheckAnswerCommand(@RequestBody CheckAnswerCommandDTO checkAnswerCommandDTO)
        throws URISyntaxException {
        log.debug("REST request to save CheckAnswerCommand : {}", checkAnswerCommandDTO);
        if (checkAnswerCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new checkAnswerCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CheckAnswerCommandDTO result = checkAnswerCommandService.save(checkAnswerCommandDTO);
        return ResponseEntity
            .created(new URI("/api/check-answer-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /check-answer-commands/:id} : Updates an existing checkAnswerCommand.
     *
     * @param id the id of the checkAnswerCommandDTO to save.
     * @param checkAnswerCommandDTO the checkAnswerCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checkAnswerCommandDTO,
     * or with status {@code 400 (Bad Request)} if the checkAnswerCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the checkAnswerCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/check-answer-commands/{id}")
    public ResponseEntity<CheckAnswerCommandDTO> updateCheckAnswerCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CheckAnswerCommandDTO checkAnswerCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CheckAnswerCommand : {}, {}", id, checkAnswerCommandDTO);
        if (checkAnswerCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, checkAnswerCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!checkAnswerCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CheckAnswerCommandDTO result = checkAnswerCommandService.update(checkAnswerCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, checkAnswerCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /check-answer-commands/:id} : Partial updates given fields of an existing checkAnswerCommand, field will ignore if it is null
     *
     * @param id the id of the checkAnswerCommandDTO to save.
     * @param checkAnswerCommandDTO the checkAnswerCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checkAnswerCommandDTO,
     * or with status {@code 400 (Bad Request)} if the checkAnswerCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the checkAnswerCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the checkAnswerCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/check-answer-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CheckAnswerCommandDTO> partialUpdateCheckAnswerCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CheckAnswerCommandDTO checkAnswerCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CheckAnswerCommand partially : {}, {}", id, checkAnswerCommandDTO);
        if (checkAnswerCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, checkAnswerCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!checkAnswerCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CheckAnswerCommandDTO> result = checkAnswerCommandService.partialUpdate(checkAnswerCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, checkAnswerCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /check-answer-commands} : get all the checkAnswerCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of checkAnswerCommands in body.
     */
    @GetMapping("/check-answer-commands")
    public List<CheckAnswerCommandDTO> getAllCheckAnswerCommands() {
        log.debug("REST request to get all CheckAnswerCommands");
        return checkAnswerCommandService.findAll();
    }

    /**
     * {@code GET  /check-answer-commands/:id} : get the "id" checkAnswerCommand.
     *
     * @param id the id of the checkAnswerCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the checkAnswerCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/check-answer-commands/{id}")
    public ResponseEntity<CheckAnswerCommandDTO> getCheckAnswerCommand(@PathVariable Long id) {
        log.debug("REST request to get CheckAnswerCommand : {}", id);
        Optional<CheckAnswerCommandDTO> checkAnswerCommandDTO = checkAnswerCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(checkAnswerCommandDTO);
    }

    /**
     * {@code DELETE  /check-answer-commands/:id} : delete the "id" checkAnswerCommand.
     *
     * @param id the id of the checkAnswerCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/check-answer-commands/{id}")
    public ResponseEntity<Void> deleteCheckAnswerCommand(@PathVariable Long id) {
        log.debug("REST request to delete CheckAnswerCommand : {}", id);
        checkAnswerCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
