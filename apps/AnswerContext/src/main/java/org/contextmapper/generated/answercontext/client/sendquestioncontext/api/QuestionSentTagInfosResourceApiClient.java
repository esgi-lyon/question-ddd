package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.SendQuestionClientConfiguration;

@FeignClient(name="${questionSentTagInfosResource.name:questionSentTagInfosResource}", url="${questionSentTagInfosResource.url:http://localhost:8094}", configuration = SendQuestionClientConfiguration.class)
public interface QuestionSentTagInfosResourceApiClient extends QuestionSentTagInfosResourceApi {
}
