package org.contextmapper.generated.evaluationcontext.web.rest;

import org.contextmapper.generated.evaluationcontext.service.CreateEvaluationCommandHandler;
import org.contextmapper.generated.evaluationcontext.service.PointAwardRuleCommandHandler;
import org.contextmapper.generated.evaluationcontext.service.dto.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class CommandHandlersResource {

    private final Logger log = LoggerFactory.getLogger(CommandHandlersResource.class);

    private static final String ENTITY_NAME = "evaluationContextCreateEvaluationCommand";

    private final CreateEvaluationCommandHandler createEvaluationCommandHandler;
    private final PointAwardRuleCommandHandler pointAwardRuleCommandHandler;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public CommandHandlersResource(
        CreateEvaluationCommandHandler createEvaluationCommandHandler,
        PointAwardRuleCommandHandler pointAwardRuleCommandHandler
    ) {
        this.createEvaluationCommandHandler = createEvaluationCommandHandler;
        this.pointAwardRuleCommandHandler = pointAwardRuleCommandHandler;
    }

    @PostMapping("/create-evaluation-command")
    @PreAuthorize("hasAuthority(\"EVALUATOR\")")
    public ResponseEntity<EvaluationCreatedEventDTO> handleCreateEvaluationCommand(@RequestBody CreateEvaluationCommandDTO evaluationDTO) throws URISyntaxException {
        log.debug("REST request to handle CreateEvaluationCommand : {}", evaluationDTO);
        final var result = createEvaluationCommandHandler.handleCreateEvaluationCommand(evaluationDTO);
        return ResponseEntity
            .created(new URI("/api/handlers/create-evaluation-command/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/award-point-for-evaluation")
    @PreAuthorize("hasAuthority(\"EVALUATOR\")")
    public ResponseEntity<AwardPointForEvaluationCommandDTO> handleAwardPointForEvaluationCommand(@RequestBody EvaluationDTO evaluationDTO)
        throws URISyntaxException {
        AwardPointForEvaluationCommandDTO result = pointAwardRuleCommandHandler.handleAwardPointForEvaluationCommand(evaluationDTO);
        return ResponseEntity
            .created(new URI("/api/handlers/award-point-for-evaluation/" + result.getId()))
            .body(result);
    }
}
