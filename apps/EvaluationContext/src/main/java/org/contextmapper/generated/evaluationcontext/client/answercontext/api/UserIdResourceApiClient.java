package org.contextmapper.generated.evaluationcontext.client.answercontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.evaluationcontext.client.answercontext.ClientConfiguration;

@FeignClient(name="${userIdResource.name:userIdResource}", url="${userIdResource.url:http://localhost:8095}", configuration = ClientConfiguration.class)
public interface UserIdResourceApiClient extends UserIdResourceApi {
}
