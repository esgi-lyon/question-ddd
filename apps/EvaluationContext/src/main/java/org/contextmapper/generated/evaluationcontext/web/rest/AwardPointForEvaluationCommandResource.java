package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.domain.AwardPointForEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.repository.AwardPointForEvaluationCommandRepository;
import org.contextmapper.generated.evaluationcontext.service.AwardPointForEvaluationCommandService;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.AwardPointForEvaluationCommand}.
 */
@RestController
@RequestMapping("/api")
public class AwardPointForEvaluationCommandResource {

    private final Logger log = LoggerFactory.getLogger(AwardPointForEvaluationCommandResource.class);

    private static final String ENTITY_NAME = "evaluationContextAwardPointForEvaluationCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AwardPointForEvaluationCommandService awardPointForEvaluationCommandService;

    private final AwardPointForEvaluationCommandRepository awardPointForEvaluationCommandRepository;

    public AwardPointForEvaluationCommandResource(
        AwardPointForEvaluationCommandService awardPointForEvaluationCommandService,
        AwardPointForEvaluationCommandRepository awardPointForEvaluationCommandRepository
    ) {
        this.awardPointForEvaluationCommandService = awardPointForEvaluationCommandService;
        this.awardPointForEvaluationCommandRepository = awardPointForEvaluationCommandRepository;
    }

    /**
     * {@code POST  /award-point-for-evaluation-commands} : Create a new awardPointForEvaluationCommand.
     *
     * @param awardPointForEvaluationCommand the awardPointForEvaluationCommand to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new awardPointForEvaluationCommand, or with status {@code 400 (Bad Request)} if the awardPointForEvaluationCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/award-point-for-evaluation-commands")
    public ResponseEntity<AwardPointForEvaluationCommand> createAwardPointForEvaluationCommand(
        @RequestBody AwardPointForEvaluationCommand awardPointForEvaluationCommand
    ) throws URISyntaxException {
        log.debug("REST request to save AwardPointForEvaluationCommand : {}", awardPointForEvaluationCommand);
        if (awardPointForEvaluationCommand.getId() != null) {
            throw new BadRequestAlertException("A new awardPointForEvaluationCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AwardPointForEvaluationCommand result = awardPointForEvaluationCommandService.save(awardPointForEvaluationCommand);
        return ResponseEntity
            .created(new URI("/api/award-point-for-evaluation-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /award-point-for-evaluation-commands/:id} : Updates an existing awardPointForEvaluationCommand.
     *
     * @param id the id of the awardPointForEvaluationCommand to save.
     * @param awardPointForEvaluationCommand the awardPointForEvaluationCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated awardPointForEvaluationCommand,
     * or with status {@code 400 (Bad Request)} if the awardPointForEvaluationCommand is not valid,
     * or with status {@code 500 (Internal Server Error)} if the awardPointForEvaluationCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/award-point-for-evaluation-commands/{id}")
    public ResponseEntity<AwardPointForEvaluationCommand> updateAwardPointForEvaluationCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AwardPointForEvaluationCommand awardPointForEvaluationCommand
    ) throws URISyntaxException {
        log.debug("REST request to update AwardPointForEvaluationCommand : {}, {}", id, awardPointForEvaluationCommand);
        if (awardPointForEvaluationCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, awardPointForEvaluationCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!awardPointForEvaluationCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AwardPointForEvaluationCommand result = awardPointForEvaluationCommandService.update(awardPointForEvaluationCommand);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, awardPointForEvaluationCommand.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /award-point-for-evaluation-commands/:id} : Partial updates given fields of an existing awardPointForEvaluationCommand, field will ignore if it is null
     *
     * @param id the id of the awardPointForEvaluationCommand to save.
     * @param awardPointForEvaluationCommand the awardPointForEvaluationCommand to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated awardPointForEvaluationCommand,
     * or with status {@code 400 (Bad Request)} if the awardPointForEvaluationCommand is not valid,
     * or with status {@code 404 (Not Found)} if the awardPointForEvaluationCommand is not found,
     * or with status {@code 500 (Internal Server Error)} if the awardPointForEvaluationCommand couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/award-point-for-evaluation-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AwardPointForEvaluationCommand> partialUpdateAwardPointForEvaluationCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AwardPointForEvaluationCommand awardPointForEvaluationCommand
    ) throws URISyntaxException {
        log.debug("REST request to partial update AwardPointForEvaluationCommand partially : {}, {}", id, awardPointForEvaluationCommand);
        if (awardPointForEvaluationCommand.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, awardPointForEvaluationCommand.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!awardPointForEvaluationCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AwardPointForEvaluationCommand> result = awardPointForEvaluationCommandService.partialUpdate(
            awardPointForEvaluationCommand
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, awardPointForEvaluationCommand.getId().toString())
        );
    }

    /**
     * {@code GET  /award-point-for-evaluation-commands} : get all the awardPointForEvaluationCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of awardPointForEvaluationCommands in body.
     */
    @GetMapping("/award-point-for-evaluation-commands")
    public List<AwardPointForEvaluationCommand> getAllAwardPointForEvaluationCommands() {
        log.debug("REST request to get all AwardPointForEvaluationCommands");
        return awardPointForEvaluationCommandService.findAll();
    }

    /**
     * {@code GET  /award-point-for-evaluation-commands/:id} : get the "id" awardPointForEvaluationCommand.
     *
     * @param id the id of the awardPointForEvaluationCommand to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the awardPointForEvaluationCommand, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/award-point-for-evaluation-commands/{id}")
    public ResponseEntity<AwardPointForEvaluationCommand> getAwardPointForEvaluationCommand(@PathVariable Long id) {
        log.debug("REST request to get AwardPointForEvaluationCommand : {}", id);
        Optional<AwardPointForEvaluationCommand> awardPointForEvaluationCommand = awardPointForEvaluationCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(awardPointForEvaluationCommand);
    }

    /**
     * {@code DELETE  /award-point-for-evaluation-commands/:id} : delete the "id" awardPointForEvaluationCommand.
     *
     * @param id the id of the awardPointForEvaluationCommand to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/award-point-for-evaluation-commands/{id}")
    public ResponseEntity<Void> deleteAwardPointForEvaluationCommand(@PathVariable Long id) {
        log.debug("REST request to delete AwardPointForEvaluationCommand : {}", id);
        awardPointForEvaluationCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
