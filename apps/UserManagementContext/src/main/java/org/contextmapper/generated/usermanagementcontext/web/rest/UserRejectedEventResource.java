package org.contextmapper.generated.usermanagementcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.usermanagementcontext.repository.UserRejectedEventRepository;
import org.contextmapper.generated.usermanagementcontext.service.UserRejectedEventService;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserRejectedEventDTO;
import org.contextmapper.generated.usermanagementcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.usermanagementcontext.domain.UserRejectedEvent}.
 */
@RestController
@RequestMapping("/api")
public class UserRejectedEventResource {

    private final Logger log = LoggerFactory.getLogger(UserRejectedEventResource.class);

    private final UserRejectedEventService userRejectedEventService;

    private final UserRejectedEventRepository userRejectedEventRepository;

    public UserRejectedEventResource(
        UserRejectedEventService userRejectedEventService,
        UserRejectedEventRepository userRejectedEventRepository
    ) {
        this.userRejectedEventService = userRejectedEventService;
        this.userRejectedEventRepository = userRejectedEventRepository;
    }

    /**
     * {@code GET  /user-rejected-events} : get all the userRejectedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userRejectedEvents in body.
     */
    @GetMapping("/user-rejected-events")
    public List<UserRejectedEventDTO> getAllUserRejectedEvents() {
        log.debug("REST request to get all UserRejectedEvents");
        return userRejectedEventService.findAll();
    }

    /**
     * {@code GET  /user-rejected-events/:id} : get the "id" userRejectedEvent.
     *
     * @param id the id of the userRejectedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userRejectedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-rejected-events/{id}")
    public ResponseEntity<UserRejectedEventDTO> getUserRejectedEvent(@PathVariable Long id) {
        log.debug("REST request to get UserRejectedEvent : {}", id);
        Optional<UserRejectedEventDTO> userRejectedEventDTO = userRejectedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userRejectedEventDTO);
    }
}
