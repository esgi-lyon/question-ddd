package org.contextmapper.generated.evaluationcontext.web.rest;

import org.contextmapper.generated.evaluationcontext.domain.CheckAnswerCommand;
import org.contextmapper.generated.evaluationcontext.service.CheckAnswerCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/handlers")
public class CheckAnswerCommandHandlerResource {

    private final CheckAnswerCommandHandler checkAnswerCommandHandler;

    public CheckAnswerCommandHandlerResource(CheckAnswerCommandHandler checkAnswerCommandHandler) {
        this.checkAnswerCommandHandler = checkAnswerCommandHandler;
    }

    @PostMapping("/check-answer-command")
    public ResponseEntity<CheckAnswerCommand> handleCheckAnswerCommand(@RequestBody CheckAnswerCommand checkAnswerCommand)
        throws URISyntaxException {
        CheckAnswerCommand result = checkAnswerCommandHandler.handleCheckAnswerCommand(checkAnswerCommand);
        return ResponseEntity
            .created(new URI("/api/handlers/check-answer-command/" + result.getId()))
            .body(result);
    }
}
