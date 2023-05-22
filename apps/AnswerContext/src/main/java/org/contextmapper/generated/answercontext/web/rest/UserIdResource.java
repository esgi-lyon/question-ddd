package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.UserIdRepository;
import org.contextmapper.generated.answercontext.service.UserIdService;
import org.contextmapper.generated.answercontext.service.dto.UserIdDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.UserId}.
 */
@RestController
@RequestMapping("/api")
public class UserIdResource {

    private final Logger log = LoggerFactory.getLogger(UserIdResource.class);

    private final UserIdService userIdService;

    private final UserIdRepository userIdRepository;

    public UserIdResource(UserIdService userIdService, UserIdRepository userIdRepository) {
        this.userIdService = userIdService;
        this.userIdRepository = userIdRepository;
    }

    /**
     * {@code GET  /user-ids} : get all the userIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userIds in body.
     */
    @GetMapping("/user-ids")
    public List<UserIdDTO> getAllUserIds() {
        log.debug("REST request to get all UserIds");
        return userIdService.findAll();
    }

    /**
     * {@code GET  /user-ids/:id} : get the "id" userId.
     *
     * @param id the id of the userIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-ids/{id}")
    public ResponseEntity<UserIdDTO> getUserId(@PathVariable Long id) {
        log.debug("REST request to get UserId : {}", id);
        Optional<UserIdDTO> userIdDTO = userIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userIdDTO);
    }
}
