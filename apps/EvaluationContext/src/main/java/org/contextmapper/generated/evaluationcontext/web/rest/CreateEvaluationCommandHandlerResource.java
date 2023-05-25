package org.contextmapper.generated.evaluationcontext.web.rest;

import org.contextmapper.generated.evaluationcontext.domain.CreateEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.service.CreateEvaluationCommandHandler;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
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
public class CreateEvaluationCommandHandlerResource {

    private final Logger log = LoggerFactory.getLogger(CreateEvaluationCommandHandlerResource.class);

    private static final String ENTITY_NAME = "evaluationContextCreateEvaluationCommand";

    private final CreateEvaluationCommandHandler createEvaluationCommandHandler;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public CreateEvaluationCommandHandlerResource(CreateEvaluationCommandHandler createEvaluationCommandHandler) {
        this.createEvaluationCommandHandler = createEvaluationCommandHandler;
    }

    @PostMapping("/create-evaluation-command")
    public ResponseEntity<CreateEvaluationCommand> handleCreateEvaluationCommand(@RequestBody EvaluationDTO evaluationDTO) throws URISyntaxException {
        log.debug("REST request to handle CreateEvaluationCommand : {}", evaluationDTO);
        final var result = createEvaluationCommandHandler.handleCreateEvaluationCommand(evaluationDTO);
        return ResponseEntity
            .created(new URI("/api/handlers/create-evaluation-command/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
