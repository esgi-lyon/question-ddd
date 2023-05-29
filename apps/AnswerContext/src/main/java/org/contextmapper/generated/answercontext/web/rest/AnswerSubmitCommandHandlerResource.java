package org.contextmapper.generated.answercontext.web.rest;

import org.contextmapper.generated.answercontext.service.AnswerSubmitCommandHandler;
import org.contextmapper.generated.answercontext.service.TagChoicesListCommandHandler;
import org.contextmapper.generated.answercontext.service.dto.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import tech.jhipster.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/handlers")
public class AnswerSubmitCommandHandlerResource {

    private final Logger log = LoggerFactory.getLogger(AnswerSubmitCommandHandlerResource.class);

    private static final String ENTITY_NAME = "answerContextAnswerSubmitCommand";

    private static final String ENTITY_NAME_TAG_CHOICE = "answerContextTagChoicesListCommand";

    private final TagChoicesListCommandHandler tagChoicesListCommandHandler;

    private final AnswerSubmitCommandHandler answerSubmitCommandHandler;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public AnswerSubmitCommandHandlerResource(
        AnswerSubmitCommandHandler answerSubmitCommandHandler,
        TagChoicesListCommandHandler tagChoicesListCommandHandler
    ) {
        this.answerSubmitCommandHandler = answerSubmitCommandHandler;
        this.tagChoicesListCommandHandler = tagChoicesListCommandHandler;
    }

    @PostMapping("/answer-submit-command")
    public ResponseEntity<AnswerSubmittedEventDTO> handleAnswerSubmitCommand(@RequestBody AnswerSubmitCommandDTO answerDTO)
        throws URISyntaxException {
        log.debug("REST request to handle AnswerSubmitCommand : {}", answerDTO);

        final var result = answerSubmitCommandHandler.handleAnswerSubmitCommand(answerDTO);
        return ResponseEntity
            .created(new URI("/api/handlers/answer-submit-command/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/tags-choices-list-query")
    public ResponseEntity<TagChoicesListCommandHandler.NewAnswerDto> handleTagChoicesListCommand(@RequestParam("questionId") Long question) throws URISyntaxException {
        log.debug("REST request to handle TagChoicesListCommand");

        final var result = tagChoicesListCommandHandler.handleTagChoicesListCommand(question);
        return ResponseEntity
            .created(new URI("/api/handlers/tags-choices-list-query/" + result.answer().getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME_TAG_CHOICE, String.valueOf(result.answer().getId())))
            .body(result);
    }
}

