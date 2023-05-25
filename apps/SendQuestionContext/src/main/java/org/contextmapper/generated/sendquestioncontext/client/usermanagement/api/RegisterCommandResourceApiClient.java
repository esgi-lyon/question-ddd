package org.contextmapper.generated.sendquestioncontext.client.usermanagement.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.sendquestioncontext.client.usermanagement.ClientConfiguration;

@FeignClient(name="${registerCommandResource.name:registerCommandResource}", url="${registerCommandResource.url:http://127.0.0.1:8081}", configuration = ClientConfiguration.class)
public interface RegisterCommandResourceApiClient extends RegisterCommandResourceApi {
}
