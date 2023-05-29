package org.contextmapper.generated.sendquestioncontext.web.rest;

import org.contextmapper.generated.sendquestioncontext.service.AddPreferenceCommandHandler;
import org.contextmapper.generated.sendquestioncontext.service.PrepareQuestionCommandHandler;
import org.contextmapper.generated.sendquestioncontext.service.SendByPreferencesCommandHandler;
import org.contextmapper.generated.sendquestioncontext.service.ViewTagsForQuestionQueryHandler;
import org.contextmapper.generated.sendquestioncontext.service.dto.*;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/handlers")
public class QueryHandlers {

    private final Logger log = LoggerFactory.getLogger(QueryHandlers.class);

    private static final String ENTITY_NAME = "sendQuestionContextViewTagsForQuestionQuery";

    private final ViewTagsForQuestionQueryHandler viewTagsForQuestionQueryHandler;


    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public QueryHandlers(
        ViewTagsForQuestionQueryHandler viewTagsForQuestionQueryHandler
    ) {
        this.viewTagsForQuestionQueryHandler = viewTagsForQuestionQueryHandler;
    }

    @GetMapping("/view-tags-for-question-query")
    public ResponseEntity<List<QuestionSentTagInfosDTO>> handlePrepareQuestionsCommand(@RequestParam("questionId") Long questionId)
        throws URISyntaxException {
        log.debug("REST request to handle ViewTagsInfos : {}", questionId);
        final var result = viewTagsForQuestionQueryHandler.handle(questionId);
        return ResponseEntity
            .created(
                new URI(
                    "/api/handlers/view-tags-for-question-query/" +
                        result.stream().map(QuestionSentTagInfosDTO::getTagId)
                            .map(String::valueOf)
                            .collect(Collectors.joining("-"))
                )
            )
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, String.valueOf(questionId)))
            .body(result);
    }

}
