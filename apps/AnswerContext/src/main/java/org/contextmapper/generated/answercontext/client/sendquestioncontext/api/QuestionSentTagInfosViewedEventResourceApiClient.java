package org.contextmapper.generated.answercontext.client.sendquestioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.sendquestioncontext.ClientConfiguration;

@FeignClient(name="${questionSentTagInfosViewedEventResource.name:questionSentTagInfosViewedEventResource}", url="${questionSentTagInfosViewedEventResource.url:http://localhost:8084}", configuration = ClientConfiguration.class)
public interface QuestionSentTagInfosViewedEventResourceApiClient extends QuestionSentTagInfosViewedEventResourceApi {
}
