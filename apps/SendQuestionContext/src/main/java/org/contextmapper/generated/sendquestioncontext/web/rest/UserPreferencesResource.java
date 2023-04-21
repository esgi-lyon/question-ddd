package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.UserPreferencesRepository;
import org.contextmapper.generated.sendquestioncontext.service.UserPreferencesService;
import org.contextmapper.generated.sendquestioncontext.service.dto.UserPreferencesDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.UserPreferences}.
 */
@RestController
@RequestMapping("/api")
public class UserPreferencesResource {

    private final Logger log = LoggerFactory.getLogger(UserPreferencesResource.class);

    private static final String ENTITY_NAME = "sendQuestionContextUserPreferences";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserPreferencesService userPreferencesService;

    private final UserPreferencesRepository userPreferencesRepository;

    public UserPreferencesResource(UserPreferencesService userPreferencesService, UserPreferencesRepository userPreferencesRepository) {
        this.userPreferencesService = userPreferencesService;
        this.userPreferencesRepository = userPreferencesRepository;
    }

    /**
     * {@code POST  /user-preferences} : Create a new userPreferences.
     *
     * @param userPreferencesDTO the userPreferencesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userPreferencesDTO, or with status {@code 400 (Bad Request)} if the userPreferences has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-preferences")
    public ResponseEntity<UserPreferencesDTO> createUserPreferences(@RequestBody UserPreferencesDTO userPreferencesDTO)
        throws URISyntaxException {
        log.debug("REST request to save UserPreferences : {}", userPreferencesDTO);
        if (userPreferencesDTO.getId() != null) {
            throw new BadRequestAlertException("A new userPreferences cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserPreferencesDTO result = userPreferencesService.save(userPreferencesDTO);
        return ResponseEntity
            .created(new URI("/api/user-preferences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-preferences/:id} : Updates an existing userPreferences.
     *
     * @param id the id of the userPreferencesDTO to save.
     * @param userPreferencesDTO the userPreferencesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userPreferencesDTO,
     * or with status {@code 400 (Bad Request)} if the userPreferencesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userPreferencesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-preferences/{id}")
    public ResponseEntity<UserPreferencesDTO> updateUserPreferences(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserPreferencesDTO userPreferencesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update UserPreferences : {}, {}", id, userPreferencesDTO);
        if (userPreferencesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userPreferencesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userPreferencesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UserPreferencesDTO result = userPreferencesService.update(userPreferencesDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userPreferencesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /user-preferences/:id} : Partial updates given fields of an existing userPreferences, field will ignore if it is null
     *
     * @param id the id of the userPreferencesDTO to save.
     * @param userPreferencesDTO the userPreferencesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userPreferencesDTO,
     * or with status {@code 400 (Bad Request)} if the userPreferencesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the userPreferencesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the userPreferencesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/user-preferences/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UserPreferencesDTO> partialUpdateUserPreferences(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserPreferencesDTO userPreferencesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update UserPreferences partially : {}, {}", id, userPreferencesDTO);
        if (userPreferencesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userPreferencesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userPreferencesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserPreferencesDTO> result = userPreferencesService.partialUpdate(userPreferencesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userPreferencesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /user-preferences} : get all the userPreferences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userPreferences in body.
     */
    @GetMapping("/user-preferences")
    public List<UserPreferencesDTO> getAllUserPreferences() {
        log.debug("REST request to get all UserPreferences");
        return userPreferencesService.findAll();
    }

    /**
     * {@code GET  /user-preferences/:id} : get the "id" userPreferences.
     *
     * @param id the id of the userPreferencesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userPreferencesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-preferences/{id}")
    public ResponseEntity<UserPreferencesDTO> getUserPreferences(@PathVariable Long id) {
        log.debug("REST request to get UserPreferences : {}", id);
        Optional<UserPreferencesDTO> userPreferencesDTO = userPreferencesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userPreferencesDTO);
    }

    /**
     * {@code DELETE  /user-preferences/:id} : delete the "id" userPreferences.
     *
     * @param id the id of the userPreferencesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-preferences/{id}")
    public ResponseEntity<Void> deleteUserPreferences(@PathVariable Long id) {
        log.debug("REST request to delete UserPreferences : {}", id);
        userPreferencesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
