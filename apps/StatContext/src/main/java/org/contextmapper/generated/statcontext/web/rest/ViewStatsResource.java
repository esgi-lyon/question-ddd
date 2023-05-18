package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.ViewStatsRepository;
import org.contextmapper.generated.statcontext.service.ViewStatsService;
import org.contextmapper.generated.statcontext.service.dto.ViewStatsDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.ViewStats}.
 */
@RestController
@RequestMapping("/api")
public class ViewStatsResource {

    private final Logger log = LoggerFactory.getLogger(ViewStatsResource.class);

    private static final String ENTITY_NAME = "statContextViewStats";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ViewStatsService viewStatsService;

    private final ViewStatsRepository viewStatsRepository;

    public ViewStatsResource(ViewStatsService viewStatsService, ViewStatsRepository viewStatsRepository) {
        this.viewStatsService = viewStatsService;
        this.viewStatsRepository = viewStatsRepository;
    }

    /**
     * {@code POST  /view-stats} : Create a new viewStats.
     *
     * @param viewStatsDTO the viewStatsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new viewStatsDTO, or with status {@code 400 (Bad Request)} if the viewStats has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/view-stats")
    public ResponseEntity<ViewStatsDTO> createViewStats(@RequestBody ViewStatsDTO viewStatsDTO) throws URISyntaxException {
        log.debug("REST request to save ViewStats : {}", viewStatsDTO);
        if (viewStatsDTO.getId() != null) {
            throw new BadRequestAlertException("A new viewStats cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ViewStatsDTO result = viewStatsService.save(viewStatsDTO);
        return ResponseEntity
            .created(new URI("/api/view-stats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /view-stats/:id} : Updates an existing viewStats.
     *
     * @param id the id of the viewStatsDTO to save.
     * @param viewStatsDTO the viewStatsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewStatsDTO,
     * or with status {@code 400 (Bad Request)} if the viewStatsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the viewStatsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/view-stats/{id}")
    public ResponseEntity<ViewStatsDTO> updateViewStats(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewStatsDTO viewStatsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ViewStats : {}, {}", id, viewStatsDTO);
        if (viewStatsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewStatsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewStatsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ViewStatsDTO result = viewStatsService.update(viewStatsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewStatsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /view-stats/:id} : Partial updates given fields of an existing viewStats, field will ignore if it is null
     *
     * @param id the id of the viewStatsDTO to save.
     * @param viewStatsDTO the viewStatsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewStatsDTO,
     * or with status {@code 400 (Bad Request)} if the viewStatsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the viewStatsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the viewStatsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/view-stats/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ViewStatsDTO> partialUpdateViewStats(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewStatsDTO viewStatsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ViewStats partially : {}, {}", id, viewStatsDTO);
        if (viewStatsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewStatsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewStatsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ViewStatsDTO> result = viewStatsService.partialUpdate(viewStatsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewStatsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /view-stats} : get all the viewStats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viewStats in body.
     */
    @GetMapping("/view-stats")
    public List<ViewStatsDTO> getAllViewStats() {
        log.debug("REST request to get all ViewStats");
        return viewStatsService.findAll();
    }

    /**
     * {@code GET  /view-stats/:id} : get the "id" viewStats.
     *
     * @param id the id of the viewStatsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viewStatsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/view-stats/{id}")
    public ResponseEntity<ViewStatsDTO> getViewStats(@PathVariable Long id) {
        log.debug("REST request to get ViewStats : {}", id);
        Optional<ViewStatsDTO> viewStatsDTO = viewStatsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(viewStatsDTO);
    }

    /**
     * {@code DELETE  /view-stats/:id} : delete the "id" viewStats.
     *
     * @param id the id of the viewStatsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/view-stats/{id}")
    public ResponseEntity<Void> deleteViewStats(@PathVariable Long id) {
        log.debug("REST request to delete ViewStats : {}", id);
        viewStatsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
