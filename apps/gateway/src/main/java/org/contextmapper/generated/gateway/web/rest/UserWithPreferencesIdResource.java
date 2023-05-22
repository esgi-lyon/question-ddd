package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.UserWithPreferencesIdRepository;
import org.contextmapper.generated.gateway.service.UserWithPreferencesIdService;
import org.contextmapper.generated.gateway.service.dto.UserWithPreferencesIdDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.UserWithPreferencesId}.
 */
@RestController
@RequestMapping("/api")
public class UserWithPreferencesIdResource {

    private final Logger log = LoggerFactory.getLogger(UserWithPreferencesIdResource.class);

    private final UserWithPreferencesIdService userWithPreferencesIdService;

    private final UserWithPreferencesIdRepository userWithPreferencesIdRepository;

    public UserWithPreferencesIdResource(
        UserWithPreferencesIdService userWithPreferencesIdService,
        UserWithPreferencesIdRepository userWithPreferencesIdRepository
    ) {
        this.userWithPreferencesIdService = userWithPreferencesIdService;
        this.userWithPreferencesIdRepository = userWithPreferencesIdRepository;
    }

    /**
     * {@code GET  /user-with-preferences-ids} : get all the userWithPreferencesIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userWithPreferencesIds in body.
     */
    @GetMapping("/user-with-preferences-ids")
    public Mono<List<UserWithPreferencesIdDTO>> getAllUserWithPreferencesIds() {
        log.debug("REST request to get all UserWithPreferencesIds");
        return userWithPreferencesIdService.findAll().collectList();
    }

    /**
     * {@code GET  /user-with-preferences-ids} : get all the userWithPreferencesIds as a stream.
     * @return the {@link Flux} of userWithPreferencesIds.
     */
    @GetMapping(value = "/user-with-preferences-ids", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<UserWithPreferencesIdDTO> getAllUserWithPreferencesIdsAsStream() {
        log.debug("REST request to get all UserWithPreferencesIds as a stream");
        return userWithPreferencesIdService.findAll();
    }

    /**
     * {@code GET  /user-with-preferences-ids/:id} : get the "id" userWithPreferencesId.
     *
     * @param id the id of the userWithPreferencesIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userWithPreferencesIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-with-preferences-ids/{id}")
    public Mono<ResponseEntity<UserWithPreferencesIdDTO>> getUserWithPreferencesId(@PathVariable Long id) {
        log.debug("REST request to get UserWithPreferencesId : {}", id);
        Mono<UserWithPreferencesIdDTO> userWithPreferencesIdDTO = userWithPreferencesIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userWithPreferencesIdDTO);
    }
}
