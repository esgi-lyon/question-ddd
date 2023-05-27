package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.UserEmailRepository;
import org.contextmapper.generated.answercontext.service.UserEmailService;
import org.contextmapper.generated.answercontext.service.dto.UserEmailDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.UserEmail}.
 */
@RestController
@RequestMapping("/api")
public class UserEmailResource {

    private final Logger log = LoggerFactory.getLogger(UserEmailResource.class);

    private final UserEmailService userEmailService;

    private final UserEmailRepository userEmailRepository;

    public UserEmailResource(UserEmailService userEmailService, UserEmailRepository userEmailRepository) {
        this.userEmailService = userEmailService;
        this.userEmailRepository = userEmailRepository;
    }

    /**
     * {@code GET  /user-emails} : get all the userEmails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userEmails in body.
     */
    @GetMapping("/user-emails")
    public List<UserEmailDTO> getAllUserEmails() {
        log.debug("REST request to get all UserEmails");
        return userEmailService.findAll();
    }

    /**
     * {@code GET  /user-emails/:id} : get the "id" userEmail.
     *
     * @param id the id of the userEmailDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userEmailDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-emails/{id}")
    public ResponseEntity<UserEmailDTO> getUserEmail(@PathVariable Long id) {
        log.debug("REST request to get UserEmail : {}", id);
        Optional<UserEmailDTO> userEmailDTO = userEmailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userEmailDTO);
    }
}
