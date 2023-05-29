package org.contextmapper.generated.questioncontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.questioncontext.client.usermanagementcontext.UserManagementClientConfiguration;

@FeignClient(name="${userRejectedEventResource.name:userRejectedEventResource}", url="${userRejectedEventResource.url:http://localhost:8081}", configuration = UserManagementClientConfiguration.class)
public interface UserRejectedEventResourceApiClient extends UserRejectedEventResourceApi {
}
