package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.AnsweringUserRepository;
import org.contextmapper.generated.gateway.service.AnsweringUserService;
import org.contextmapper.generated.gateway.service.dto.AnsweringUserDTO;
import org.contextmapper.generated.gateway.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.AnsweringUser}.
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
    public Mono<List<AnsweringUserDTO>> getAllAnsweringUsers() {
        log.debug("REST request to get all AnsweringUsers");
        return answeringUserService.findAll().collectList();
    }

    /**
     * {@code GET  /answering-users} : get all the answeringUsers as a stream.
     * @return the {@link Flux} of answeringUsers.
     */
    @GetMapping(value = "/answering-users", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<AnsweringUserDTO> getAllAnsweringUsersAsStream() {
        log.debug("REST request to get all AnsweringUsers as a stream");
        return answeringUserService.findAll();
    }

    /**
     * {@code GET  /answering-users/:id} : get the "id" answeringUser.
     *
     * @param id the id of the answeringUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the answeringUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/answering-users/{id}")
    public Mono<ResponseEntity<AnsweringUserDTO>> getAnsweringUser(@PathVariable Long id) {
        log.debug("REST request to get AnsweringUser : {}", id);
        Mono<AnsweringUserDTO> answeringUserDTO = answeringUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(answeringUserDTO);
    }
}
