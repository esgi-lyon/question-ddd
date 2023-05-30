package org.contextmapper.generated.evaluationcontext.client.answercontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.evaluationcontext.client.answercontext.ClientConfiguration;

@FeignClient(name="${answerSubmitResource.name:answerSubmitResource}", url="${answerSubmitResource.url:http://localhost:8095}", configuration = ClientConfiguration.class)
public interface AnswerSubmitResourceApiClient extends AnswerSubmitResourceApi {
}
