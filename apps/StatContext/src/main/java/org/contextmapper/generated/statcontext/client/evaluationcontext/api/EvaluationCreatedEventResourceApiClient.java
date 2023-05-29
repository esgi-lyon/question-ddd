package org.contextmapper.generated.statcontext.client.evaluationcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.statcontext.client.evaluationcontext.ClientConfiguration;

@FeignClient(name="${evaluationCreatedEventResource.name:evaluationCreatedEventResource}", url="${evaluationCreatedEventResource.url:http://localhost:8087}", configuration = ClientConfiguration.class)
public interface EvaluationCreatedEventResourceApiClient extends EvaluationCreatedEventResourceApi {
}
