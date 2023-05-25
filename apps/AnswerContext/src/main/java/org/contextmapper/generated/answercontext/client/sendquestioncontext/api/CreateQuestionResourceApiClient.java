package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.SendQuestionContextClientConfiguration;

@FeignClient(name="${createQuestionResource.name:createQuestionResource}", url="${createQuestionResource.url:http://localhost:8084}", configuration = SendQuestionContextClientConfiguration.class)
public interface CreateQuestionResourceApiClient extends CreateQuestionResourceApi {
}
