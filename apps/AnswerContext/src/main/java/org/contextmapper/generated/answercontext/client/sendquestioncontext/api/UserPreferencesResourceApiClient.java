package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.SendQuestionContextClientConfiguration;

@FeignClient(name="${userPreferencesResource.name:userPreferencesResource}", url="${userPreferencesResource.url:http://localhost:8084}", configuration = SendQuestionContextClientConfiguration.class)
public interface UserPreferencesResourceApiClient extends UserPreferencesResourceApi {
}
