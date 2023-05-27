package org.contextmapper.generated.usermanagementcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.usermanagementcontext.repository.UserViewedEventRepository;
import org.contextmapper.generated.usermanagementcontext.service.UserViewedEventService;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserViewedEventDTO;
import org.contextmapper.generated.usermanagementcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.usermanagementcontext.domain.UserViewedEvent}.
 */
@RestController
@RequestMapping("/api")
public class UserViewedEventResource {

    private final Logger log = LoggerFactory.getLogger(UserViewedEventResource.class);

    private final UserViewedEventService userViewedEventService;

    private final UserViewedEventRepository userViewedEventRepository;

    public UserViewedEventResource(UserViewedEventService userViewedEventService, UserViewedEventRepository userViewedEventRepository) {
        this.userViewedEventService = userViewedEventService;
        this.userViewedEventRepository = userViewedEventRepository;
    }

    /**
     * {@code GET  /user-viewed-events} : get all the userViewedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userViewedEvents in body.
     */
    @GetMapping("/user-viewed-events")
    public List<UserViewedEventDTO> getAllUserViewedEvents() {
        log.debug("REST request to get all UserViewedEvents");
        return userViewedEventService.findAll();
    }

    /**
     * {@code GET  /user-viewed-events/:id} : get the "id" userViewedEvent.
     *
     * @param id the id of the userViewedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userViewedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-viewed-events/{id}")
    public ResponseEntity<UserViewedEventDTO> getUserViewedEvent(@PathVariable Long id) {
        log.debug("REST request to get UserViewedEvent : {}", id);
        Optional<UserViewedEventDTO> userViewedEventDTO = userViewedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userViewedEventDTO);
    }
}
