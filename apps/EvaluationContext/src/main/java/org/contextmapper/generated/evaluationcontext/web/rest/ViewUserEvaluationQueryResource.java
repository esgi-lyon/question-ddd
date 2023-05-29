package org.contextmapper.generated.evaluationcontext.web.rest;

import org.contextmapper.generated.evaluationcontext.service.ViewUserEvaluationQueryHandler;
import org.contextmapper.generated.evaluationcontext.service.dto.EvaluationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/handlers")
public class ViewUserEvaluationQueryResource {

    private final ViewUserEvaluationQueryHandler viewUserEvaluationQueryHandler;

    public ViewUserEvaluationQueryResource(ViewUserEvaluationQueryHandler viewUserEvaluationQueryHandler) {
        this.viewUserEvaluationQueryHandler = viewUserEvaluationQueryHandler;
    }

    @GetMapping("/view-user-evaluation-query")
    public ResponseEntity<List<EvaluationDTO>> handleViewUserEvaluationQuery(@RequestParam(required = false) String userMail) {
        List<EvaluationDTO> result = viewUserEvaluationQueryHandler.handle(userMail);
        return ResponseEntity.ok().body(result);
    }
}
