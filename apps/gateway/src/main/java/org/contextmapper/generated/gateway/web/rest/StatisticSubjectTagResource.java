package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.StatisticSubjectTagRepository;
import org.contextmapper.generated.gateway.service.StatisticSubjectTagService;
import org.contextmapper.generated.gateway.service.dto.StatisticSubjectTagDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.StatisticSubjectTag}.
 */
@RestController
@RequestMapping("/api")
public class StatisticSubjectTagResource {

    private final Logger log = LoggerFactory.getLogger(StatisticSubjectTagResource.class);

    private final StatisticSubjectTagService statisticSubjectTagService;

    private final StatisticSubjectTagRepository statisticSubjectTagRepository;

    public StatisticSubjectTagResource(
        StatisticSubjectTagService statisticSubjectTagService,
        StatisticSubjectTagRepository statisticSubjectTagRepository
    ) {
        this.statisticSubjectTagService = statisticSubjectTagService;
        this.statisticSubjectTagRepository = statisticSubjectTagRepository;
    }

    /**
     * {@code GET  /statistic-subject-tags} : get all the statisticSubjectTags.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of statisticSubjectTags in body.
     */
    @GetMapping("/statistic-subject-tags")
    public Mono<List<StatisticSubjectTagDTO>> getAllStatisticSubjectTags() {
        log.debug("REST request to get all StatisticSubjectTags");
        return statisticSubjectTagService.findAll().collectList();
    }

    /**
     * {@code GET  /statistic-subject-tags} : get all the statisticSubjectTags as a stream.
     * @return the {@link Flux} of statisticSubjectTags.
     */
    @GetMapping(value = "/statistic-subject-tags", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<StatisticSubjectTagDTO> getAllStatisticSubjectTagsAsStream() {
        log.debug("REST request to get all StatisticSubjectTags as a stream");
        return statisticSubjectTagService.findAll();
    }

    /**
     * {@code GET  /statistic-subject-tags/:id} : get the "id" statisticSubjectTag.
     *
     * @param id the id of the statisticSubjectTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statisticSubjectTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/statistic-subject-tags/{id}")
    public Mono<ResponseEntity<StatisticSubjectTagDTO>> getStatisticSubjectTag(@PathVariable Long id) {
        log.debug("REST request to get StatisticSubjectTag : {}", id);
        Mono<StatisticSubjectTagDTO> statisticSubjectTagDTO = statisticSubjectTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statisticSubjectTagDTO);
    }
}
