package org.contextmapper.generated.questioncontext.client.skillcontext.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.contextmapper.generated.questioncontext.client.skillcontext.ClientConfiguration;

@FeignClient(name="${createTagCommandResource.name:createTagCommandResource}", url="${createTagCommandResource.url:http://localhost:8083}", configuration = ClientConfiguration.class)
public interface CreateTagCommandResourceApiClient extends CreateTagCommandResourceApi {
}
