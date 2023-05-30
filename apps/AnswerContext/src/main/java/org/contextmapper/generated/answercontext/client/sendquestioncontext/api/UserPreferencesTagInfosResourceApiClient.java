package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.SendQuestionClientConfiguration;

@FeignClient(name="${userPreferencesTagInfosResource.name:userPreferencesTagInfosResource}", url="${userPreferencesTagInfosResource.url:http://localhost:8094}", configuration = SendQuestionClientConfiguration.class)
public interface UserPreferencesTagInfosResourceApiClient extends UserPreferencesTagInfosResourceApi {
}
