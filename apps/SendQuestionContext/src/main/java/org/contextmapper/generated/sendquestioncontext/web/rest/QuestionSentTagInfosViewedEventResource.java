package org.contextmapper.generated.sendquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.sendquestioncontext.repository.QuestionSentTagInfosViewedEventRepository;
import org.contextmapper.generated.sendquestioncontext.service.QuestionSentTagInfosViewedEventService;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentTagInfosViewedEventDTO;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.sendquestioncontext.domain.QuestionSentTagInfosViewedEvent}.
 */
@RestController
@RequestMapping("/api")
public class QuestionSentTagInfosViewedEventResource {

    private final Logger log = LoggerFactory.getLogger(QuestionSentTagInfosViewedEventResource.class);

    private final QuestionSentTagInfosViewedEventService questionSentTagInfosViewedEventService;

    private final QuestionSentTagInfosViewedEventRepository questionSentTagInfosViewedEventRepository;

    public QuestionSentTagInfosViewedEventResource(
        QuestionSentTagInfosViewedEventService questionSentTagInfosViewedEventService,
        QuestionSentTagInfosViewedEventRepository questionSentTagInfosViewedEventRepository
    ) {
        this.questionSentTagInfosViewedEventService = questionSentTagInfosViewedEventService;
        this.questionSentTagInfosViewedEventRepository = questionSentTagInfosViewedEventRepository;
    }

    /**
     * {@code GET  /question-sent-tag-infos-viewed-events} : get all the questionSentTagInfosViewedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionSentTagInfosViewedEvents in body.
     */
    @GetMapping("/question-sent-tag-infos-viewed-events")
    public List<QuestionSentTagInfosViewedEventDTO> getAllQuestionSentTagInfosViewedEvents() {
        log.debug("REST request to get all QuestionSentTagInfosViewedEvents");
        return questionSentTagInfosViewedEventService.findAll();
    }

    /**
     * {@code GET  /question-sent-tag-infos-viewed-events/:id} : get the "id" questionSentTagInfosViewedEvent.
     *
     * @param id the id of the questionSentTagInfosViewedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionSentTagInfosViewedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-sent-tag-infos-viewed-events/{id}")
    public ResponseEntity<QuestionSentTagInfosViewedEventDTO> getQuestionSentTagInfosViewedEvent(@PathVariable Long id) {
        log.debug("REST request to get QuestionSentTagInfosViewedEvent : {}", id);
        Optional<QuestionSentTagInfosViewedEventDTO> questionSentTagInfosViewedEventDTO = questionSentTagInfosViewedEventService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(questionSentTagInfosViewedEventDTO);
    }
}
