package org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.ClientConfiguration;

@FeignClient(name="${userValidatedEventResource.name:userValidatedEventResource}", url="${userValidatedEventResource.url:http://localhost:8081}", configuration = ClientConfiguration.class)
public interface UserValidatedEventResourceApiClient extends UserValidatedEventResourceApi {
}