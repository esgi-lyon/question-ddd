package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.SendQuestionContextClientConfiguration;

@FeignClient(name="${sendByPreferencesCommandResource.name:sendByPreferencesCommandResource}", url="${sendByPreferencesCommandResource.url:http://localhost:8084}", configuration = SendQuestionContextClientConfiguration.class)
public interface SendByPreferencesCommandResourceApiClient extends SendByPreferencesCommandResourceApi {
}
