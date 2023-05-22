package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.UserPreferencesTagInfosRepository;
import org.contextmapper.generated.gateway.service.UserPreferencesTagInfosService;
import org.contextmapper.generated.gateway.service.dto.UserPreferencesTagInfosDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.UserPreferencesTagInfos}.
 */
@RestController
@RequestMapping("/api")
public class UserPreferencesTagInfosResource {

    private final Logger log = LoggerFactory.getLogger(UserPreferencesTagInfosResource.class);

    private final UserPreferencesTagInfosService userPreferencesTagInfosService;

    private final UserPreferencesTagInfosRepository userPreferencesTagInfosRepository;

    public UserPreferencesTagInfosResource(
        UserPreferencesTagInfosService userPreferencesTagInfosService,
        UserPreferencesTagInfosRepository userPreferencesTagInfosRepository
    ) {
        this.userPreferencesTagInfosService = userPreferencesTagInfosService;
        this.userPreferencesTagInfosRepository = userPreferencesTagInfosRepository;
    }

    /**
     * {@code GET  /user-preferences-tag-infos} : get all the userPreferencesTagInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userPreferencesTagInfos in body.
     */
    @GetMapping("/user-preferences-tag-infos")
    public Mono<List<UserPreferencesTagInfosDTO>> getAllUserPreferencesTagInfos() {
        log.debug("REST request to get all UserPreferencesTagInfos");
        return userPreferencesTagInfosService.findAll().collectList();
    }

    /**
     * {@code GET  /user-preferences-tag-infos} : get all the userPreferencesTagInfos as a stream.
     * @return the {@link Flux} of userPreferencesTagInfos.
     */
    @GetMapping(value = "/user-preferences-tag-infos", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<UserPreferencesTagInfosDTO> getAllUserPreferencesTagInfosAsStream() {
        log.debug("REST request to get all UserPreferencesTagInfos as a stream");
        return userPreferencesTagInfosService.findAll();
    }

    /**
     * {@code GET  /user-preferences-tag-infos/:id} : get the "id" userPreferencesTagInfos.
     *
     * @param id the id of the userPreferencesTagInfosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userPreferencesTagInfosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-preferences-tag-infos/{id}")
    public Mono<ResponseEntity<UserPreferencesTagInfosDTO>> getUserPreferencesTagInfos(@PathVariable Long id) {
        log.debug("REST request to get UserPreferencesTagInfos : {}", id);
        Mono<UserPreferencesTagInfosDTO> userPreferencesTagInfosDTO = userPreferencesTagInfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userPreferencesTagInfosDTO);
    }
}
