package org.contextmapper.generated.statcontext.client.evaluationcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.statcontext.client.evaluationcontext.ClientConfiguration;

@FeignClient(name="${answerCheckedResource.name:answerCheckedResource}", url="${answerCheckedResource.url:http://localhost:8096}", configuration = ClientConfiguration.class)
public interface AnswerCheckedResourceApiClient extends AnswerCheckedResourceApi {
}
