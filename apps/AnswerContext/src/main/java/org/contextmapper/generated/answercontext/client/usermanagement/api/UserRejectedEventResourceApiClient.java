package org.contextmapper.generated.answercontext.client.usermanagement.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.usermanagement.UserManagementContextClientConfiguration;

@FeignClient(name="${userRejectedEventResource.name:userRejectedEventResource}", url="${userRejectedEventResource.url:http://127.0.0.1:8081}", configuration = UserManagementContextClientConfiguration.class)
public interface UserRejectedEventResourceApiClient extends UserRejectedEventResourceApi {
}
