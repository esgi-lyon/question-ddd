package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.ResourceIdRepository;
import org.contextmapper.generated.sendquestioncontext.service.ResourceIdService;
import org.contextmapper.generated.sendquestioncontext.service.dto.ResourceIdDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.ResourceId}.
 */
@RestController
@RequestMapping("/api")
public class ResourceIdResource {

    private final Logger log = LoggerFactory.getLogger(ResourceIdResource.class);

    private final ResourceIdService resourceIdService;

    private final ResourceIdRepository resourceIdRepository;

    public ResourceIdResource(ResourceIdService resourceIdService, ResourceIdRepository resourceIdRepository) {
        this.resourceIdService = resourceIdService;
        this.resourceIdRepository = resourceIdRepository;
    }

    /**
     * {@code GET  /resource-ids} : get all the resourceIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resourceIds in body.
     */
    @GetMapping("/resource-ids")
    public List<ResourceIdDTO> getAllResourceIds() {
        log.debug("REST request to get all ResourceIds");
        return resourceIdService.findAll();
    }

    /**
     * {@code GET  /resource-ids/:id} : get the "id" resourceId.
     *
     * @param id the id of the resourceIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourceIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resource-ids/{id}")
    public ResponseEntity<ResourceIdDTO> getResourceId(@PathVariable Long id) {
        log.debug("REST request to get ResourceId : {}", id);
        Optional<ResourceIdDTO> resourceIdDTO = resourceIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resourceIdDTO);
    }
}
