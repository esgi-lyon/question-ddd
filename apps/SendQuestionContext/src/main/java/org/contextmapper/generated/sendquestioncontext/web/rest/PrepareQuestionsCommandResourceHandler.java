package org.contextmapper.generated.sendquestioncontext.web.rest;

import org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionsCommand;
import org.contextmapper.generated.sendquestioncontext.service.PrepareQuestionCommandHandler;
import org.contextmapper.generated.sendquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/handlers")
public class PrepareQuestionsCommandResourceHandler {

    private final Logger log = LoggerFactory.getLogger(PrepareQuestionsCommandResourceHandler.class);

    private static final String ENTITY_NAME = "sendQuestionContextPrepareQuestionsCommand";

    private final PrepareQuestionCommandHandler prepareQuestionCommandHandler;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public PrepareQuestionsCommandResourceHandler(
        PrepareQuestionCommandHandler prepareQuestionCommandHandler
    ) {
        this.prepareQuestionCommandHandler = prepareQuestionCommandHandler;
    }

    @PostMapping("/prepare-question-command")
    public ResponseEntity<PrepareQuestionsCommand> handlePrepareQuestionsCommand(@RequestBody PrepareQuestionsCommand command)
        throws URISyntaxException {
        log.debug("REST request to handle PrepareQuestionsCommand : {}", command);
        if (command.getId() != null) {
            throw new BadRequestAlertException("New entity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        final var result = prepareQuestionCommandHandler.handlePrepareQuestionsCommand(command);
        return ResponseEntity
            .created(new URI("/api/handlers/prepare-question-command/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
