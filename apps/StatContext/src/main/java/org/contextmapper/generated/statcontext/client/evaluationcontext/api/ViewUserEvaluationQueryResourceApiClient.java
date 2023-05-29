package org.contextmapper.generated.statcontext.client.evaluationcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.statcontext.client.evaluationcontext.ClientConfiguration;

@FeignClient(name="${viewUserEvaluationQueryResource.name:viewUserEvaluationQueryResource}", url="${viewUserEvaluationQueryResource.url:http://localhost:8087}", configuration = ClientConfiguration.class)
public interface ViewUserEvaluationQueryResourceApiClient extends ViewUserEvaluationQueryResourceApi {
}
