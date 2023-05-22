package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.QuestionResourceTagInfosRepository;
import org.contextmapper.generated.gateway.service.QuestionResourceTagInfosService;
import org.contextmapper.generated.gateway.service.dto.QuestionResourceTagInfosDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.QuestionResourceTagInfos}.
 */
@RestController
@RequestMapping("/api")
public class QuestionResourceTagInfosResource {

    private final Logger log = LoggerFactory.getLogger(QuestionResourceTagInfosResource.class);

    private final QuestionResourceTagInfosService questionResourceTagInfosService;

    private final QuestionResourceTagInfosRepository questionResourceTagInfosRepository;

    public QuestionResourceTagInfosResource(
        QuestionResourceTagInfosService questionResourceTagInfosService,
        QuestionResourceTagInfosRepository questionResourceTagInfosRepository
    ) {
        this.questionResourceTagInfosService = questionResourceTagInfosService;
        this.questionResourceTagInfosRepository = questionResourceTagInfosRepository;
    }

    /**
     * {@code GET  /question-resource-tag-infos} : get all the questionResourceTagInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionResourceTagInfos in body.
     */
    @GetMapping("/question-resource-tag-infos")
    public Mono<List<QuestionResourceTagInfosDTO>> getAllQuestionResourceTagInfos() {
        log.debug("REST request to get all QuestionResourceTagInfos");
        return questionResourceTagInfosService.findAll().collectList();
    }

    /**
     * {@code GET  /question-resource-tag-infos} : get all the questionResourceTagInfos as a stream.
     * @return the {@link Flux} of questionResourceTagInfos.
     */
    @GetMapping(value = "/question-resource-tag-infos", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<QuestionResourceTagInfosDTO> getAllQuestionResourceTagInfosAsStream() {
        log.debug("REST request to get all QuestionResourceTagInfos as a stream");
        return questionResourceTagInfosService.findAll();
    }

    /**
     * {@code GET  /question-resource-tag-infos/:id} : get the "id" questionResourceTagInfos.
     *
     * @param id the id of the questionResourceTagInfosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionResourceTagInfosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-resource-tag-infos/{id}")
    public Mono<ResponseEntity<QuestionResourceTagInfosDTO>> getQuestionResourceTagInfos(@PathVariable Long id) {
        log.debug("REST request to get QuestionResourceTagInfos : {}", id);
        Mono<QuestionResourceTagInfosDTO> questionResourceTagInfosDTO = questionResourceTagInfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionResourceTagInfosDTO);
    }
}
