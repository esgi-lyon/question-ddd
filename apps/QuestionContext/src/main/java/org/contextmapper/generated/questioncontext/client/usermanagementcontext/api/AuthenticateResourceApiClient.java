package org.contextmapper.generated.questioncontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.questioncontext.client.usermanagementcontext.UserManagementClientConfiguration;

@FeignClient(name="${authenticateResource.name:authenticateResource}", url="${authenticateResource.url:http://localhost:8081}", configuration = UserManagementClientConfiguration.class)
public interface AuthenticateResourceApiClient extends AuthenticateResourceApi {
}
