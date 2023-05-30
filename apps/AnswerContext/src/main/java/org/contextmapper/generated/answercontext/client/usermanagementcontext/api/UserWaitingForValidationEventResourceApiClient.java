package org.contextmapper.generated.answercontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.usermanagementcontext.UserManagementClientConfiguration;

@FeignClient(name="${userWaitingForValidationEventResource.name:userWaitingForValidationEventResource}", url="${userWaitingForValidationEventResource.url:http://localhost:8091}", configuration = UserManagementClientConfiguration.class)
public interface UserWaitingForValidationEventResourceApiClient extends UserWaitingForValidationEventResourceApi {
}
