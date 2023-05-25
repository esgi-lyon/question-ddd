package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.ClientConfiguration;

@FeignClient(name="${questionResource.name:questionResource}", url="${questionResource.url:http://localhost:8084}", configuration = ClientConfiguration.class)
public interface QuestionResourceApiClient extends QuestionResourceApi {
}
