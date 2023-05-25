package org.contextmapper.generated.sendquestioncontext.client.questioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.QuestionContextClientConfiguration;

@FeignClient(name="${questionResourceTagInfosResource.name:questionResourceTagInfosResource}", url="${questionResourceTagInfosResource.url:http://localhost:8082}", configuration = QuestionContextClientConfiguration.class)
public interface QuestionResourceTagInfosResourceApiClient extends QuestionResourceTagInfosResourceApi {
}
