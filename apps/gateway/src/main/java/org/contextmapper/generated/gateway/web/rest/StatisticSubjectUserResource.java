package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.StatisticSubjectUserRepository;
import org.contextmapper.generated.gateway.service.StatisticSubjectUserService;
import org.contextmapper.generated.gateway.service.dto.StatisticSubjectUserDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.StatisticSubjectUser}.
 */
@RestController
@RequestMapping("/api")
public class StatisticSubjectUserResource {

    private final Logger log = LoggerFactory.getLogger(StatisticSubjectUserResource.class);

    private final StatisticSubjectUserService statisticSubjectUserService;

    private final StatisticSubjectUserRepository statisticSubjectUserRepository;

    public StatisticSubjectUserResource(
        StatisticSubjectUserService statisticSubjectUserService,
        StatisticSubjectUserRepository statisticSubjectUserRepository
    ) {
        this.statisticSubjectUserService = statisticSubjectUserService;
        this.statisticSubjectUserRepository = statisticSubjectUserRepository;
    }

    /**
     * {@code GET  /statistic-subject-users} : get all the statisticSubjectUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of statisticSubjectUsers in body.
     */
    @GetMapping("/statistic-subject-users")
    public Mono<List<StatisticSubjectUserDTO>> getAllStatisticSubjectUsers() {
        log.debug("REST request to get all StatisticSubjectUsers");
        return statisticSubjectUserService.findAll().collectList();
    }

    /**
     * {@code GET  /statistic-subject-users} : get all the statisticSubjectUsers as a stream.
     * @return the {@link Flux} of statisticSubjectUsers.
     */
    @GetMapping(value = "/statistic-subject-users", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<StatisticSubjectUserDTO> getAllStatisticSubjectUsersAsStream() {
        log.debug("REST request to get all StatisticSubjectUsers as a stream");
        return statisticSubjectUserService.findAll();
    }

    /**
     * {@code GET  /statistic-subject-users/:id} : get the "id" statisticSubjectUser.
     *
     * @param id the id of the statisticSubjectUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statisticSubjectUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/statistic-subject-users/{id}")
    public Mono<ResponseEntity<StatisticSubjectUserDTO>> getStatisticSubjectUser(@PathVariable Long id) {
        log.debug("REST request to get StatisticSubjectUser : {}", id);
        Mono<StatisticSubjectUserDTO> statisticSubjectUserDTO = statisticSubjectUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statisticSubjectUserDTO);
    }
}
