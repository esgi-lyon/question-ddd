package org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.UserManagementContextClientConfiguration;

@FeignClient(name="${userValidatedEventResource.name:userValidatedEventResource}", url="${userValidatedEventResource.url:http://localhost:8091}", configuration = UserManagementContextClientConfiguration.class)
public interface UserValidatedEventResourceApiClient extends UserValidatedEventResourceApi {
}
