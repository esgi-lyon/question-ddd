package org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.UserManagementContextClientConfiguration;

@FeignClient(name="${viewUserByEmailCommandResource.name:viewUserByEmailCommandResource}", url="${viewUserByEmailCommandResource.url:http://localhost:8091}", configuration = UserManagementContextClientConfiguration.class)
public interface ViewUserByEmailCommandResourceApiClient extends ViewUserByEmailCommandResourceApi {
}
