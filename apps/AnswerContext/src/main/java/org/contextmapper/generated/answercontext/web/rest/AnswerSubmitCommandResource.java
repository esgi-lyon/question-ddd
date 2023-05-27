package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.AnswerSubmitCommandRepository;
import org.contextmapper.generated.answercontext.service.AnswerSubmitCommandService;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmitCommandDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.AnswerSubmitCommand}.
 */
@RestController
@RequestMapping("/api")
public class AnswerSubmitCommandResource {

    private final Logger log = LoggerFactory.getLogger(AnswerSubmitCommandResource.class);

    private static final String ENTITY_NAME = "answerContextAnswerSubmitCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnswerSubmitCommandService answerSubmitCommandService;

    private final AnswerSubmitCommandRepository answerSubmitCommandRepository;

    public AnswerSubmitCommandResource(
        AnswerSubmitCommandService answerSubmitCommandService,
        AnswerSubmitCommandRepository answerSubmitCommandRepository
    ) {
        this.answerSubmitCommandService = answerSubmitCommandService;
        this.answerSubmitCommandRepository = answerSubmitCommandRepository;
    }

    /**
     * {@code POST  /answer-submit-commands} : Create a new answerSubmitCommand.
     *
     * @param answerSubmitCommandDTO the answerSubmitCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new answerSubmitCommandDTO, or with status {@code 400 (Bad Request)} if the answerSubmitCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/answer-submit-commands")
    public ResponseEntity<AnswerSubmitCommandDTO> createAnswerSubmitCommand(@RequestBody AnswerSubmitCommandDTO answerSubmitCommandDTO)
        throws URISyntaxException {
        log.debug("REST request to save AnswerSubmitCommand : {}", answerSubmitCommandDTO);
        if (answerSubmitCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new answerSubmitCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnswerSubmitCommandDTO result = answerSubmitCommandService.save(answerSubmitCommandDTO);
        return ResponseEntity
            .created(new URI("/api/answer-submit-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /answer-submit-commands/:id} : Updates an existing answerSubmitCommand.
     *
     * @param id the id of the answerSubmitCommandDTO to save.
     * @param answerSubmitCommandDTO the answerSubmitCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated answerSubmitCommandDTO,
     * or with status {@code 400 (Bad Request)} if the answerSubmitCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the answerSubmitCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/answer-submit-commands/{id}")
    public ResponseEntity<AnswerSubmitCommandDTO> updateAnswerSubmitCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AnswerSubmitCommandDTO answerSubmitCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AnswerSubmitCommand : {}, {}", id, answerSubmitCommandDTO);
        if (answerSubmitCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, answerSubmitCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!answerSubmitCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AnswerSubmitCommandDTO result = answerSubmitCommandService.update(answerSubmitCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, answerSubmitCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /answer-submit-commands/:id} : Partial updates given fields of an existing answerSubmitCommand, field will ignore if it is null
     *
     * @param id the id of the answerSubmitCommandDTO to save.
     * @param answerSubmitCommandDTO the answerSubmitCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated answerSubmitCommandDTO,
     * or with status {@code 400 (Bad Request)} if the answerSubmitCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the answerSubmitCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the answerSubmitCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/answer-submit-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AnswerSubmitCommandDTO> partialUpdateAnswerSubmitCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AnswerSubmitCommandDTO answerSubmitCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AnswerSubmitCommand partially : {}, {}", id, answerSubmitCommandDTO);
        if (answerSubmitCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, answerSubmitCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!answerSubmitCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AnswerSubmitCommandDTO> result = answerSubmitCommandService.partialUpdate(answerSubmitCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, answerSubmitCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /answer-submit-commands} : get all the answerSubmitCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of answerSubmitCommands in body.
     */
    @GetMapping("/answer-submit-commands")
    public List<AnswerSubmitCommandDTO> getAllAnswerSubmitCommands() {
        log.debug("REST request to get all AnswerSubmitCommands");
        return answerSubmitCommandService.findAll();
    }

    /**
     * {@code GET  /answer-submit-commands/:id} : get the "id" answerSubmitCommand.
     *
     * @param id the id of the answerSubmitCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the answerSubmitCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/answer-submit-commands/{id}")
    public ResponseEntity<AnswerSubmitCommandDTO> getAnswerSubmitCommand(@PathVariable Long id) {
        log.debug("REST request to get AnswerSubmitCommand : {}", id);
        Optional<AnswerSubmitCommandDTO> answerSubmitCommandDTO = answerSubmitCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(answerSubmitCommandDTO);
    }

    /**
     * {@code DELETE  /answer-submit-commands/:id} : delete the "id" answerSubmitCommand.
     *
     * @param id the id of the answerSubmitCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/answer-submit-commands/{id}")
    public ResponseEntity<Void> deleteAnswerSubmitCommand(@PathVariable Long id) {
        log.debug("REST request to delete AnswerSubmitCommand : {}", id);
        answerSubmitCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
