package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.UserAndLevelRepository;
import org.contextmapper.generated.evaluationcontext.service.UserAndLevelService;
import org.contextmapper.generated.evaluationcontext.service.dto.UserAndLevelDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.UserAndLevel}.
 */
@RestController
@RequestMapping("/api")
public class UserAndLevelResource {

    private final Logger log = LoggerFactory.getLogger(UserAndLevelResource.class);

    private static final String ENTITY_NAME = "evaluationContextUserAndLevel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserAndLevelService userAndLevelService;

    private final UserAndLevelRepository userAndLevelRepository;

    public UserAndLevelResource(UserAndLevelService userAndLevelService, UserAndLevelRepository userAndLevelRepository) {
        this.userAndLevelService = userAndLevelService;
        this.userAndLevelRepository = userAndLevelRepository;
    }

    /**
     * {@code POST  /user-and-levels} : Create a new userAndLevel.
     *
     * @param userAndLevelDTO the userAndLevelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userAndLevelDTO, or with status {@code 400 (Bad Request)} if the userAndLevel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-and-levels")
    public ResponseEntity<UserAndLevelDTO> createUserAndLevel(@RequestBody UserAndLevelDTO userAndLevelDTO) throws URISyntaxException {
        log.debug("REST request to save UserAndLevel : {}", userAndLevelDTO);
        if (userAndLevelDTO.getId() != null) {
            throw new BadRequestAlertException("A new userAndLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserAndLevelDTO result = userAndLevelService.save(userAndLevelDTO);
        return ResponseEntity
            .created(new URI("/api/user-and-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-and-levels/:id} : Updates an existing userAndLevel.
     *
     * @param id the id of the userAndLevelDTO to save.
     * @param userAndLevelDTO the userAndLevelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userAndLevelDTO,
     * or with status {@code 400 (Bad Request)} if the userAndLevelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userAndLevelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-and-levels/{id}")
    public ResponseEntity<UserAndLevelDTO> updateUserAndLevel(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserAndLevelDTO userAndLevelDTO
    ) throws URISyntaxException {
        log.debug("REST request to update UserAndLevel : {}, {}", id, userAndLevelDTO);
        if (userAndLevelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userAndLevelDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userAndLevelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UserAndLevelDTO result = userAndLevelService.update(userAndLevelDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userAndLevelDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /user-and-levels/:id} : Partial updates given fields of an existing userAndLevel, field will ignore if it is null
     *
     * @param id the id of the userAndLevelDTO to save.
     * @param userAndLevelDTO the userAndLevelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userAndLevelDTO,
     * or with status {@code 400 (Bad Request)} if the userAndLevelDTO is not valid,
     * or with status {@code 404 (Not Found)} if the userAndLevelDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the userAndLevelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/user-and-levels/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UserAndLevelDTO> partialUpdateUserAndLevel(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserAndLevelDTO userAndLevelDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update UserAndLevel partially : {}, {}", id, userAndLevelDTO);
        if (userAndLevelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userAndLevelDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userAndLevelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserAndLevelDTO> result = userAndLevelService.partialUpdate(userAndLevelDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userAndLevelDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /user-and-levels} : get all the userAndLevels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userAndLevels in body.
     */
    @GetMapping("/user-and-levels")
    public List<UserAndLevelDTO> getAllUserAndLevels() {
        log.debug("REST request to get all UserAndLevels");
        return userAndLevelService.findAll();
    }

    /**
     * {@code GET  /user-and-levels/:id} : get the "id" userAndLevel.
     *
     * @param id the id of the userAndLevelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userAndLevelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-and-levels/{id}")
    public ResponseEntity<UserAndLevelDTO> getUserAndLevel(@PathVariable Long id) {
        log.debug("REST request to get UserAndLevel : {}", id);
        Optional<UserAndLevelDTO> userAndLevelDTO = userAndLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userAndLevelDTO);
    }

    /**
     * {@code DELETE  /user-and-levels/:id} : delete the "id" userAndLevel.
     *
     * @param id the id of the userAndLevelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-and-levels/{id}")
    public ResponseEntity<Void> deleteUserAndLevel(@PathVariable Long id) {
        log.debug("REST request to delete UserAndLevel : {}", id);
        userAndLevelService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
