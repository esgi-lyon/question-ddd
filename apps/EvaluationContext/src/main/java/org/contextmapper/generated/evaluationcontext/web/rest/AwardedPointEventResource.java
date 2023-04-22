package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.AwardedPointEventRepository;
import org.contextmapper.generated.evaluationcontext.service.AwardedPointEventService;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardedPointEventDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.AwardedPointEvent}.
 */
@RestController
@RequestMapping("/api")
public class AwardedPointEventResource {

    private final Logger log = LoggerFactory.getLogger(AwardedPointEventResource.class);

    private final AwardedPointEventService awardedPointEventService;

    private final AwardedPointEventRepository awardedPointEventRepository;

    public AwardedPointEventResource(
        AwardedPointEventService awardedPointEventService,
        AwardedPointEventRepository awardedPointEventRepository
    ) {
        this.awardedPointEventService = awardedPointEventService;
        this.awardedPointEventRepository = awardedPointEventRepository;
    }

    /**
     * {@code GET  /awarded-point-events} : get all the awardedPointEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of awardedPointEvents in body.
     */
    @GetMapping("/awarded-point-events")
    public List<AwardedPointEventDTO> getAllAwardedPointEvents() {
        log.debug("REST request to get all AwardedPointEvents");
        return awardedPointEventService.findAll();
    }

    /**
     * {@code GET  /awarded-point-events/:id} : get the "id" awardedPointEvent.
     *
     * @param id the id of the awardedPointEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the awardedPointEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/awarded-point-events/{id}")
    public ResponseEntity<AwardedPointEventDTO> getAwardedPointEvent(@PathVariable Long id) {
        log.debug("REST request to get AwardedPointEvent : {}", id);
        Optional<AwardedPointEventDTO> awardedPointEventDTO = awardedPointEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(awardedPointEventDTO);
    }
}
