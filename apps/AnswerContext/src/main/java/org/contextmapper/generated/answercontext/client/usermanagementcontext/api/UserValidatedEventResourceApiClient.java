package org.contextmapper.generated.answercontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.usermanagementcontext.UserManagementClientConfiguration;

@FeignClient(name="${userValidatedEventResource.name:userValidatedEventResource}", url="${userValidatedEventResource.url:http://localhost:8091}", configuration = UserManagementClientConfiguration.class)
public interface UserValidatedEventResourceApiClient extends UserValidatedEventResourceApi {
}
