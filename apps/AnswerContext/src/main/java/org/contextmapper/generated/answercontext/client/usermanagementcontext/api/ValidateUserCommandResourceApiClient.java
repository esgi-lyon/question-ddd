package org.contextmapper.generated.answercontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.usermanagementcontext.UserManagementClientConfiguration;

@FeignClient(name="${validateUserCommandResource.name:validateUserCommandResource}", url="${validateUserCommandResource.url:http://localhost:8091}", configuration = UserManagementClientConfiguration.class)
public interface ValidateUserCommandResourceApiClient extends ValidateUserCommandResourceApi {
}
