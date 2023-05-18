package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.repository.RejectResourceTagRepository;
import org.contextmapper.generated.questioncontext.service.RejectResourceTagService;
import org.contextmapper.generated.questioncontext.service.dto.RejectResourceTagDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.RejectResourceTag}.
 */
@RestController
@RequestMapping("/api")
public class RejectResourceTagResource {

    private final Logger log = LoggerFactory.getLogger(RejectResourceTagResource.class);

    private static final String ENTITY_NAME = "questionContextRejectResourceTag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RejectResourceTagService rejectResourceTagService;

    private final RejectResourceTagRepository rejectResourceTagRepository;

    public RejectResourceTagResource(
        RejectResourceTagService rejectResourceTagService,
        RejectResourceTagRepository rejectResourceTagRepository
    ) {
        this.rejectResourceTagService = rejectResourceTagService;
        this.rejectResourceTagRepository = rejectResourceTagRepository;
    }

    /**
     * {@code POST  /reject-resource-tags} : Create a new rejectResourceTag.
     *
     * @param rejectResourceTagDTO the rejectResourceTagDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rejectResourceTagDTO, or with status {@code 400 (Bad Request)} if the rejectResourceTag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reject-resource-tags")
    public ResponseEntity<RejectResourceTagDTO> createRejectResourceTag(@RequestBody RejectResourceTagDTO rejectResourceTagDTO)
        throws URISyntaxException {
        log.debug("REST request to save RejectResourceTag : {}", rejectResourceTagDTO);
        if (rejectResourceTagDTO.getId() != null) {
            throw new BadRequestAlertException("A new rejectResourceTag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RejectResourceTagDTO result = rejectResourceTagService.save(rejectResourceTagDTO);
        return ResponseEntity
            .created(new URI("/api/reject-resource-tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reject-resource-tags/:id} : Updates an existing rejectResourceTag.
     *
     * @param id the id of the rejectResourceTagDTO to save.
     * @param rejectResourceTagDTO the rejectResourceTagDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rejectResourceTagDTO,
     * or with status {@code 400 (Bad Request)} if the rejectResourceTagDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rejectResourceTagDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reject-resource-tags/{id}")
    public ResponseEntity<RejectResourceTagDTO> updateRejectResourceTag(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RejectResourceTagDTO rejectResourceTagDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RejectResourceTag : {}, {}", id, rejectResourceTagDTO);
        if (rejectResourceTagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rejectResourceTagDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rejectResourceTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RejectResourceTagDTO result = rejectResourceTagService.update(rejectResourceTagDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rejectResourceTagDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /reject-resource-tags/:id} : Partial updates given fields of an existing rejectResourceTag, field will ignore if it is null
     *
     * @param id the id of the rejectResourceTagDTO to save.
     * @param rejectResourceTagDTO the rejectResourceTagDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rejectResourceTagDTO,
     * or with status {@code 400 (Bad Request)} if the rejectResourceTagDTO is not valid,
     * or with status {@code 404 (Not Found)} if the rejectResourceTagDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the rejectResourceTagDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/reject-resource-tags/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RejectResourceTagDTO> partialUpdateRejectResourceTag(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RejectResourceTagDTO rejectResourceTagDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RejectResourceTag partially : {}, {}", id, rejectResourceTagDTO);
        if (rejectResourceTagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rejectResourceTagDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rejectResourceTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RejectResourceTagDTO> result = rejectResourceTagService.partialUpdate(rejectResourceTagDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rejectResourceTagDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /reject-resource-tags} : get all the rejectResourceTags.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rejectResourceTags in body.
     */
    @GetMapping("/reject-resource-tags")
    public List<RejectResourceTagDTO> getAllRejectResourceTags() {
        log.debug("REST request to get all RejectResourceTags");
        return rejectResourceTagService.findAll();
    }

    /**
     * {@code GET  /reject-resource-tags/:id} : get the "id" rejectResourceTag.
     *
     * @param id the id of the rejectResourceTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rejectResourceTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reject-resource-tags/{id}")
    public ResponseEntity<RejectResourceTagDTO> getRejectResourceTag(@PathVariable Long id) {
        log.debug("REST request to get RejectResourceTag : {}", id);
        Optional<RejectResourceTagDTO> rejectResourceTagDTO = rejectResourceTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rejectResourceTagDTO);
    }

    /**
     * {@code DELETE  /reject-resource-tags/:id} : delete the "id" rejectResourceTag.
     *
     * @param id the id of the rejectResourceTagDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reject-resource-tags/{id}")
    public ResponseEntity<Void> deleteRejectResourceTag(@PathVariable Long id) {
        log.debug("REST request to delete RejectResourceTag : {}", id);
        rejectResourceTagService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
