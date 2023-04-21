package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.LeaderBoardRepository;
import org.contextmapper.generated.statcontext.service.LeaderBoardService;
import org.contextmapper.generated.statcontext.service.dto.LeaderBoardDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.LeaderBoard}.
 */
@RestController
@RequestMapping("/api")
public class LeaderBoardResource {

    private final Logger log = LoggerFactory.getLogger(LeaderBoardResource.class);

    private static final String ENTITY_NAME = "statContextLeaderBoard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LeaderBoardService leaderBoardService;

    private final LeaderBoardRepository leaderBoardRepository;

    public LeaderBoardResource(LeaderBoardService leaderBoardService, LeaderBoardRepository leaderBoardRepository) {
        this.leaderBoardService = leaderBoardService;
        this.leaderBoardRepository = leaderBoardRepository;
    }

    /**
     * {@code POST  /leader-boards} : Create a new leaderBoard.
     *
     * @param leaderBoardDTO the leaderBoardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new leaderBoardDTO, or with status {@code 400 (Bad Request)} if the leaderBoard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/leader-boards")
    public ResponseEntity<LeaderBoardDTO> createLeaderBoard(@RequestBody LeaderBoardDTO leaderBoardDTO) throws URISyntaxException {
        log.debug("REST request to save LeaderBoard : {}", leaderBoardDTO);
        if (leaderBoardDTO.getId() != null) {
            throw new BadRequestAlertException("A new leaderBoard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LeaderBoardDTO result = leaderBoardService.save(leaderBoardDTO);
        return ResponseEntity
            .created(new URI("/api/leader-boards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /leader-boards/:id} : Updates an existing leaderBoard.
     *
     * @param id the id of the leaderBoardDTO to save.
     * @param leaderBoardDTO the leaderBoardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leaderBoardDTO,
     * or with status {@code 400 (Bad Request)} if the leaderBoardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the leaderBoardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/leader-boards/{id}")
    public ResponseEntity<LeaderBoardDTO> updateLeaderBoard(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LeaderBoardDTO leaderBoardDTO
    ) throws URISyntaxException {
        log.debug("REST request to update LeaderBoard : {}, {}", id, leaderBoardDTO);
        if (leaderBoardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, leaderBoardDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!leaderBoardRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LeaderBoardDTO result = leaderBoardService.update(leaderBoardDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, leaderBoardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /leader-boards/:id} : Partial updates given fields of an existing leaderBoard, field will ignore if it is null
     *
     * @param id the id of the leaderBoardDTO to save.
     * @param leaderBoardDTO the leaderBoardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leaderBoardDTO,
     * or with status {@code 400 (Bad Request)} if the leaderBoardDTO is not valid,
     * or with status {@code 404 (Not Found)} if the leaderBoardDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the leaderBoardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/leader-boards/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LeaderBoardDTO> partialUpdateLeaderBoard(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LeaderBoardDTO leaderBoardDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update LeaderBoard partially : {}, {}", id, leaderBoardDTO);
        if (leaderBoardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, leaderBoardDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!leaderBoardRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LeaderBoardDTO> result = leaderBoardService.partialUpdate(leaderBoardDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, leaderBoardDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /leader-boards} : get all the leaderBoards.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of leaderBoards in body.
     */
    @GetMapping("/leader-boards")
    public List<LeaderBoardDTO> getAllLeaderBoards() {
        log.debug("REST request to get all LeaderBoards");
        return leaderBoardService.findAll();
    }

    /**
     * {@code GET  /leader-boards/:id} : get the "id" leaderBoard.
     *
     * @param id the id of the leaderBoardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the leaderBoardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/leader-boards/{id}")
    public ResponseEntity<LeaderBoardDTO> getLeaderBoard(@PathVariable Long id) {
        log.debug("REST request to get LeaderBoard : {}", id);
        Optional<LeaderBoardDTO> leaderBoardDTO = leaderBoardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(leaderBoardDTO);
    }

    /**
     * {@code DELETE  /leader-boards/:id} : delete the "id" leaderBoard.
     *
     * @param id the id of the leaderBoardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/leader-boards/{id}")
    public ResponseEntity<Void> deleteLeaderBoard(@PathVariable Long id) {
        log.debug("REST request to delete LeaderBoard : {}", id);
        leaderBoardService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
