package org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.UserManagementContextClientConfiguration;

@FeignClient(name="${validateUserCommandResource.name:validateUserCommandResource}", url="${validateUserCommandResource.url:http://localhost:8091}", configuration = UserManagementContextClientConfiguration.class)
public interface ValidateUserCommandResourceApiClient extends ValidateUserCommandResourceApi {
}
