package org.contextmapper.generated.questioncontext.client.usermanagementcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.questioncontext.client.usermanagementcontext.ClientConfiguration;

@FeignClient(name="${userViewedEventResource.name:userViewedEventResource}", url="${userViewedEventResource.url:http://localhost:8081}", configuration = ClientConfiguration.class)
public interface UserViewedEventResourceApiClient extends UserViewedEventResourceApi {
}