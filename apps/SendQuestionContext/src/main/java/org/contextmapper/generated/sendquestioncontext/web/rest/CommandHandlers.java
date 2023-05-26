package org.contextmapper.generated.sendquestioncontext.web.rest;

import org.contextmapper.generated.sendquestioncontext.domain.PrepareQuestionCommand;
import org.contextmapper.generated.sendquestioncontext.domain.SendByPreferencesCommand;
import org.contextmapper.generated.sendquestioncontext.service.PrepareQuestionCommandHandler;
import org.contextmapper.generated.sendquestioncontext.service.SendByPreferencesCommandHandler;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
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
public class CommandHandlers {

    private final Logger log = LoggerFactory.getLogger(CommandHandlers.class);

    private static final String ENTITY_NAME = "sendQuestionContextPrepareQuestionsCommand";

    private static final String ENTITY_NAME_PREFS = "sendQuestionContextSendQuestionByTagsPreferencesCommand";

    private final PrepareQuestionCommandHandler prepareQuestionCommandHandler;

    private final SendByPreferencesCommandHandler sendByPreferencesCommandHandler;


    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public CommandHandlers(
            PrepareQuestionCommandHandler prepareQuestionCommandHandler,
            SendByPreferencesCommandHandler sendByPreferencesCommandHandler

    ) {
        this.sendByPreferencesCommandHandler = sendByPreferencesCommandHandler;
        this.prepareQuestionCommandHandler = prepareQuestionCommandHandler;
    }

    @PostMapping("/prepare-question-command")
    public ResponseEntity<PrepareQuestionCommand> handlePrepareQuestionsCommand(@RequestBody PrepareQuestionCommand command)
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

    @PostMapping("/send-question-by-preferences-command")
    public ResponseEntity<SendByPreferencesCommand> handleSendQuestionByTagsPreferencesCommand(@RequestBody QuestionSentDTO questionSentDTO)
            throws URISyntaxException {
        log.debug("REST request to handle SendQuestionByTagsPreferencesCommand : {}", questionSentDTO);
        final var result = sendByPreferencesCommandHandler.handleSendQuestionByTagsPreferencesCommand(questionSentDTO);
        return ResponseEntity
                .created(new URI("/api/handlers/send-question-by-preferences-command/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME_PREFS, result.getId().toString()))
                .body(result);
    }
}
