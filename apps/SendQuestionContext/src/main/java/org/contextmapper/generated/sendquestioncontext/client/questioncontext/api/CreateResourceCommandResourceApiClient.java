package org.contextmapper.generated.sendquestioncontext.client.questioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.QuestionContextClientConfiguration;

@FeignClient(name="${createResourceCommandResource.name:createResourceCommandResource}", url="${createResourceCommandResource.url:http://localhost:8092}", configuration = QuestionContextClientConfiguration.class)
public interface CreateResourceCommandResourceApiClient extends CreateResourceCommandResourceApi {
}
