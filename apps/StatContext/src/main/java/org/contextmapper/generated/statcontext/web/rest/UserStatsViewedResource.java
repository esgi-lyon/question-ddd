package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.UserStatsViewedRepository;
import org.contextmapper.generated.statcontext.service.UserStatsViewedService;
import org.contextmapper.generated.statcontext.service.dto.UserStatsViewedDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.UserStatsViewed}.
 */
@RestController
@RequestMapping("/api")
public class UserStatsViewedResource {

    private final Logger log = LoggerFactory.getLogger(UserStatsViewedResource.class);

    private static final String ENTITY_NAME = "statContextUserStatsViewed";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserStatsViewedService userStatsViewedService;

    private final UserStatsViewedRepository userStatsViewedRepository;

    public UserStatsViewedResource(UserStatsViewedService userStatsViewedService, UserStatsViewedRepository userStatsViewedRepository) {
        this.userStatsViewedService = userStatsViewedService;
        this.userStatsViewedRepository = userStatsViewedRepository;
    }

    /**
     * {@code POST  /user-stats-vieweds} : Create a new userStatsViewed.
     *
     * @param userStatsViewedDTO the userStatsViewedDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userStatsViewedDTO, or with status {@code 400 (Bad Request)} if the userStatsViewed has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-stats-vieweds")
    public ResponseEntity<UserStatsViewedDTO> createUserStatsViewed(@RequestBody UserStatsViewedDTO userStatsViewedDTO)
        throws URISyntaxException {
        log.debug("REST request to save UserStatsViewed : {}", userStatsViewedDTO);
        if (userStatsViewedDTO.getId() != null) {
            throw new BadRequestAlertException("A new userStatsViewed cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserStatsViewedDTO result = userStatsViewedService.save(userStatsViewedDTO);
        return ResponseEntity
            .created(new URI("/api/user-stats-vieweds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-stats-vieweds/:id} : Updates an existing userStatsViewed.
     *
     * @param id the id of the userStatsViewedDTO to save.
     * @param userStatsViewedDTO the userStatsViewedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userStatsViewedDTO,
     * or with status {@code 400 (Bad Request)} if the userStatsViewedDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userStatsViewedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-stats-vieweds/{id}")
    public ResponseEntity<UserStatsViewedDTO> updateUserStatsViewed(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserStatsViewedDTO userStatsViewedDTO
    ) throws URISyntaxException {
        log.debug("REST request to update UserStatsViewed : {}, {}", id, userStatsViewedDTO);
        if (userStatsViewedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userStatsViewedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userStatsViewedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UserStatsViewedDTO result = userStatsViewedService.update(userStatsViewedDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userStatsViewedDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /user-stats-vieweds/:id} : Partial updates given fields of an existing userStatsViewed, field will ignore if it is null
     *
     * @param id the id of the userStatsViewedDTO to save.
     * @param userStatsViewedDTO the userStatsViewedDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userStatsViewedDTO,
     * or with status {@code 400 (Bad Request)} if the userStatsViewedDTO is not valid,
     * or with status {@code 404 (Not Found)} if the userStatsViewedDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the userStatsViewedDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/user-stats-vieweds/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UserStatsViewedDTO> partialUpdateUserStatsViewed(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserStatsViewedDTO userStatsViewedDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update UserStatsViewed partially : {}, {}", id, userStatsViewedDTO);
        if (userStatsViewedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userStatsViewedDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userStatsViewedRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserStatsViewedDTO> result = userStatsViewedService.partialUpdate(userStatsViewedDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userStatsViewedDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /user-stats-vieweds} : get all the userStatsVieweds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userStatsVieweds in body.
     */
    @GetMapping("/user-stats-vieweds")
    public List<UserStatsViewedDTO> getAllUserStatsVieweds() {
        log.debug("REST request to get all UserStatsVieweds");
        return userStatsViewedService.findAll();
    }

    /**
     * {@code GET  /user-stats-vieweds/:id} : get the "id" userStatsViewed.
     *
     * @param id the id of the userStatsViewedDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userStatsViewedDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-stats-vieweds/{id}")
    public ResponseEntity<UserStatsViewedDTO> getUserStatsViewed(@PathVariable Long id) {
        log.debug("REST request to get UserStatsViewed : {}", id);
        Optional<UserStatsViewedDTO> userStatsViewedDTO = userStatsViewedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userStatsViewedDTO);
    }

    /**
     * {@code DELETE  /user-stats-vieweds/:id} : delete the "id" userStatsViewed.
     *
     * @param id the id of the userStatsViewedDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-stats-vieweds/{id}")
    public ResponseEntity<Void> deleteUserStatsViewed(@PathVariable Long id) {
        log.debug("REST request to delete UserStatsViewed : {}", id);
        userStatsViewedService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
