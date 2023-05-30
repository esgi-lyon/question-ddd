package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.SendQuestionClientConfiguration;

@FeignClient(name="${questionSentResource.name:questionSentResource}", url="${questionSentResource.url:http://localhost:8094}", configuration = SendQuestionClientConfiguration.class)
public interface QuestionSentResourceApiClient extends QuestionSentResourceApi {
}
