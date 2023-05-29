package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.LeaderBoardEntryRepository;
import org.contextmapper.generated.statcontext.service.LeaderBoardEntryService;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardEntryDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.LeaderBoardEntry}.
 */
@RestController
@RequestMapping("/api")
public class LeaderBoardEntryResource {

    private final Logger log = LoggerFactory.getLogger(LeaderBoardEntryResource.class);

    private static final String ENTITY_NAME = "statContextLeaderBoardEntry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LeaderBoardEntryService leaderBoardEntryService;

    private final LeaderBoardEntryRepository leaderBoardEntryRepository;

    public LeaderBoardEntryResource(
        LeaderBoardEntryService leaderBoardEntryService,
        LeaderBoardEntryRepository leaderBoardEntryRepository
    ) {
        this.leaderBoardEntryService = leaderBoardEntryService;
        this.leaderBoardEntryRepository = leaderBoardEntryRepository;
    }

    /**
     * {@code POST  /leader-board-entries} : Create a new leaderBoardEntry.
     *
     * @param leaderBoardEntryDTO the leaderBoardEntryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new leaderBoardEntryDTO, or with status {@code 400 (Bad Request)} if the leaderBoardEntry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/leader-board-entries")
    public ResponseEntity<LeaderBoardEntryDTO> createLeaderBoardEntry(@RequestBody LeaderBoardEntryDTO leaderBoardEntryDTO)
        throws URISyntaxException {
        log.debug("REST request to save LeaderBoardEntry : {}", leaderBoardEntryDTO);
        if (leaderBoardEntryDTO.getId() != null) {
            throw new BadRequestAlertException("A new leaderBoardEntry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LeaderBoardEntryDTO result = leaderBoardEntryService.save(leaderBoardEntryDTO);
        return ResponseEntity
            .created(new URI("/api/leader-board-entries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /leader-board-entries/:id} : Updates an existing leaderBoardEntry.
     *
     * @param id the id of the leaderBoardEntryDTO to save.
     * @param leaderBoardEntryDTO the leaderBoardEntryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leaderBoardEntryDTO,
     * or with status {@code 400 (Bad Request)} if the leaderBoardEntryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the leaderBoardEntryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/leader-board-entries/{id}")
    public ResponseEntity<LeaderBoardEntryDTO> updateLeaderBoardEntry(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LeaderBoardEntryDTO leaderBoardEntryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update LeaderBoardEntry : {}, {}", id, leaderBoardEntryDTO);
        if (leaderBoardEntryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, leaderBoardEntryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!leaderBoardEntryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LeaderBoardEntryDTO result = leaderBoardEntryService.update(leaderBoardEntryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, leaderBoardEntryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /leader-board-entries/:id} : Partial updates given fields of an existing leaderBoardEntry, field will ignore if it is null
     *
     * @param id the id of the leaderBoardEntryDTO to save.
     * @param leaderBoardEntryDTO the leaderBoardEntryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leaderBoardEntryDTO,
     * or with status {@code 400 (Bad Request)} if the leaderBoardEntryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the leaderBoardEntryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the leaderBoardEntryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/leader-board-entries/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LeaderBoardEntryDTO> partialUpdateLeaderBoardEntry(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LeaderBoardEntryDTO leaderBoardEntryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update LeaderBoardEntry partially : {}, {}", id, leaderBoardEntryDTO);
        if (leaderBoardEntryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, leaderBoardEntryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!leaderBoardEntryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LeaderBoardEntryDTO> result = leaderBoardEntryService.partialUpdate(leaderBoardEntryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, leaderBoardEntryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /leader-board-entries} : get all the leaderBoardEntries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of leaderBoardEntries in body.
     */
    @GetMapping("/leader-board-entries")
    public List<LeaderBoardEntryDTO> getAllLeaderBoardEntries() {
        log.debug("REST request to get all LeaderBoardEntries");
        return leaderBoardEntryService.findAll();
    }

    /**
     * {@code GET  /leader-board-entries/:id} : get the "id" leaderBoardEntry.
     *
     * @param id the id of the leaderBoardEntryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the leaderBoardEntryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/leader-board-entries/{id}")
    public ResponseEntity<LeaderBoardEntryDTO> getLeaderBoardEntry(@PathVariable Long id) {
        log.debug("REST request to get LeaderBoardEntry : {}", id);
        Optional<LeaderBoardEntryDTO> leaderBoardEntryDTO = leaderBoardEntryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(leaderBoardEntryDTO);
    }

    /**
     * {@code DELETE  /leader-board-entries/:id} : delete the "id" leaderBoardEntry.
     *
     * @param id the id of the leaderBoardEntryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/leader-board-entries/{id}")
    public ResponseEntity<Void> deleteLeaderBoardEntry(@PathVariable Long id) {
        log.debug("REST request to delete LeaderBoardEntry : {}", id);
        leaderBoardEntryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
