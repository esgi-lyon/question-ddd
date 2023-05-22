package org.contextmapper.generated.skillcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.skillcontext.repository.CreatedByIdRepository;
import org.contextmapper.generated.skillcontext.service.CreatedByIdService;
import org.contextmapper.generated.skillcontext.service.dto.CreatedByIdDTO;
import org.contextmapper.generated.skillcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.skillcontext.domain.CreatedById}.
 */
@RestController
@RequestMapping("/api")
public class CreatedByIdResource {

    private final Logger log = LoggerFactory.getLogger(CreatedByIdResource.class);

    private final CreatedByIdService createdByIdService;

    private final CreatedByIdRepository createdByIdRepository;

    public CreatedByIdResource(CreatedByIdService createdByIdService, CreatedByIdRepository createdByIdRepository) {
        this.createdByIdService = createdByIdService;
        this.createdByIdRepository = createdByIdRepository;
    }

    /**
     * {@code GET  /created-by-ids} : get all the createdByIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of createdByIds in body.
     */
    @GetMapping("/created-by-ids")
    public List<CreatedByIdDTO> getAllCreatedByIds() {
        log.debug("REST request to get all CreatedByIds");
        return createdByIdService.findAll();
    }

    /**
     * {@code GET  /created-by-ids/:id} : get the "id" createdById.
     *
     * @param id the id of the createdByIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the createdByIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/created-by-ids/{id}")
    public ResponseEntity<CreatedByIdDTO> getCreatedById(@PathVariable Long id) {
        log.debug("REST request to get CreatedById : {}", id);
        Optional<CreatedByIdDTO> createdByIdDTO = createdByIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(createdByIdDTO);
    }
}
