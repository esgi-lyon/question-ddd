package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.AnsweringUserRepository;
import org.contextmapper.generated.evaluationcontext.service.AnsweringUserService;
import org.contextmapper.generated.evaluationcontext.service.dto.AnsweringUserDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.AnsweringUser}.
 */
@RestController
@RequestMapping("/api")
public class AnsweringUserResource {

    private final Logger log = LoggerFactory.getLogger(AnsweringUserResource.class);

    private final AnsweringUserService answeringUserService;

    private final AnsweringUserRepository answeringUserRepository;

    public AnsweringUserResource(AnsweringUserService answeringUserService, AnsweringUserRepository answeringUserRepository) {
        this.answeringUserService = answeringUserService;
        this.answeringUserRepository = answeringUserRepository;
    }

    /**
     * {@code GET  /answering-users} : get all the answeringUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of answeringUsers in body.
     */
    @GetMapping("/answering-users")
    public List<AnsweringUserDTO> getAllAnsweringUsers() {
        log.debug("REST request to get all AnsweringUsers");
        return answeringUserService.findAll();
    }

    /**
     * {@code GET  /answering-users/:id} : get the "id" answeringUser.
     *
     * @param id the id of the answeringUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the answeringUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/answering-users/{id}")
    public ResponseEntity<AnsweringUserDTO> getAnsweringUser(@PathVariable Long id) {
        log.debug("REST request to get AnsweringUser : {}", id);
        Optional<AnsweringUserDTO> answeringUserDTO = answeringUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(answeringUserDTO);
    }
}
