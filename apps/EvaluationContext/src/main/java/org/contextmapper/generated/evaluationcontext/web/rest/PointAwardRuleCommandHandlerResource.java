package org.contextmapper.generated.evaluationcontext.web.rest;

    import org.contextmapper.generated.evaluationcontext.domain.AwardPointForEvaluationCommand;
    import org.contextmapper.generated.evaluationcontext.service.PointAwardRuleCommandHandler;
    import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.net.URI;
    import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/handlers")
public class PointAwardRuleCommandHandlerResource {

    private final PointAwardRuleCommandHandler pointAwardRuleCommandHandler;

    public PointAwardRuleCommandHandlerResource(PointAwardRuleCommandHandler pointAwardRuleCommandHandler) {
        this.pointAwardRuleCommandHandler = pointAwardRuleCommandHandler;
    }

    @PostMapping("/award-point-for-evaluation")
    public ResponseEntity<AwardPointForEvaluationCommand> handleAwardPointForEvaluationCommand(@RequestBody EvaluationDTO evaluationDTO)
        throws URISyntaxException {
        AwardPointForEvaluationCommand result = pointAwardRuleCommandHandler.handleAwardPointForEvaluationCommand(evaluationDTO);
        return ResponseEntity
            .created(new URI("/api/handlers/award-point-for-evaluation/" + result.getId()))
            .body(result);
    }
}
