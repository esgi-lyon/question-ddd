package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.AnswerSubmitRepository;
import org.contextmapper.generated.answercontext.service.AnswerSubmitService;
import org.contextmapper.generated.answercontext.service.dto.AnswerSubmitDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.AnswerSubmit}.
 */
@RestController
@RequestMapping("/api")
public class AnswerSubmitResource {

    private final Logger log = LoggerFactory.getLogger(AnswerSubmitResource.class);

    private static final String ENTITY_NAME = "answerContextAnswerSubmit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnswerSubmitService answerSubmitService;

    private final AnswerSubmitRepository answerSubmitRepository;

    public AnswerSubmitResource(AnswerSubmitService answerSubmitService, AnswerSubmitRepository answerSubmitRepository) {
        this.answerSubmitService = answerSubmitService;
        this.answerSubmitRepository = answerSubmitRepository;
    }

    /**
     * {@code POST  /answer-submits} : Create a new answerSubmit.
     *
     * @param answerSubmitDTO the answerSubmitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new answerSubmitDTO, or with status {@code 400 (Bad Request)} if the answerSubmit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/answer-submits")
    public ResponseEntity<AnswerSubmitDTO> createAnswerSubmit(@RequestBody AnswerSubmitDTO answerSubmitDTO) throws URISyntaxException {
        log.debug("REST request to save AnswerSubmit : {}", answerSubmitDTO);
        if (answerSubmitDTO.getId() != null) {
            throw new BadRequestAlertException("A new answerSubmit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnswerSubmitDTO result = answerSubmitService.save(answerSubmitDTO);
        return ResponseEntity
            .created(new URI("/api/answer-submits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /answer-submits/:id} : Updates an existing answerSubmit.
     *
     * @param id the id of the answerSubmitDTO to save.
     * @param answerSubmitDTO the answerSubmitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated answerSubmitDTO,
     * or with status {@code 400 (Bad Request)} if the answerSubmitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the answerSubmitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/answer-submits/{id}")
    public ResponseEntity<AnswerSubmitDTO> updateAnswerSubmit(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AnswerSubmitDTO answerSubmitDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AnswerSubmit : {}, {}", id, answerSubmitDTO);
        if (answerSubmitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, answerSubmitDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!answerSubmitRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AnswerSubmitDTO result = answerSubmitService.update(answerSubmitDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, answerSubmitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /answer-submits/:id} : Partial updates given fields of an existing answerSubmit, field will ignore if it is null
     *
     * @param id the id of the answerSubmitDTO to save.
     * @param answerSubmitDTO the answerSubmitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated answerSubmitDTO,
     * or with status {@code 400 (Bad Request)} if the answerSubmitDTO is not valid,
     * or with status {@code 404 (Not Found)} if the answerSubmitDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the answerSubmitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/answer-submits/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AnswerSubmitDTO> partialUpdateAnswerSubmit(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AnswerSubmitDTO answerSubmitDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AnswerSubmit partially : {}, {}", id, answerSubmitDTO);
        if (answerSubmitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, answerSubmitDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!answerSubmitRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AnswerSubmitDTO> result = answerSubmitService.partialUpdate(answerSubmitDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, answerSubmitDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /answer-submits} : get all the answerSubmits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of answerSubmits in body.
     */
    @GetMapping("/answer-submits")
    public List<AnswerSubmitDTO> getAllAnswerSubmits() {
        log.debug("REST request to get all AnswerSubmits");
        return answerSubmitService.findAll();
    }

    /**
     * {@code GET  /answer-submits/:id} : get the "id" answerSubmit.
     *
     * @param id the id of the answerSubmitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the answerSubmitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/answer-submits/{id}")
    public ResponseEntity<AnswerSubmitDTO> getAnswerSubmit(@PathVariable Long id) {
        log.debug("REST request to get AnswerSubmit : {}", id);
        Optional<AnswerSubmitDTO> answerSubmitDTO = answerSubmitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(answerSubmitDTO);
    }

    /**
     * {@code DELETE  /answer-submits/:id} : delete the "id" answerSubmit.
     *
     * @param id the id of the answerSubmitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/answer-submits/{id}")
    public ResponseEntity<Void> deleteAnswerSubmit(@PathVariable Long id) {
        log.debug("REST request to delete AnswerSubmit : {}", id);
        answerSubmitService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
