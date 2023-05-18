package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.CheckAnswerRepository;
import org.contextmapper.generated.evaluationcontext.service.CheckAnswerService;
import org.contextmapper.generated.evaluationcontext.service.dto.CheckAnswerDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.CheckAnswer}.
 */
@RestController
@RequestMapping("/api")
public class CheckAnswerResource {

    private final Logger log = LoggerFactory.getLogger(CheckAnswerResource.class);

    private static final String ENTITY_NAME = "evaluationContextCheckAnswer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CheckAnswerService checkAnswerService;

    private final CheckAnswerRepository checkAnswerRepository;

    public CheckAnswerResource(CheckAnswerService checkAnswerService, CheckAnswerRepository checkAnswerRepository) {
        this.checkAnswerService = checkAnswerService;
        this.checkAnswerRepository = checkAnswerRepository;
    }

    /**
     * {@code POST  /check-answers} : Create a new checkAnswer.
     *
     * @param checkAnswerDTO the checkAnswerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new checkAnswerDTO, or with status {@code 400 (Bad Request)} if the checkAnswer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/check-answers")
    public ResponseEntity<CheckAnswerDTO> createCheckAnswer(@RequestBody CheckAnswerDTO checkAnswerDTO) throws URISyntaxException {
        log.debug("REST request to save CheckAnswer : {}", checkAnswerDTO);
        if (checkAnswerDTO.getId() != null) {
            throw new BadRequestAlertException("A new checkAnswer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CheckAnswerDTO result = checkAnswerService.save(checkAnswerDTO);
        return ResponseEntity
            .created(new URI("/api/check-answers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /check-answers/:id} : Updates an existing checkAnswer.
     *
     * @param id the id of the checkAnswerDTO to save.
     * @param checkAnswerDTO the checkAnswerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checkAnswerDTO,
     * or with status {@code 400 (Bad Request)} if the checkAnswerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the checkAnswerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/check-answers/{id}")
    public ResponseEntity<CheckAnswerDTO> updateCheckAnswer(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CheckAnswerDTO checkAnswerDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CheckAnswer : {}, {}", id, checkAnswerDTO);
        if (checkAnswerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, checkAnswerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!checkAnswerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CheckAnswerDTO result = checkAnswerService.update(checkAnswerDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, checkAnswerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /check-answers/:id} : Partial updates given fields of an existing checkAnswer, field will ignore if it is null
     *
     * @param id the id of the checkAnswerDTO to save.
     * @param checkAnswerDTO the checkAnswerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated checkAnswerDTO,
     * or with status {@code 400 (Bad Request)} if the checkAnswerDTO is not valid,
     * or with status {@code 404 (Not Found)} if the checkAnswerDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the checkAnswerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/check-answers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CheckAnswerDTO> partialUpdateCheckAnswer(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CheckAnswerDTO checkAnswerDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CheckAnswer partially : {}, {}", id, checkAnswerDTO);
        if (checkAnswerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, checkAnswerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!checkAnswerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CheckAnswerDTO> result = checkAnswerService.partialUpdate(checkAnswerDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, checkAnswerDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /check-answers} : get all the checkAnswers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of checkAnswers in body.
     */
    @GetMapping("/check-answers")
    public List<CheckAnswerDTO> getAllCheckAnswers() {
        log.debug("REST request to get all CheckAnswers");
        return checkAnswerService.findAll();
    }

    /**
     * {@code GET  /check-answers/:id} : get the "id" checkAnswer.
     *
     * @param id the id of the checkAnswerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the checkAnswerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/check-answers/{id}")
    public ResponseEntity<CheckAnswerDTO> getCheckAnswer(@PathVariable Long id) {
        log.debug("REST request to get CheckAnswer : {}", id);
        Optional<CheckAnswerDTO> checkAnswerDTO = checkAnswerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(checkAnswerDTO);
    }

    /**
     * {@code DELETE  /check-answers/:id} : delete the "id" checkAnswer.
     *
     * @param id the id of the checkAnswerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/check-answers/{id}")
    public ResponseEntity<Void> deleteCheckAnswer(@PathVariable Long id) {
        log.debug("REST request to delete CheckAnswer : {}", id);
        checkAnswerService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
