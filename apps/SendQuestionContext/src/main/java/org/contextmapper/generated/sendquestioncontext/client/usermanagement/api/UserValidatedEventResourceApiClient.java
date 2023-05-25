package org.contextmapper.generated.sendquestioncontext.client.usermanagement.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.usermanagement.UserManagementClientConfiguration;

@FeignClient(name="${userValidatedEventResource.name:userValidatedEventResource}", url="${userValidatedEventResource.url:http://127.0.0.1:8081}", configuration = UserManagementClientConfiguration.class)
public interface UserValidatedEventResourceApiClient extends UserValidatedEventResourceApi {
}
