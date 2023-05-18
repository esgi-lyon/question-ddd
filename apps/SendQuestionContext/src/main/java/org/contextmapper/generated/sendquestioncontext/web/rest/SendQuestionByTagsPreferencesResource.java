package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.SendQuestionByTagsPreferencesRepository;
import org.contextmapper.generated.sendquestioncontext.service.SendQuestionByTagsPreferencesService;
import org.contextmapper.generated.sendquestioncontext.service.dto.SendQuestionByTagsPreferencesDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.SendQuestionByTagsPreferences}.
 */
@RestController
@RequestMapping("/api")
public class SendQuestionByTagsPreferencesResource {

    private final Logger log = LoggerFactory.getLogger(SendQuestionByTagsPreferencesResource.class);

    private static final String ENTITY_NAME = "sendQuestionContextSendQuestionByTagsPreferences";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SendQuestionByTagsPreferencesService sendQuestionByTagsPreferencesService;

    private final SendQuestionByTagsPreferencesRepository sendQuestionByTagsPreferencesRepository;

    public SendQuestionByTagsPreferencesResource(
        SendQuestionByTagsPreferencesService sendQuestionByTagsPreferencesService,
        SendQuestionByTagsPreferencesRepository sendQuestionByTagsPreferencesRepository
    ) {
        this.sendQuestionByTagsPreferencesService = sendQuestionByTagsPreferencesService;
        this.sendQuestionByTagsPreferencesRepository = sendQuestionByTagsPreferencesRepository;
    }

    /**
     * {@code POST  /send-question-by-tags-preferences} : Create a new sendQuestionByTagsPreferences.
     *
     * @param sendQuestionByTagsPreferencesDTO the sendQuestionByTagsPreferencesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sendQuestionByTagsPreferencesDTO, or with status {@code 400 (Bad Request)} if the sendQuestionByTagsPreferences has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/send-question-by-tags-preferences")
    public ResponseEntity<SendQuestionByTagsPreferencesDTO> createSendQuestionByTagsPreferences(
        @RequestBody SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SendQuestionByTagsPreferences : {}", sendQuestionByTagsPreferencesDTO);
        if (sendQuestionByTagsPreferencesDTO.getId() != null) {
            throw new BadRequestAlertException("A new sendQuestionByTagsPreferences cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SendQuestionByTagsPreferencesDTO result = sendQuestionByTagsPreferencesService.save(sendQuestionByTagsPreferencesDTO);
        return ResponseEntity
            .created(new URI("/api/send-question-by-tags-preferences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /send-question-by-tags-preferences/:id} : Updates an existing sendQuestionByTagsPreferences.
     *
     * @param id the id of the sendQuestionByTagsPreferencesDTO to save.
     * @param sendQuestionByTagsPreferencesDTO the sendQuestionByTagsPreferencesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sendQuestionByTagsPreferencesDTO,
     * or with status {@code 400 (Bad Request)} if the sendQuestionByTagsPreferencesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sendQuestionByTagsPreferencesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/send-question-by-tags-preferences/{id}")
    public ResponseEntity<SendQuestionByTagsPreferencesDTO> updateSendQuestionByTagsPreferences(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SendQuestionByTagsPreferences : {}, {}", id, sendQuestionByTagsPreferencesDTO);
        if (sendQuestionByTagsPreferencesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sendQuestionByTagsPreferencesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sendQuestionByTagsPreferencesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SendQuestionByTagsPreferencesDTO result = sendQuestionByTagsPreferencesService.update(sendQuestionByTagsPreferencesDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sendQuestionByTagsPreferencesDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /send-question-by-tags-preferences/:id} : Partial updates given fields of an existing sendQuestionByTagsPreferences, field will ignore if it is null
     *
     * @param id the id of the sendQuestionByTagsPreferencesDTO to save.
     * @param sendQuestionByTagsPreferencesDTO the sendQuestionByTagsPreferencesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sendQuestionByTagsPreferencesDTO,
     * or with status {@code 400 (Bad Request)} if the sendQuestionByTagsPreferencesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sendQuestionByTagsPreferencesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sendQuestionByTagsPreferencesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/send-question-by-tags-preferences/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SendQuestionByTagsPreferencesDTO> partialUpdateSendQuestionByTagsPreferences(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SendQuestionByTagsPreferencesDTO sendQuestionByTagsPreferencesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SendQuestionByTagsPreferences partially : {}, {}", id, sendQuestionByTagsPreferencesDTO);
        if (sendQuestionByTagsPreferencesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sendQuestionByTagsPreferencesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sendQuestionByTagsPreferencesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SendQuestionByTagsPreferencesDTO> result = sendQuestionByTagsPreferencesService.partialUpdate(
            sendQuestionByTagsPreferencesDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sendQuestionByTagsPreferencesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /send-question-by-tags-preferences} : get all the sendQuestionByTagsPreferences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sendQuestionByTagsPreferences in body.
     */
    @GetMapping("/send-question-by-tags-preferences")
    public List<SendQuestionByTagsPreferencesDTO> getAllSendQuestionByTagsPreferences() {
        log.debug("REST request to get all SendQuestionByTagsPreferences");
        return sendQuestionByTagsPreferencesService.findAll();
    }

    /**
     * {@code GET  /send-question-by-tags-preferences/:id} : get the "id" sendQuestionByTagsPreferences.
     *
     * @param id the id of the sendQuestionByTagsPreferencesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sendQuestionByTagsPreferencesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/send-question-by-tags-preferences/{id}")
    public ResponseEntity<SendQuestionByTagsPreferencesDTO> getSendQuestionByTagsPreferences(@PathVariable Long id) {
        log.debug("REST request to get SendQuestionByTagsPreferences : {}", id);
        Optional<SendQuestionByTagsPreferencesDTO> sendQuestionByTagsPreferencesDTO = sendQuestionByTagsPreferencesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sendQuestionByTagsPreferencesDTO);
    }

    /**
     * {@code DELETE  /send-question-by-tags-preferences/:id} : delete the "id" sendQuestionByTagsPreferences.
     *
     * @param id the id of the sendQuestionByTagsPreferencesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/send-question-by-tags-preferences/{id}")
    public ResponseEntity<Void> deleteSendQuestionByTagsPreferences(@PathVariable Long id) {
        log.debug("REST request to delete SendQuestionByTagsPreferences : {}", id);
        sendQuestionByTagsPreferencesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
