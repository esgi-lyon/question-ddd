package org.contextmapper.generated.evaluationcontext.client.answercontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.evaluationcontext.client.answercontext.ClientConfiguration;

@FeignClient(name="${userEmailResource.name:userEmailResource}", url="${userEmailResource.url:http://localhost:8095}", configuration = ClientConfiguration.class)
public interface UserEmailResourceApiClient extends UserEmailResourceApi {
}
