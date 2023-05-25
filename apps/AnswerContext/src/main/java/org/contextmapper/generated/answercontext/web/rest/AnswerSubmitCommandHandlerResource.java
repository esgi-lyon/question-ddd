package org.contextmapper.generated.answercontext.web.rest;

import org.contextmapper.generated.answercontext.domain.AnswerSubmitCommand;
import org.contextmapper.generated.answercontext.service.AnswerSubmitCommandHandler;
import org.contextmapper.generated.answercontext.service.dto.AnswerDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import tech.jhipster.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/handlers")
public class AnswerSubmitCommandHandlerResource {

    private final Logger log = LoggerFactory.getLogger(AnswerSubmitCommandHandlerResource.class);

    private static final String ENTITY_NAME = "answerContextAnswerSubmitCommand";

    private final AnswerSubmitCommandHandler answerSubmitCommandHandler;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public AnswerSubmitCommandHandlerResource(
        AnswerSubmitCommandHandler answerSubmitCommandHandler
    ) {
        this.answerSubmitCommandHandler = answerSubmitCommandHandler;
    }

    @PostMapping("/answer-submit-command")
    public ResponseEntity<AnswerSubmitCommand> handleAnswerSubmitCommand(@RequestBody AnswerDTO answerDTO)
        throws URISyntaxException {
        log.debug("REST request to handle AnswerSubmitCommand : {}", answerDTO);

        final var result = answerSubmitCommandHandler.handleAnswerSubmitCommand(answerDTO);
        return ResponseEntity
            .created(new URI("/api/handlers/answer-submit-command/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}

