package org.contextmapper.generated.sendquestioncontext.web.rest;

import org.contextmapper.generated.sendquestioncontext.domain.SendQuestionByTagsPreferencesCommand;
import org.contextmapper.generated.sendquestioncontext.service.SendQuestionByTagsPreferencesCommandHandler;
import org.contextmapper.generated.sendquestioncontext.service.dto.QuestionSentDTO;
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
@RequestMapping("/api")
public class SendQuestionByTagsPreferencesCommandHandlerResource {

    private final Logger log = LoggerFactory.getLogger(SendQuestionByTagsPreferencesCommandHandlerResource.class);

    private static final String ENTITY_NAME = "sendQuestionContextSendQuestionByTagsPreferencesCommand";

    private final SendQuestionByTagsPreferencesCommandHandler sendQuestionByTagsPreferencesCommandHandler;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public SendQuestionByTagsPreferencesCommandHandlerResource(
        SendQuestionByTagsPreferencesCommandHandler sendQuestionByTagsPreferencesCommandHandler
    ) {
        this.sendQuestionByTagsPreferencesCommandHandler = sendQuestionByTagsPreferencesCommandHandler;
    }

    @PostMapping("/send-question-by-preferences-command")
    public ResponseEntity<SendQuestionByTagsPreferencesCommand> handleSendQuestionByTagsPreferencesCommand(@RequestBody QuestionSentDTO questionSentDTO)
        throws URISyntaxException {
        log.debug("REST request to handle SendQuestionByTagsPreferencesCommand : {}", questionSentDTO);
        final var result = sendQuestionByTagsPreferencesCommandHandler.handleSendQuestionByTagsPreferencesCommand(questionSentDTO);
        return ResponseEntity
            .created(new URI("/api/handlers/send-question-by-preferences-command/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
