package org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.usermanagementcontext.UserManagementContextClientConfiguration;

@FeignClient(name="${userViewedEventResource.name:userViewedEventResource}", url="${userViewedEventResource.url:http://localhost:8091}", configuration = UserManagementContextClientConfiguration.class)
public interface UserViewedEventResourceApiClient extends UserViewedEventResourceApi {
}
