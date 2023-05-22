package org.contextmapper.generated.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.gateway.repository.StatisticSubjectQuestionRepository;
import org.contextmapper.generated.gateway.service.StatisticSubjectQuestionService;
import org.contextmapper.generated.gateway.service.dto.StatisticSubjectQuestionDTO;
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
 * REST controller for managing {@link org.contextmapper.generated.gateway.domain.StatisticSubjectQuestion}.
 */
@RestController
@RequestMapping("/api")
public class StatisticSubjectQuestionResource {

    private final Logger log = LoggerFactory.getLogger(StatisticSubjectQuestionResource.class);

    private final StatisticSubjectQuestionService statisticSubjectQuestionService;

    private final StatisticSubjectQuestionRepository statisticSubjectQuestionRepository;

    public StatisticSubjectQuestionResource(
        StatisticSubjectQuestionService statisticSubjectQuestionService,
        StatisticSubjectQuestionRepository statisticSubjectQuestionRepository
    ) {
        this.statisticSubjectQuestionService = statisticSubjectQuestionService;
        this.statisticSubjectQuestionRepository = statisticSubjectQuestionRepository;
    }

    /**
     * {@code GET  /statistic-subject-questions} : get all the statisticSubjectQuestions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of statisticSubjectQuestions in body.
     */
    @GetMapping("/statistic-subject-questions")
    public Mono<List<StatisticSubjectQuestionDTO>> getAllStatisticSubjectQuestions() {
        log.debug("REST request to get all StatisticSubjectQuestions");
        return statisticSubjectQuestionService.findAll().collectList();
    }

    /**
     * {@code GET  /statistic-subject-questions} : get all the statisticSubjectQuestions as a stream.
     * @return the {@link Flux} of statisticSubjectQuestions.
     */
    @GetMapping(value = "/statistic-subject-questions", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<StatisticSubjectQuestionDTO> getAllStatisticSubjectQuestionsAsStream() {
        log.debug("REST request to get all StatisticSubjectQuestions as a stream");
        return statisticSubjectQuestionService.findAll();
    }

    /**
     * {@code GET  /statistic-subject-questions/:id} : get the "id" statisticSubjectQuestion.
     *
     * @param id the id of the statisticSubjectQuestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statisticSubjectQuestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/statistic-subject-questions/{id}")
    public Mono<ResponseEntity<StatisticSubjectQuestionDTO>> getStatisticSubjectQuestion(@PathVariable Long id) {
        log.debug("REST request to get StatisticSubjectQuestion : {}", id);
        Mono<StatisticSubjectQuestionDTO> statisticSubjectQuestionDTO = statisticSubjectQuestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statisticSubjectQuestionDTO);
    }
}
