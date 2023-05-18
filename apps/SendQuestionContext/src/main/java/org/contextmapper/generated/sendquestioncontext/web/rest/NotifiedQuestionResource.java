package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.NotifiedQuestionRepository;
import org.contextmapper.generated.sendquestioncontext.service.NotifiedQuestionService;
import org.contextmapper.generated.sendquestioncontext.service.dto.NotifiedQuestionDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.NotifiedQuestion}.
 */
@RestController
@RequestMapping("/api")
public class NotifiedQuestionResource {

    private final Logger log = LoggerFactory.getLogger(NotifiedQuestionResource.class);

    private static final String ENTITY_NAME = "sendQuestionContextNotifiedQuestion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotifiedQuestionService notifiedQuestionService;

    private final NotifiedQuestionRepository notifiedQuestionRepository;

    public NotifiedQuestionResource(
        NotifiedQuestionService notifiedQuestionService,
        NotifiedQuestionRepository notifiedQuestionRepository
    ) {
        this.notifiedQuestionService = notifiedQuestionService;
        this.notifiedQuestionRepository = notifiedQuestionRepository;
    }

    /**
     * {@code POST  /notified-questions} : Create a new notifiedQuestion.
     *
     * @param notifiedQuestionDTO the notifiedQuestionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notifiedQuestionDTO, or with status {@code 400 (Bad Request)} if the notifiedQuestion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notified-questions")
    public ResponseEntity<NotifiedQuestionDTO> createNotifiedQuestion(@RequestBody NotifiedQuestionDTO notifiedQuestionDTO)
        throws URISyntaxException {
        log.debug("REST request to save NotifiedQuestion : {}", notifiedQuestionDTO);
        if (notifiedQuestionDTO.getId() != null) {
            throw new BadRequestAlertException("A new notifiedQuestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotifiedQuestionDTO result = notifiedQuestionService.save(notifiedQuestionDTO);
        return ResponseEntity
            .created(new URI("/api/notified-questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notified-questions/:id} : Updates an existing notifiedQuestion.
     *
     * @param id the id of the notifiedQuestionDTO to save.
     * @param notifiedQuestionDTO the notifiedQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notifiedQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the notifiedQuestionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notifiedQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notified-questions/{id}")
    public ResponseEntity<NotifiedQuestionDTO> updateNotifiedQuestion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NotifiedQuestionDTO notifiedQuestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NotifiedQuestion : {}, {}", id, notifiedQuestionDTO);
        if (notifiedQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, notifiedQuestionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!notifiedQuestionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NotifiedQuestionDTO result = notifiedQuestionService.update(notifiedQuestionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notifiedQuestionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /notified-questions/:id} : Partial updates given fields of an existing notifiedQuestion, field will ignore if it is null
     *
     * @param id the id of the notifiedQuestionDTO to save.
     * @param notifiedQuestionDTO the notifiedQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notifiedQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the notifiedQuestionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the notifiedQuestionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the notifiedQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/notified-questions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NotifiedQuestionDTO> partialUpdateNotifiedQuestion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NotifiedQuestionDTO notifiedQuestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NotifiedQuestion partially : {}, {}", id, notifiedQuestionDTO);
        if (notifiedQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, notifiedQuestionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!notifiedQuestionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NotifiedQuestionDTO> result = notifiedQuestionService.partialUpdate(notifiedQuestionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notifiedQuestionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /notified-questions} : get all the notifiedQuestions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notifiedQuestions in body.
     */
    @GetMapping("/notified-questions")
    public List<NotifiedQuestionDTO> getAllNotifiedQuestions() {
        log.debug("REST request to get all NotifiedQuestions");
        return notifiedQuestionService.findAll();
    }

    /**
     * {@code GET  /notified-questions/:id} : get the "id" notifiedQuestion.
     *
     * @param id the id of the notifiedQuestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notifiedQuestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notified-questions/{id}")
    public ResponseEntity<NotifiedQuestionDTO> getNotifiedQuestion(@PathVariable Long id) {
        log.debug("REST request to get NotifiedQuestion : {}", id);
        Optional<NotifiedQuestionDTO> notifiedQuestionDTO = notifiedQuestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notifiedQuestionDTO);
    }

    /**
     * {@code DELETE  /notified-questions/:id} : delete the "id" notifiedQuestion.
     *
     * @param id the id of the notifiedQuestionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notified-questions/{id}")
    public ResponseEntity<Void> deleteNotifiedQuestion(@PathVariable Long id) {
        log.debug("REST request to delete NotifiedQuestion : {}", id);
        notifiedQuestionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
