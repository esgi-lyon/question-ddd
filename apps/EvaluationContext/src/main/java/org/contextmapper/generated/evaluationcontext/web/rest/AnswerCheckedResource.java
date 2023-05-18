package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.AnswerCheckedRepository;
import org.contextmapper.generated.evaluationcontext.service.AnswerCheckedService;
import org.contextmapper.generated.evaluationcontext.service.dto.AnswerCheckedDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.AnswerChecked}.
 */
@RestController
@RequestMapping("/api")
public class AnswerCheckedResource {

    private final Logger log = LoggerFactory.getLogger(AnswerCheckedResource.class);

    private static final String ENTITY_NAME = "evaluationContextAnswerChecked";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnswerCheckedService answerCheckedService;

    private final AnswerCheckedRepository answerCheckedRepository;

    public AnswerCheckedResource(AnswerCheckedService answerCheckedService, AnswerCheckedRepository answerCheckedRepository) {
        this.answerCheckedService = answerCheckedService;
        this.answerCheckedRepository = answerCheckedRepository;
    }

    /**
     * {@code POST  /answer-checkeds} : Create a new answerChecked.
     *
     * @param answerCheckedDTO the answerCheckedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new answerCheckedDTO, or with status {@code 400 (Bad Request)} if the answerChecked has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/answer-checkeds")
    public ResponseEntity<AnswerCheckedDTO> createAnswerChecked(@RequestBody AnswerCheckedDTO answerCheckedDTO) throws URISyntaxException {
        log.debug("REST request to save AnswerChecked : {}", answerCheckedDTO);
        if (answerCheckedDTO.getId() != null) {
            throw new BadRequestAlertException("A new answerChecked cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnswerCheckedDTO result = answerCheckedService.save(answerCheckedDTO);
        return ResponseEntity
            .created(new URI("/api/answer-checkeds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /answer-checkeds/:id} : Updates an existing answerChecked.
     *
     * @param id the id of the answerCheckedDTO to save.
     * @param answerCheckedDTO the answerCheckedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated answerCheckedDTO,
     * or with status {@code 400 (Bad Request)} if the answerCheckedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the answerCheckedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/answer-checkeds/{id}")
    public ResponseEntity<AnswerCheckedDTO> updateAnswerChecked(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AnswerCheckedDTO answerCheckedDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AnswerChecked : {}, {}", id, answerCheckedDTO);
        if (answerCheckedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, answerCheckedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!answerCheckedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AnswerCheckedDTO result = answerCheckedService.update(answerCheckedDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, answerCheckedDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /answer-checkeds/:id} : Partial updates given fields of an existing answerChecked, field will ignore if it is null
     *
     * @param id the id of the answerCheckedDTO to save.
     * @param answerCheckedDTO the answerCheckedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated answerCheckedDTO,
     * or with status {@code 400 (Bad Request)} if the answerCheckedDTO is not valid,
     * or with status {@code 404 (Not Found)} if the answerCheckedDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the answerCheckedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/answer-checkeds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AnswerCheckedDTO> partialUpdateAnswerChecked(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AnswerCheckedDTO answerCheckedDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AnswerChecked partially : {}, {}", id, answerCheckedDTO);
        if (answerCheckedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, answerCheckedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!answerCheckedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AnswerCheckedDTO> result = answerCheckedService.partialUpdate(answerCheckedDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, answerCheckedDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /answer-checkeds} : get all the answerCheckeds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of answerCheckeds in body.
     */
    @GetMapping("/answer-checkeds")
    public List<AnswerCheckedDTO> getAllAnswerCheckeds() {
        log.debug("REST request to get all AnswerCheckeds");
        return answerCheckedService.findAll();
    }

    /**
     * {@code GET  /answer-checkeds/:id} : get the "id" answerChecked.
     *
     * @param id the id of the answerCheckedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the answerCheckedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/answer-checkeds/{id}")
    public ResponseEntity<AnswerCheckedDTO> getAnswerChecked(@PathVariable Long id) {
        log.debug("REST request to get AnswerChecked : {}", id);
        Optional<AnswerCheckedDTO> answerCheckedDTO = answerCheckedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(answerCheckedDTO);
    }

    /**
     * {@code DELETE  /answer-checkeds/:id} : delete the "id" answerChecked.
     *
     * @param id the id of the answerCheckedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/answer-checkeds/{id}")
    public ResponseEntity<Void> deleteAnswerChecked(@PathVariable Long id) {
        log.debug("REST request to delete AnswerChecked : {}", id);
        answerCheckedService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
