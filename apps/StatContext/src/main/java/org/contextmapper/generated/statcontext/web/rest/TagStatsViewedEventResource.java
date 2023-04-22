package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.TagStatsViewedEventRepository;
import org.contextmapper.generated.statcontext.service.TagStatsViewedEventService;
import org.contextmapper.generated.statcontext.service.dto.TagStatsViewedEventDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.TagStatsViewedEvent}.
 */
@RestController
@RequestMapping("/api")
public class TagStatsViewedEventResource {

    private final Logger log = LoggerFactory.getLogger(TagStatsViewedEventResource.class);

    private final TagStatsViewedEventService tagStatsViewedEventService;

    private final TagStatsViewedEventRepository tagStatsViewedEventRepository;

    public TagStatsViewedEventResource(
        TagStatsViewedEventService tagStatsViewedEventService,
        TagStatsViewedEventRepository tagStatsViewedEventRepository
    ) {
        this.tagStatsViewedEventService = tagStatsViewedEventService;
        this.tagStatsViewedEventRepository = tagStatsViewedEventRepository;
    }

    /**
     * {@code GET  /tag-stats-viewed-events} : get all the tagStatsViewedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagStatsViewedEvents in body.
     */
    @GetMapping("/tag-stats-viewed-events")
    public List<TagStatsViewedEventDTO> getAllTagStatsViewedEvents() {
        log.debug("REST request to get all TagStatsViewedEvents");
        return tagStatsViewedEventService.findAll();
    }

    /**
     * {@code GET  /tag-stats-viewed-events/:id} : get the "id" tagStatsViewedEvent.
     *
     * @param id the id of the tagStatsViewedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagStatsViewedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-stats-viewed-events/{id}")
    public ResponseEntity<TagStatsViewedEventDTO> getTagStatsViewedEvent(@PathVariable Long id) {
        log.debug("REST request to get TagStatsViewedEvent : {}", id);
        Optional<TagStatsViewedEventDTO> tagStatsViewedEventDTO = tagStatsViewedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagStatsViewedEventDTO);
    }
}
