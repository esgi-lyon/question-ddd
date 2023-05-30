package org.contextmapper.generated.statcontext.client.evaluationcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.statcontext.client.evaluationcontext.ClientConfiguration;

@FeignClient(name="${awardPointForEvaluationCommandResource.name:awardPointForEvaluationCommandResource}", url="${awardPointForEvaluationCommandResource.url:http://localhost:8096}", configuration = ClientConfiguration.class)
public interface AwardPointForEvaluationCommandResourceApiClient extends AwardPointForEvaluationCommandResourceApi {
}
