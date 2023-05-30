package org.contextmapper.generated.questioncontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.questioncontext.client.usermanagementcontext.UserManagementClientConfiguration;

@FeignClient(name="${commandHandlerResource.name:commandHandlerResource}", url="${commandHandlerResource.url:http://localhost:8091}", configuration = UserManagementClientConfiguration.class)
public interface CommandHandlerResourceApiClient extends CommandHandlerResourceApi {
}
