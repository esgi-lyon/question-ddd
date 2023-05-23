package org.contextmapper.generated.usermanagementcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.usermanagementcontext.repository.UserWaitingForValidationEventRepository;
import org.contextmapper.generated.usermanagementcontext.service.UserWaitingForValidationEventService;
import org.contextmapper.generated.usermanagementcontext.service.dto.UserWaitingForValidationEventDTO;
import org.contextmapper.generated.usermanagementcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.usermanagementcontext.domain.UserWaitingForValidationEvent}.
 */
@RestController
@RequestMapping("/api")
public class UserWaitingForValidationEventResource {

    private final Logger log = LoggerFactory.getLogger(UserWaitingForValidationEventResource.class);

    private final UserWaitingForValidationEventService userWaitingForValidationEventService;

    private final UserWaitingForValidationEventRepository userWaitingForValidationEventRepository;

    public UserWaitingForValidationEventResource(
        UserWaitingForValidationEventService userWaitingForValidationEventService,
        UserWaitingForValidationEventRepository userWaitingForValidationEventRepository
    ) {
        this.userWaitingForValidationEventService = userWaitingForValidationEventService;
        this.userWaitingForValidationEventRepository = userWaitingForValidationEventRepository;
    }

    /**
     * {@code GET  /user-waiting-for-validation-events} : get all the userWaitingForValidationEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userWaitingForValidationEvents in body.
     */
    @GetMapping("/user-waiting-for-validation-events")
    public List<UserWaitingForValidationEventDTO> getAllUserWaitingForValidationEvents() {
        log.debug("REST request to get all UserWaitingForValidationEvents");
        return userWaitingForValidationEventService.findAll();
    }

    /**
     * {@code GET  /user-waiting-for-validation-events/:id} : get the "id" userWaitingForValidationEvent.
     *
     * @param id the id of the userWaitingForValidationEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userWaitingForValidationEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-waiting-for-validation-events/{id}")
    public ResponseEntity<UserWaitingForValidationEventDTO> getUserWaitingForValidationEvent(@PathVariable Long id) {
        log.debug("REST request to get UserWaitingForValidationEvent : {}", id);
        Optional<UserWaitingForValidationEventDTO> userWaitingForValidationEventDTO = userWaitingForValidationEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userWaitingForValidationEventDTO);
    }
}
