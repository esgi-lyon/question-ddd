package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.SendQuestionContextClientConfiguration;

@FeignClient(name="${sendQuestionByTagsPreferencesCommandResource.name:sendQuestionByTagsPreferencesCommandResource}", url="${sendQuestionByTagsPreferencesCommandResource.url:http://localhost:8084}", configuration = SendQuestionContextClientConfiguration.class)
public interface SendQuestionByTagsPreferencesCommandResourceApiClient extends SendQuestionByTagsPreferencesCommandResourceApi {
}
