package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.AnswerSubmittedRepository;
import org.contextmapper.generated.answercontext.service.AnswerSubmittedService;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmittedDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.AnswerSubmitted}.
 */
@RestController
@RequestMapping("/api")
public class AnswerSubmittedResource {

    private final Logger log = LoggerFactory.getLogger(AnswerSubmittedResource.class);

    private static final String ENTITY_NAME = "answerContextAnswerSubmitted";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnswerSubmittedService answerSubmittedService;

    private final AnswerSubmittedRepository answerSubmittedRepository;

    public AnswerSubmittedResource(AnswerSubmittedService answerSubmittedService, AnswerSubmittedRepository answerSubmittedRepository) {
        this.answerSubmittedService = answerSubmittedService;
        this.answerSubmittedRepository = answerSubmittedRepository;
    }

    /**
     * {@code POST  /answer-submitteds} : Create a new answerSubmitted.
     *
     * @param answerSubmittedDTO the answerSubmittedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new answerSubmittedDTO, or with status {@code 400 (Bad Request)} if the answerSubmitted has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/answer-submitteds")
    public ResponseEntity<AnswerSubmittedDTO> createAnswerSubmitted(@RequestBody AnswerSubmittedDTO answerSubmittedDTO)
        throws URISyntaxException {
        log.debug("REST request to save AnswerSubmitted : {}", answerSubmittedDTO);
        if (answerSubmittedDTO.getId() != null) {
            throw new BadRequestAlertException("A new answerSubmitted cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnswerSubmittedDTO result = answerSubmittedService.save(answerSubmittedDTO);
        return ResponseEntity
            .created(new URI("/api/answer-submitteds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /answer-submitteds/:id} : Updates an existing answerSubmitted.
     *
     * @param id the id of the answerSubmittedDTO to save.
     * @param answerSubmittedDTO the answerSubmittedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated answerSubmittedDTO,
     * or with status {@code 400 (Bad Request)} if the answerSubmittedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the answerSubmittedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/answer-submitteds/{id}")
    public ResponseEntity<AnswerSubmittedDTO> updateAnswerSubmitted(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AnswerSubmittedDTO answerSubmittedDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AnswerSubmitted : {}, {}", id, answerSubmittedDTO);
        if (answerSubmittedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, answerSubmittedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!answerSubmittedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AnswerSubmittedDTO result = answerSubmittedService.update(answerSubmittedDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, answerSubmittedDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /answer-submitteds/:id} : Partial updates given fields of an existing answerSubmitted, field will ignore if it is null
     *
     * @param id the id of the answerSubmittedDTO to save.
     * @param answerSubmittedDTO the answerSubmittedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated answerSubmittedDTO,
     * or with status {@code 400 (Bad Request)} if the answerSubmittedDTO is not valid,
     * or with status {@code 404 (Not Found)} if the answerSubmittedDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the answerSubmittedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/answer-submitteds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AnswerSubmittedDTO> partialUpdateAnswerSubmitted(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AnswerSubmittedDTO answerSubmittedDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AnswerSubmitted partially : {}, {}", id, answerSubmittedDTO);
        if (answerSubmittedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, answerSubmittedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!answerSubmittedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AnswerSubmittedDTO> result = answerSubmittedService.partialUpdate(answerSubmittedDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, answerSubmittedDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /answer-submitteds} : get all the answerSubmitteds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of answerSubmitteds in body.
     */
    @GetMapping("/answer-submitteds")
    public List<AnswerSubmittedDTO> getAllAnswerSubmitteds() {
        log.debug("REST request to get all AnswerSubmitteds");
        return answerSubmittedService.findAll();
    }

    /**
     * {@code GET  /answer-submitteds/:id} : get the "id" answerSubmitted.
     *
     * @param id the id of the answerSubmittedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the answerSubmittedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/answer-submitteds/{id}")
    public ResponseEntity<AnswerSubmittedDTO> getAnswerSubmitted(@PathVariable Long id) {
        log.debug("REST request to get AnswerSubmitted : {}", id);
        Optional<AnswerSubmittedDTO> answerSubmittedDTO = answerSubmittedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(answerSubmittedDTO);
    }

    /**
     * {@code DELETE  /answer-submitteds/:id} : delete the "id" answerSubmitted.
     *
     * @param id the id of the answerSubmittedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/answer-submitteds/{id}")
    public ResponseEntity<Void> deleteAnswerSubmitted(@PathVariable Long id) {
        log.debug("REST request to delete AnswerSubmitted : {}", id);
        answerSubmittedService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
