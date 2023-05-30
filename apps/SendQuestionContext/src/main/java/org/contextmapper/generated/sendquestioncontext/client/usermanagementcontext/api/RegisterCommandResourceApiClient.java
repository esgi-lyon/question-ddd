package org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.UserManagementContextClientConfiguration;

@FeignClient(name="${registerCommandResource.name:registerCommandResource}", url="${registerCommandResource.url:http://localhost:8091}", configuration = UserManagementContextClientConfiguration.class)
public interface RegisterCommandResourceApiClient extends RegisterCommandResourceApi {
}
