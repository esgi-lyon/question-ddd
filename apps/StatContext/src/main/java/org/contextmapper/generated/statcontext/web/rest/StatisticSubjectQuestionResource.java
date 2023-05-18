package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.StatisticSubjectQuestionRepository;
import org.contextmapper.generated.statcontext.service.StatisticSubjectQuestionService;
import org.contextmapper.generated.statcontext.service.dto.StatisticSubjectQuestionDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.StatisticSubjectQuestion}.
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
    public List<StatisticSubjectQuestionDTO> getAllStatisticSubjectQuestions() {
        log.debug("REST request to get all StatisticSubjectQuestions");
        return statisticSubjectQuestionService.findAll();
    }

    /**
     * {@code GET  /statistic-subject-questions/:id} : get the "id" statisticSubjectQuestion.
     *
     * @param id the id of the statisticSubjectQuestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the statisticSubjectQuestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/statistic-subject-questions/{id}")
    public ResponseEntity<StatisticSubjectQuestionDTO> getStatisticSubjectQuestion(@PathVariable Long id) {
        log.debug("REST request to get StatisticSubjectQuestion : {}", id);
        Optional<StatisticSubjectQuestionDTO> statisticSubjectQuestionDTO = statisticSubjectQuestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(statisticSubjectQuestionDTO);
    }
}
