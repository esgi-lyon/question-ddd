package org.contextmapper.generated.usermanagementcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.usermanagementcontext.repository.UserValidatedEventRepository;
import org.contextmapper.generated.usermanagementcontext.service.UserValidatedEventService;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserValidatedEventDTO;
import org.contextmapper.generated.usermanagementcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.usermanagementcontext.domain.UserValidatedEvent}.
 */
@RestController
@RequestMapping("/api")
public class UserValidatedEventResource {

    private final Logger log = LoggerFactory.getLogger(UserValidatedEventResource.class);

    private final UserValidatedEventService userValidatedEventService;

    private final UserValidatedEventRepository userValidatedEventRepository;

    public UserValidatedEventResource(
        UserValidatedEventService userValidatedEventService,
        UserValidatedEventRepository userValidatedEventRepository
    ) {
        this.userValidatedEventService = userValidatedEventService;
        this.userValidatedEventRepository = userValidatedEventRepository;
    }

    /**
     * {@code GET  /user-validated-events} : get all the userValidatedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userValidatedEvents in body.
     */
    @GetMapping("/user-validated-events")
    public List<UserValidatedEventDTO> getAllUserValidatedEvents() {
        log.debug("REST request to get all UserValidatedEvents");
        return userValidatedEventService.findAll();
    }

    /**
     * {@code GET  /user-validated-events/:id} : get the "id" userValidatedEvent.
     *
     * @param id the id of the userValidatedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userValidatedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-validated-events/{id}")
    public ResponseEntity<UserValidatedEventDTO> getUserValidatedEvent(@PathVariable Long id) {
        log.debug("REST request to get UserValidatedEvent : {}", id);
        Optional<UserValidatedEventDTO> userValidatedEventDTO = userValidatedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userValidatedEventDTO);
    }
}
