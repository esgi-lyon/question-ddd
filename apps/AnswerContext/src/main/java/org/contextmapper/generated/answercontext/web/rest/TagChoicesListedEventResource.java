package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.TagChoicesListedEventRepository;
import org.contextmapper.generated.answercontext.service.TagChoicesListedEventService;
import org.contextmapper.generated.answercontext.service.dto.TagChoicesListedEventDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.TagChoicesListedEvent}.
 */
@RestController
@RequestMapping("/api")
public class TagChoicesListedEventResource {

    private final Logger log = LoggerFactory.getLogger(TagChoicesListedEventResource.class);

    private final TagChoicesListedEventService tagChoicesListedEventService;

    private final TagChoicesListedEventRepository tagChoicesListedEventRepository;

    public TagChoicesListedEventResource(
        TagChoicesListedEventService tagChoicesListedEventService,
        TagChoicesListedEventRepository tagChoicesListedEventRepository
    ) {
        this.tagChoicesListedEventService = tagChoicesListedEventService;
        this.tagChoicesListedEventRepository = tagChoicesListedEventRepository;
    }

    /**
     * {@code GET  /tag-choices-listed-events} : get all the tagChoicesListedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagChoicesListedEvents in body.
     */
    @GetMapping("/tag-choices-listed-events")
    public List<TagChoicesListedEventDTO> getAllTagChoicesListedEvents() {
        log.debug("REST request to get all TagChoicesListedEvents");
        return tagChoicesListedEventService.findAll();
    }

    /**
     * {@code GET  /tag-choices-listed-events/:id} : get the "id" tagChoicesListedEvent.
     *
     * @param id the id of the tagChoicesListedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagChoicesListedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-choices-listed-events/{id}")
    public ResponseEntity<TagChoicesListedEventDTO> getTagChoicesListedEvent(@PathVariable Long id) {
        log.debug("REST request to get TagChoicesListedEvent : {}", id);
        Optional<TagChoicesListedEventDTO> tagChoicesListedEventDTO = tagChoicesListedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagChoicesListedEventDTO);
    }
}
