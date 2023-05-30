package org.contextmapper.generated.answercontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.answercontext.client.usermanagementcontext.UserManagementClientConfiguration;

@FeignClient(name="${registerCommandResource.name:registerCommandResource}", url="${registerCommandResource.url:http://localhost:8091}", configuration = UserManagementClientConfiguration.class)
public interface RegisterCommandResourceApiClient extends RegisterCommandResourceApi {
}
