package org.contextmapper.generated.sendquestioncontext.client.questioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.QuestionContextClientConfiguration;

@FeignClient(name="${questionResourceResource.name:questionResourceResource}", url="${questionResourceResource.url:http://localhost:8092}", configuration = QuestionContextClientConfiguration.class)
public interface QuestionResourceResourceApiClient extends QuestionResourceResourceApi {
}
