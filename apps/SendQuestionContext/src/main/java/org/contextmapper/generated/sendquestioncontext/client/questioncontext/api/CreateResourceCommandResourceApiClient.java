package org.contextmapper.generated.sendquestioncontext.client.questioncontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.questioncontext.QuestionClientConfiguration;

@FeignClient(name="${createResourceCommandResource.name:createResourceCommandResource}", url="${createResourceCommandResource.url:http://localhost:8082}", configuration = QuestionClientConfiguration.class)
public interface CreateResourceCommandResourceApiClient extends CreateResourceCommandResourceApi {
}
