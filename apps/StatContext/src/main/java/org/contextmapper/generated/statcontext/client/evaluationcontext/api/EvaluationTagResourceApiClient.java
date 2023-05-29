package org.contextmapper.generated.statcontext.client.evaluationcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.statcontext.client.evaluationcontext.ClientConfiguration;

@FeignClient(name="${evaluationTagResource.name:evaluationTagResource}", url="${evaluationTagResource.url:http://localhost:8087}", configuration = ClientConfiguration.class)
public interface EvaluationTagResourceApiClient extends EvaluationTagResourceApi {
}
