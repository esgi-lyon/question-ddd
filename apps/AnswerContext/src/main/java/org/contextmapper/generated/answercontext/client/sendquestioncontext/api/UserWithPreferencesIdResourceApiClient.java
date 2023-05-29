package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.SendQuestionContextClientConfiguration;

@FeignClient(name="${userWithPreferencesIdResource.name:userWithPreferencesIdResource}", url="${userWithPreferencesIdResource.url:http://localhost:8084}", configuration = SendQuestionContextClientConfiguration.class)
public interface UserWithPreferencesIdResourceApiClient extends UserWithPreferencesIdResourceApi {
}
