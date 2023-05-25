package org.contextmapper.generated.answercontext.client.usermanagement.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.usermanagement.ClientConfiguration;

@FeignClient(name="${authenticateResource.name:authenticateResource}", url="${authenticateResource.url:http://127.0.0.1:8081}", configuration = ClientConfiguration.class)
public interface AuthenticateResourceApiClient extends AuthenticateResourceApi {
}
