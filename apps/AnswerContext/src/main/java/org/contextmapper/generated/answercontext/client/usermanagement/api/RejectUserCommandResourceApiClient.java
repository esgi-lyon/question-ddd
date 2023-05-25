package org.contextmapper.generated.answercontext.client.usermanagement.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.usermanagement.UserManagementContextClientConfiguration;

@FeignClient(name="${rejectUserCommandResource.name:rejectUserCommandResource}", url="${rejectUserCommandResource.url:http://127.0.0.1:8081}", configuration = UserManagementContextClientConfiguration.class)
public interface RejectUserCommandResourceApiClient extends RejectUserCommandResourceApi {
}
