package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.ClientConfiguration;

@FeignClient(name="${prepareQuestionsCommandResource.name:prepareQuestionsCommandResource}", url="${prepareQuestionsCommandResource.url:http://localhost:8084}", configuration = ClientConfiguration.class)
public interface PrepareQuestionsCommandResourceApiClient extends PrepareQuestionsCommandResourceApi {
}
