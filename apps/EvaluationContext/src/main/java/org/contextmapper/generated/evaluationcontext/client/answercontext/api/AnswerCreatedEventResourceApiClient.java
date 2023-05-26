package org.contextmapper.generated.evaluationcontext.client.answercontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.evaluationcontext.client.answercontext.ClientConfiguration;

@FeignClient(name="${answerCreatedEventResource.name:answerCreatedEventResource}", url="${answerCreatedEventResource.url:http://localhost:8085}", configuration = ClientConfiguration.class)
public interface AnswerCreatedEventResourceApiClient extends AnswerCreatedEventResourceApi {
}
