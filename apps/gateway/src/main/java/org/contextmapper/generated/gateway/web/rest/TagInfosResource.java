package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.TagInfosRepository;
import org.contextmapper.generated.gateway.service.TagInfosService;
import org.contextmapper.generated.gateway.service.dto.TagInfosDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.TagInfos}.
 */
@RestController
@RequestMapping("/api")
public class TagInfosResource {

    private final Logger log = LoggerFactory.getLogger(TagInfosResource.class);

    private final TagInfosService tagInfosService;

    private final TagInfosRepository tagInfosRepository;

    public TagInfosResource(TagInfosService tagInfosService, TagInfosRepository tagInfosRepository) {
        this.tagInfosService = tagInfosService;
        this.tagInfosRepository = tagInfosRepository;
    }

    /**
     * {@code GET  /tag-infos} : get all the tagInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagInfos in body.
     */
    @GetMapping("/tag-infos")
    public Mono<List<TagInfosDTO>> getAllTagInfos() {
        log.debug("REST request to get all TagInfos");
        return tagInfosService.findAll().collectList();
    }

    /**
     * {@code GET  /tag-infos} : get all the tagInfos as a stream.
     * @return the {@link Flux} of tagInfos.
     */
    @GetMapping(value = "/tag-infos", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<TagInfosDTO> getAllTagInfosAsStream() {
        log.debug("REST request to get all TagInfos as a stream");
        return tagInfosService.findAll();
    }

    /**
     * {@code GET  /tag-infos/:id} : get the "id" tagInfos.
     *
     * @param id the id of the tagInfosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagInfosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-infos/{id}")
    public Mono<ResponseEntity<TagInfosDTO>> getTagInfos(@PathVariable Long id) {
        log.debug("REST request to get TagInfos : {}", id);
        Mono<TagInfosDTO> tagInfosDTO = tagInfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagInfosDTO);
    }
}
