package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.SendByPreferencesCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.SendByPreferencesCommandService;
import org.contextmapper.generated.sendquestioncontext.service.dto.SendByPreferencesCommandDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.SendByPreferencesCommand}.
 */
@RestController
@RequestMapping("/api")
public class SendByPreferencesCommandResource {

    private final Logger log = LoggerFactory.getLogger(SendByPreferencesCommandResource.class);

    private static final String ENTITY_NAME = "sendQuestionContextSendByPreferencesCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SendByPreferencesCommandService sendByPreferencesCommandService;

    private final SendByPreferencesCommandRepository sendByPreferencesCommandRepository;

    public SendByPreferencesCommandResource(
        SendByPreferencesCommandService sendByPreferencesCommandService,
        SendByPreferencesCommandRepository sendByPreferencesCommandRepository
    ) {
        this.sendByPreferencesCommandService = sendByPreferencesCommandService;
        this.sendByPreferencesCommandRepository = sendByPreferencesCommandRepository;
    }

    /**
     * {@code POST  /send-by-preferences-commands} : Create a new sendByPreferencesCommand.
     *
     * @param sendByPreferencesCommandDTO the sendByPreferencesCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sendByPreferencesCommandDTO, or with status {@code 400 (Bad Request)} if the sendByPreferencesCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/send-by-preferences-commands")
    public ResponseEntity<SendByPreferencesCommandDTO> createSendByPreferencesCommand(
        @RequestBody SendByPreferencesCommandDTO sendByPreferencesCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SendByPreferencesCommand : {}", sendByPreferencesCommandDTO);
        if (sendByPreferencesCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new sendByPreferencesCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SendByPreferencesCommandDTO result = sendByPreferencesCommandService.save(sendByPreferencesCommandDTO);
        return ResponseEntity
            .created(new URI("/api/send-by-preferences-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /send-by-preferences-commands/:id} : Updates an existing sendByPreferencesCommand.
     *
     * @param id the id of the sendByPreferencesCommandDTO to save.
     * @param sendByPreferencesCommandDTO the sendByPreferencesCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sendByPreferencesCommandDTO,
     * or with status {@code 400 (Bad Request)} if the sendByPreferencesCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sendByPreferencesCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/send-by-preferences-commands/{id}")
    public ResponseEntity<SendByPreferencesCommandDTO> updateSendByPreferencesCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SendByPreferencesCommandDTO sendByPreferencesCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SendByPreferencesCommand : {}, {}", id, sendByPreferencesCommandDTO);
        if (sendByPreferencesCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sendByPreferencesCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sendByPreferencesCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SendByPreferencesCommandDTO result = sendByPreferencesCommandService.update(sendByPreferencesCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sendByPreferencesCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /send-by-preferences-commands/:id} : Partial updates given fields of an existing sendByPreferencesCommand, field will ignore if it is null
     *
     * @param id the id of the sendByPreferencesCommandDTO to save.
     * @param sendByPreferencesCommandDTO the sendByPreferencesCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sendByPreferencesCommandDTO,
     * or with status {@code 400 (Bad Request)} if the sendByPreferencesCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the sendByPreferencesCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the sendByPreferencesCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/send-by-preferences-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SendByPreferencesCommandDTO> partialUpdateSendByPreferencesCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SendByPreferencesCommandDTO sendByPreferencesCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SendByPreferencesCommand partially : {}, {}", id, sendByPreferencesCommandDTO);
        if (sendByPreferencesCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sendByPreferencesCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sendByPreferencesCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SendByPreferencesCommandDTO> result = sendByPreferencesCommandService.partialUpdate(sendByPreferencesCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sendByPreferencesCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /send-by-preferences-commands} : get all the sendByPreferencesCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sendByPreferencesCommands in body.
     */
    @GetMapping("/send-by-preferences-commands")
    public List<SendByPreferencesCommandDTO> getAllSendByPreferencesCommands() {
        log.debug("REST request to get all SendByPreferencesCommands");
        return sendByPreferencesCommandService.findAll();
    }

    /**
     * {@code GET  /send-by-preferences-commands/:id} : get the "id" sendByPreferencesCommand.
     *
     * @param id the id of the sendByPreferencesCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sendByPreferencesCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/send-by-preferences-commands/{id}")
    public ResponseEntity<SendByPreferencesCommandDTO> getSendByPreferencesCommand(@PathVariable Long id) {
        log.debug("REST request to get SendByPreferencesCommand : {}", id);
        Optional<SendByPreferencesCommandDTO> sendByPreferencesCommandDTO = sendByPreferencesCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sendByPreferencesCommandDTO);
    }

    /**
     * {@code DELETE  /send-by-preferences-commands/:id} : delete the "id" sendByPreferencesCommand.
     *
     * @param id the id of the sendByPreferencesCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/send-by-preferences-commands/{id}")
    public ResponseEntity<Void> deleteSendByPreferencesCommand(@PathVariable Long id) {
        log.debug("REST request to delete SendByPreferencesCommand : {}", id);
        sendByPreferencesCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
