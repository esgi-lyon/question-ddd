package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.AnsweredTagRepository;
import org.contextmapper.generated.gateway.service.AnsweredTagService;
import org.contextmapper.generated.gateway.service.dto.AnsweredTagDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.AnsweredTag}.
 */
@RestController
@RequestMapping("/api")
public class AnsweredTagResource {

    private final Logger log = LoggerFactory.getLogger(AnsweredTagResource.class);

    private final AnsweredTagService answeredTagService;

    private final AnsweredTagRepository answeredTagRepository;

    public AnsweredTagResource(AnsweredTagService answeredTagService, AnsweredTagRepository answeredTagRepository) {
        this.answeredTagService = answeredTagService;
        this.answeredTagRepository = answeredTagRepository;
    }

    /**
     * {@code GET  /answered-tags} : get all the answeredTags.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of answeredTags in body.
     */
    @GetMapping("/answered-tags")
    public Mono<List<AnsweredTagDTO>> getAllAnsweredTags() {
        log.debug("REST request to get all AnsweredTags");
        return answeredTagService.findAll().collectList();
    }

    /**
     * {@code GET  /answered-tags} : get all the answeredTags as a stream.
     * @return the {@link Flux} of answeredTags.
     */
    @GetMapping(value = "/answered-tags", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<AnsweredTagDTO> getAllAnsweredTagsAsStream() {
        log.debug("REST request to get all AnsweredTags as a stream");
        return answeredTagService.findAll();
    }

    /**
     * {@code GET  /answered-tags/:id} : get the "id" answeredTag.
     *
     * @param id the id of the answeredTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the answeredTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/answered-tags/{id}")
    public Mono<ResponseEntity<AnsweredTagDTO>> getAnsweredTag(@PathVariable Long id) {
        log.debug("REST request to get AnsweredTag : {}", id);
        Mono<AnsweredTagDTO> answeredTagDTO = answeredTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(answeredTagDTO);
    }
}
