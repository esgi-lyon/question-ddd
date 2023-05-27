package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.AddPreferencesCommandRepository;
import org.contextmapper.generated.sendquestioncontext.service.AddPreferencesCommandService;
import org.contextmapper.generated.sendquestioncontext.service.dto.AddPreferencesCommandDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.AddPreferencesCommand}.
 */
@RestController
@RequestMapping("/api")
public class AddPreferencesCommandResource {

    private final Logger log = LoggerFactory.getLogger(AddPreferencesCommandResource.class);

    private static final String ENTITY_NAME = "sendQuestionContextAddPreferencesCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AddPreferencesCommandService addPreferencesCommandService;

    private final AddPreferencesCommandRepository addPreferencesCommandRepository;

    public AddPreferencesCommandResource(
        AddPreferencesCommandService addPreferencesCommandService,
        AddPreferencesCommandRepository addPreferencesCommandRepository
    ) {
        this.addPreferencesCommandService = addPreferencesCommandService;
        this.addPreferencesCommandRepository = addPreferencesCommandRepository;
    }

    /**
     * {@code POST  /add-preferences-commands} : Create a new addPreferencesCommand.
     *
     * @param addPreferencesCommandDTO the addPreferencesCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new addPreferencesCommandDTO, or with status {@code 400 (Bad Request)} if the addPreferencesCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/add-preferences-commands")
    public ResponseEntity<AddPreferencesCommandDTO> createAddPreferencesCommand(
        @RequestBody AddPreferencesCommandDTO addPreferencesCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to save AddPreferencesCommand : {}", addPreferencesCommandDTO);
        if (addPreferencesCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new addPreferencesCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AddPreferencesCommandDTO result = addPreferencesCommandService.save(addPreferencesCommandDTO);
        return ResponseEntity
            .created(new URI("/api/add-preferences-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /add-preferences-commands/:id} : Updates an existing addPreferencesCommand.
     *
     * @param id the id of the addPreferencesCommandDTO to save.
     * @param addPreferencesCommandDTO the addPreferencesCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated addPreferencesCommandDTO,
     * or with status {@code 400 (Bad Request)} if the addPreferencesCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the addPreferencesCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/add-preferences-commands/{id}")
    public ResponseEntity<AddPreferencesCommandDTO> updateAddPreferencesCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AddPreferencesCommandDTO addPreferencesCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AddPreferencesCommand : {}, {}", id, addPreferencesCommandDTO);
        if (addPreferencesCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, addPreferencesCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!addPreferencesCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AddPreferencesCommandDTO result = addPreferencesCommandService.update(addPreferencesCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, addPreferencesCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /add-preferences-commands/:id} : Partial updates given fields of an existing addPreferencesCommand, field will ignore if it is null
     *
     * @param id the id of the addPreferencesCommandDTO to save.
     * @param addPreferencesCommandDTO the addPreferencesCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated addPreferencesCommandDTO,
     * or with status {@code 400 (Bad Request)} if the addPreferencesCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the addPreferencesCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the addPreferencesCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/add-preferences-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AddPreferencesCommandDTO> partialUpdateAddPreferencesCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AddPreferencesCommandDTO addPreferencesCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AddPreferencesCommand partially : {}, {}", id, addPreferencesCommandDTO);
        if (addPreferencesCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, addPreferencesCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!addPreferencesCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AddPreferencesCommandDTO> result = addPreferencesCommandService.partialUpdate(addPreferencesCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, addPreferencesCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /add-preferences-commands} : get all the addPreferencesCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of addPreferencesCommands in body.
     */
    @GetMapping("/add-preferences-commands")
    public List<AddPreferencesCommandDTO> getAllAddPreferencesCommands() {
        log.debug("REST request to get all AddPreferencesCommands");
        return addPreferencesCommandService.findAll();
    }

    /**
     * {@code GET  /add-preferences-commands/:id} : get the "id" addPreferencesCommand.
     *
     * @param id the id of the addPreferencesCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the addPreferencesCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/add-preferences-commands/{id}")
    public ResponseEntity<AddPreferencesCommandDTO> getAddPreferencesCommand(@PathVariable Long id) {
        log.debug("REST request to get AddPreferencesCommand : {}", id);
        Optional<AddPreferencesCommandDTO> addPreferencesCommandDTO = addPreferencesCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(addPreferencesCommandDTO);
    }

    /**
     * {@code DELETE  /add-preferences-commands/:id} : delete the "id" addPreferencesCommand.
     *
     * @param id the id of the addPreferencesCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/add-preferences-commands/{id}")
    public ResponseEntity<Void> deleteAddPreferencesCommand(@PathVariable Long id) {
        log.debug("REST request to delete AddPreferencesCommand : {}", id);
        addPreferencesCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
