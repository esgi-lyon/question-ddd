package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.SendQuestionContextClientConfiguration;

@FeignClient(name="${createQuestionCommandResource.name:createQuestionCommandResource}", url="${createQuestionCommandResource.url:http://localhost:8084}", configuration = SendQuestionContextClientConfiguration.class)
public interface CreateQuestionCommandResourceApiClient extends CreateQuestionCommandResourceApi {
}
