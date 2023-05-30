package org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.UserManagementContextClientConfiguration;

@FeignClient(name="${commandHandlerResource.name:commandHandlerResource}", url="${commandHandlerResource.url:http://localhost:8091}", configuration = UserManagementContextClientConfiguration.class)
public interface CommandHandlerResourceApiClient extends CommandHandlerResourceApi {
}
