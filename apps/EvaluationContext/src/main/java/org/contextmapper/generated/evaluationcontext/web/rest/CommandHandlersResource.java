package org.contextmapper.generated.evaluationcontext.web.rest;

import org.contextmapper.generated.evaluationcontext.domain.CreateEvaluationCommand;
import org.contextmapper.generated.evaluationcontext.service.CheckAnswerCommandHandler;
import org.contextmapper.generated.evaluationcontext.service.CreateEvaluationCommandHandler;
import org.contextmapper.generated.evaluationcontext.service.PointAwardRuleCommandHandler;
import org.contextmapper.generated.evaluationcontext.service.dto.AwardPointForEvaluationCommandDTO;
import org.contextmapper.generated.evaluationcontext.service.dto.CheckAnswerCommandDTO;
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
public class CommandHandlersResource {

    private final Logger log = LoggerFactory.getLogger(CommandHandlersResource.class);

    private static final String ENTITY_NAME = "evaluationContextCreateEvaluationCommand";

    private final CreateEvaluationCommandHandler createEvaluationCommandHandler;
    private final PointAwardRuleCommandHandler pointAwardRuleCommandHandler;

    private final CheckAnswerCommandHandler checkAnswerCommandHandler;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public CommandHandlersResource(
        CreateEvaluationCommandHandler createEvaluationCommandHandler,
        PointAwardRuleCommandHandler pointAwardRuleCommandHandler,
        CheckAnswerCommandHandler checkAnswerCommandHandler
    ) {
        this.checkAnswerCommandHandler = checkAnswerCommandHandler;
        this.createEvaluationCommandHandler =createEvaluationCommandHandler;
        this.pointAwardRuleCommandHandler =pointAwardRuleCommandHandler;
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

    @PostMapping("/check-answer-command")
    public ResponseEntity<CheckAnswerCommandDTO> handleCheckAnswerCommand(@RequestBody CheckAnswerCommandDTO checkAnswerCommand)
        throws URISyntaxException {
        CheckAnswerCommandDTO result = checkAnswerCommandHandler.handleCheckAnswerCommand(checkAnswerCommand);
        return ResponseEntity
            .created(new URI("/api/handlers/check-answer-command/" + result.getId()))
            .body(result);
    }

    @PostMapping("/award-point-for-evaluation")
    public ResponseEntity<AwardPointForEvaluationCommandDTO> handleAwardPointForEvaluationCommand(@RequestBody EvaluationDTO evaluationDTO)
        throws URISyntaxException {
        AwardPointForEvaluationCommandDTO result = pointAwardRuleCommandHandler.handleAwardPointForEvaluationCommand(evaluationDTO);
        return ResponseEntity
            .created(new URI("/api/handlers/award-point-for-evaluation/" + result.getId()))
            .body(result);
    }
}
